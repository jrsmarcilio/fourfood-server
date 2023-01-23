package br.com.ifpe.oxefoodmarcilio.modelo.empresa;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefoodmarcilio.modelo.acesso.Usuario;
import br.com.ifpe.oxefoodmarcilio.modelo.acesso.UsuarioService;
import br.com.ifpe.oxefoodmarcilio.servicos.empresa.CategoriaEmpresaRequest;
import br.com.ifpe.oxefoodmarcilio.servicos.empresa.EmpresaRequest;
import br.com.ifpe.oxefoodmarcilio.util.entity.GenericService;
import br.com.ifpe.oxefoodmarcilio.util.exception.EntityAlreadyExistsException;

@Service
public class EmpresaService extends GenericService {

	@Autowired
	private EmpresaRepository repository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private CategoriaEmpresaService categoriaEmpresaService;

	@Transactional
	public Empresa save(Empresa empresa) {
		usuarioService.save(empresa.getUsuario());
		super.preencherCamposAuditoria(empresa);
		return repository.save(empresa);
	}

	@Transactional
	public Empresa findById(Long id) {
		return repository.findById(id).get();
	}

	@Transactional
	public Empresa findByChave(String chave) {
		return repository.findByChave(chave);
	}

	@Transactional
	public List<Empresa> consultarPorChave(String chave) {
		return repository.findByChaveOrderByNomeEmpresarialAsc(chave);
	}

	@Transactional
	public void update(Long id, Empresa empresaAlterada) {
		validarEmpresaExistente(empresaAlterada, id);
		Empresa empresa = this.findById(id);
		empresa.updateFrom(empresaAlterada);
		super.preencherCamposAuditoria(empresa);
		repository.save(empresa);
	}

	@Transactional
	public void delete(Long id) {
		Empresa empresa = this.findById(id);
		empresa.setHabilitado(Boolean.FALSE);
		super.preencherCamposAuditoria(empresa);
		repository.save(empresa);
	}

	private void validarEmpresaExistente(Empresa empresaParam, Long id) {
		if (StringUtils.isNotBlank(empresaParam.getNomeEmpresarial())) {
			Empresa empresa = repository.findByChaveAndName(empresaParam.getChave(), empresaParam.getNomeEmpresarial());
			if (id == null) {
				if (empresa != null) {
					throw new EntityAlreadyExistsException(Empresa.LABEL, "NomeEmpresarial");
				}
			} else {
				if (empresa != null && empresa.getId() != id) {
					throw new EntityAlreadyExistsException(Empresa.LABEL, "NomeEmpresarial");
				}
			}
		}
	}

	@Transactional
	public List<Empresa> obterTodasEmpresas() {
		return repository.findAll();
	}

	@Transactional
	public List<Empresa> obterEmpresasPorCategoriaId(Long id) {
		return repository.findByCategoriaId(id);
	}

	public List<Empresa> saveList(@Valid List<EmpresaRequest> request) {
		List<Empresa> empresas = new ArrayList<>();
		
		for (EmpresaRequest empresaRequest : request) {
			System.out.println("file: EmpresaService.java:99 ~ empresaRequest" + empresaRequest.getEmail());

			Empresa empresa = new Empresa();
			Usuario usuario = new Usuario();

			usuario.setUsername(empresaRequest.getEmail());
			usuario.setPassword(empresaRequest.getPassword());
			usuario.setHabilitado(Boolean.TRUE);
			empresa.setUsuario(usuario);

			empresa.setChave(empresaRequest.getChave());
			empresa.setSite(empresaRequest.getSite());
			empresa.setCnpj(empresaRequest.getCnpj());
			empresa.setInscricaoEstadual(empresaRequest.getInscricaoEstadual());
			empresa.setNomeEmpresarial(empresaRequest.getNomeEmpresarial());
			empresa.setNomeFantasia(empresaRequest.getNomeFantasia());
			empresa.setFone(empresaRequest.getFone());
			empresa.setFoneAlternativo(empresaRequest.getFoneAlternativo());
			empresa.setHabilitado(Boolean.TRUE);
			empresa.setCategoria(categoriaEmpresaService.findById(empresaRequest.getIdCategoria()));
			empresa.getUsuario().getRoles().add(Usuario.ROLE_EMPRESA_ADMIN);

			empresa.getUsuario().builder().build();
			empresa.builder().build();

			empresas.add(empresa);
		}

		return repository.saveAll(empresas);
	}
}

package br.com.ifpe.oxefoodmarcilio.modelo.empresa;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefoodmarcilio.servicos.empresa.CategoriaEmpresaRequest;
import br.com.ifpe.oxefoodmarcilio.util.entity.GenericService;
import br.com.ifpe.oxefoodmarcilio.util.exception.EntityAlreadyExistsException;

@Service
public class CategoriaEmpresaService extends GenericService {

	@Autowired
	private CategoriaEmpresaRepository repository;

	@Transactional
	public CategoriaEmpresa findById(Long id) {
		return repository.findById(id).get();
	}

	@Transactional
	public CategoriaEmpresa save(CategoriaEmpresa CategoriaEmpresa) {
		super.validarRegistroVazio(CategoriaEmpresa.getDescricao(), "descrição");
		this.validarCategoriaEmpresaExistente(CategoriaEmpresa, null);
		super.preencherCamposAuditoria(CategoriaEmpresa);
		return repository.save(CategoriaEmpresa);
	}

	@Transactional
	public void update(Long id, CategoriaEmpresa CategoriaEmpresaAlterado) {
		validarCategoriaEmpresaExistente(CategoriaEmpresaAlterado, id);
		CategoriaEmpresa categoria = this.findById(id);
		categoria.updateFrom(CategoriaEmpresaAlterado);
		super.preencherCamposAuditoria(categoria);
		repository.save(categoria);
	}

	@Transactional
	public void delete(Long id) {
		CategoriaEmpresa categoria = this.findById(id);
		categoria.setHabilitado(Boolean.FALSE);
		super.preencherCamposAuditoria(categoria);
		repository.save(categoria);
	}

	private void validarCategoriaEmpresaExistente(CategoriaEmpresa categoriaParam, Long id) {
		if (StringUtils.isNotBlank(categoriaParam.getDescricao())) {
			CategoriaEmpresa categoria = repository.findByDescricao(categoriaParam.getDescricao());
			if (id == null) {
				if (categoria != null) {
					throw new EntityAlreadyExistsException(CategoriaEmpresa.LABEL, "Descrição");
				}
			} else {
				if (categoria != null && categoria.getId().equals(id)) {
					throw new EntityAlreadyExistsException(CategoriaEmpresa.LABEL, "Descrição");
				}
			}
		}
	}

	public List<CategoriaEmpresa> obterTodasCategorias() {
		return repository.findAll();
	}

  public List<CategoriaEmpresa> saveList(@Valid List<CategoriaEmpresaRequest> request) {
		List<CategoriaEmpresa> categorias = new ArrayList<>();
		for (CategoriaEmpresaRequest categoriaRequest : request) {
			CategoriaEmpresa categoria = new CategoriaEmpresa();
			categoria.setDescricao(categoriaRequest.getDescricao());
			categoria.setImagem(categoriaRequest.getImagem());
			categoria.setHabilitado(true);
			categorias.add(categoria);
		}
		return repository.saveAll(categorias);
  }
}

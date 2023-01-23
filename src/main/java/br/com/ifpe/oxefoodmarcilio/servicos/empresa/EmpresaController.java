package br.com.ifpe.oxefoodmarcilio.servicos.empresa;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefoodmarcilio.modelo.acesso.Usuario;
import br.com.ifpe.oxefoodmarcilio.modelo.empresa.CategoriaEmpresaService;
import br.com.ifpe.oxefoodmarcilio.modelo.empresa.Empresa;
import br.com.ifpe.oxefoodmarcilio.modelo.empresa.EmpresaService;
import br.com.ifpe.oxefoodmarcilio.util.entity.GenericController;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/empresa")
public class EmpresaController extends GenericController {

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private CategoriaEmpresaService categoriaEmpresaService;

	@ApiOperation(value = "Serviço responsável por obter uma empresa referente ao Id passado na URL.")
	@GetMapping("/{id}")
	public Empresa get(@PathVariable Long id) {
		return empresaService.findById(id);
	}

	@ApiOperation(value = "Serviço responsável por obter todas as empresa cadastradas.")
	@GetMapping
	public List<Empresa> empresas() {
		return empresaService.obterTodasEmpresas();
	}

	@ApiOperation(value = "Serviço responsável por salvar uma empresa no sistema.")
	@PostMapping
	public ResponseEntity<Empresa> save(@RequestBody @Valid EmpresaRequest request) {
		Empresa empresa = request.buildEmpresa();

		empresa.getUsuario().getRoles()
				.add(request.getPerfil().equals("Usuario") ? Usuario.ROLE_EMPRESA_USER : Usuario.ROLE_EMPRESA_ADMIN);

		empresa.setCategoria(categoriaEmpresaService.findById(request.getIdCategoria()));
		Empresa empresaCriada = empresaService.save(empresa);
		return new ResponseEntity<Empresa>(empresaCriada, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Serviço responsável por salvar uma lista de empresa no sistema.")
	@PostMapping("/list")
	public ResponseEntity<List<Empresa>> save(@RequestBody List<EmpresaRequest> request) {
		List<Empresa> empresasCriadas = empresaService.saveList(request);
		return new ResponseEntity<List<Empresa>>(empresasCriadas, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Serviço responsável por obter uma lista de empresa referente ao id passada na URL.")
	@GetMapping("/porcategoria/{id}")
	public List<Empresa> consultarPorIdCategoria(@PathVariable Long id) {
		return empresaService.obterEmpresasPorCategoriaId(id);
	}

	@ApiOperation(value = "Serviço responsável por obter uma empresa referente a chave passada na URL.")
	@GetMapping("/porchave/{chave}")
	public List<Empresa> consultarPorChave(@PathVariable String chave) {
		return empresaService.consultarPorChave(chave);
	}

	@ApiOperation(value = "Serviço responsável por atualizar as informações de uma empresa no sistema.")
	@PutMapping("/{id}")
	public ResponseEntity<Empresa> update(@PathVariable("id") Long id, @RequestBody EmpresaRequest request) {
		Empresa empresa = request.buildEmpresa();
		empresa.setCategoria(categoriaEmpresaService.findById(id));
		empresaService.update(id, empresa);
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "Rota responsável por remover(exclusão lógica) de uma empresa do sistema.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		empresaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}

package br.com.ifpe.oxefoodmarcilio.servicos.empresa;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefoodmarcilio.modelo.empresa.CategoriaEmpresa;
import br.com.ifpe.oxefoodmarcilio.modelo.empresa.CategoriaEmpresaService;
import br.com.ifpe.oxefoodmarcilio.util.entity.GenericController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/categoriaempresa")
public class CategoriaEmpresaController extends GenericController {

	@Autowired
	private CategoriaEmpresaService CategoriaEmpresaService;

	@ApiOperation(value = "Serviço responsável por salvar uma categoria de produto no sistema.")
	@PostMapping
	public ResponseEntity<CategoriaEmpresa> save(@RequestBody @Valid CategoriaEmpresaRequest request) {
		validarPreenchimentoChave(request.getChaveEmpresa());
		CategoriaEmpresa CategoriaEmpresa = CategoriaEmpresaService.save(request.buildCategoriaEmpresa());
		return new ResponseEntity<CategoriaEmpresa>(CategoriaEmpresa, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Serviço responsável por obter uma categoria de produto referente ao Id passado na URL.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a categoria de produto."),
			@ApiResponse(code = 401, message = "Acesso não autorizado."),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
			@ApiResponse(code = 404, message = "Não foi encontrado um registro para o Id informado."),
			@ApiResponse(code = 500, message = "Foi gerado um erro no servidor."), })
	@GetMapping("/{id}")
	public CategoriaEmpresa get(@PathVariable Long id) {
		return CategoriaEmpresaService.findById(id);
	}

	@ApiOperation(value = "Serviço responsável por obter uma lista de categorias de produto cadastradas.")
	@GetMapping
	public List<CategoriaEmpresa> categorias() {
		return CategoriaEmpresaService.obterTodasCategorias();
	}

	@ApiOperation(value = "Serviço responsável por obter uma lista de categorias de produto da empresa passado na URL.")
	@GetMapping("/porempresa/{chaveEmpresa}")
	public List<CategoriaEmpresa> consultarPorChaveEmpresa(@PathVariable String chaveEmpresa) {
		return CategoriaEmpresaService.consultarPorChaveEmpresa(chaveEmpresa);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Serviço responsável por atualizar as informações da categoria de produto no sistema.")
	public ResponseEntity<CategoriaEmpresa> update(@PathVariable("id") Long id,
			@RequestBody CategoriaEmpresaRequest request) {
		CategoriaEmpresaService.update(id, request.buildCategoriaEmpresa());
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Rota responsável por remover(exclusão lógica) uma categoria de produto do sistema.")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		CategoriaEmpresaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}

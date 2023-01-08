package br.com.ifpe.oxefoodmarcilio.servicos.cliente;

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

import br.com.ifpe.oxefoodmarcilio.modelo.cliente.Cliente;
import br.com.ifpe.oxefoodmarcilio.modelo.cliente.ClienteService;
import br.com.ifpe.oxefoodmarcilio.util.entity.GenericController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/cliente")
public class ClienteController extends GenericController {

	@Autowired
	private ClienteService clienteService;

	@ApiOperation(value = "Serviço responsável por salvar um cliente no sistema.")
	@PostMapping
	public ResponseEntity<Cliente> save(@RequestBody @Valid ClienteRequest request) {
		Cliente clienteRequisicao = request.buildCliente();
		Cliente clienteSalvo = clienteService.save(clienteRequisicao);
		return new ResponseEntity<Cliente>(clienteSalvo, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Serviço responsável por obter todos os cliente cadastrados.")
	@GetMapping
	public List<Cliente> clientes() {
		return clienteService.obterTodosClientes();
	}
	
	@ApiOperation(value = "Serviço responsável por obter um cliente referente ao Id passado na URL.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna o cliente."),
			@ApiResponse(code = 401, message = "Acesso não autorizado."),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
			@ApiResponse(code = 404, message = "Não foi encontrado um registro para o Id informado."),
			@ApiResponse(code = 500, message = "Foi gerado um erro no servidor."), })
	@GetMapping("/{id}")
	public Cliente obterClientePorID(@PathVariable Long id) {
		System.out.println(id);
		return clienteService.obterClientePorID(id);
	}

	@ApiOperation(value = "Serviço responsável por obter uma lista de clientes da empresa passado na URL.")
	@GetMapping("/porempresa/{chaveEmpresa}")
	public List<Cliente> consultarPorChaveEmpresa(@PathVariable String chaveEmpresa) {
		return clienteService.consultarPorChaveEmpresa(chaveEmpresa);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Serviço responsável por atualizar as informações do cliente no sistema.")
	public ResponseEntity<Cliente> update(@PathVariable("id") Long id, @RequestBody ClienteRequest request) {
		clienteService.update(id, request.buildCliente());
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Rota responsável por remover(exclusão lógica) um cliente do sistema.")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
}

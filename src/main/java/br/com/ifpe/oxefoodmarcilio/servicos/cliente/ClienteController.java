package br.com.ifpe.oxefoodmarcilio.servicos.cliente;

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

import br.com.ifpe.oxefoodmarcilio.modelo.cliente.Cliente;
import br.com.ifpe.oxefoodmarcilio.modelo.cliente.ClienteService;
import br.com.ifpe.oxefoodmarcilio.util.entity.GenericController;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController extends GenericController {

	@Autowired
	private ClienteService clienteService;

	@ApiOperation(value = "Serviço responsável por salvar um cliente no sistema.")
	@PostMapping
	public ResponseEntity<Cliente> save(@RequestBody @Valid ClienteRequest request) {
		super.validarPreenchimentoChave(request.getChaveEmpresa());
		Cliente cliente = clienteService.save(request.buildCliente());
		return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Serviço responsável por obter um cliente referente ao Id passado na URL.")
	@GetMapping("/{id}")
	public Cliente get(@PathVariable Long id) {
		return clienteService.obterClientePorID(id);
	}

	@ApiOperation(value = "Serviço responsável por obter um cliente referente a chave passada na URL.")
	@GetMapping("/porempresa/{chaveEmpresa}")
	public List<Cliente> consultarPorChaveEmpresa(@PathVariable String chaveEmpresa) {
		return clienteService.consultarPorChaveEmpresa(chaveEmpresa);
	}

	@ApiOperation(value = "Serviço responsável por atualizar as informações de um cliente no sistema.")
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> update(@PathVariable("id") Long id, @RequestBody ClienteRequest request) {
		clienteService.update(id, request.buildCliente());
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "Rota responsável por remover(exclusão lógica) de um cliente do sistema.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}

}

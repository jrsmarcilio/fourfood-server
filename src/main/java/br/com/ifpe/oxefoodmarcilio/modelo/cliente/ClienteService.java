package br.com.ifpe.oxefoodmarcilio.modelo.cliente;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefoodmarcilio.mensagens.EmailService;
import br.com.ifpe.oxefoodmarcilio.util.entity.GenericService;
import br.com.ifpe.oxefoodmarcilio.util.exception.EntidadeNaoEncontradaException;

@Service
public class ClienteService extends GenericService {

	@Autowired
	private ClienteRepository repository;
	@Autowired
	private EmailService emailService;

	@Transactional
	public Cliente save(Cliente cliente) {
		super.preencherCamposAuditoria(cliente);
		Cliente clienteSalvo = repository.save(cliente);
		emailService.enviarEmailConfirmacaoCadastroCliente(clienteSalvo);
		return clienteSalvo;
	}

	@Transactional
	public Cliente obterClientePorID(Long id) {
		Optional<Cliente> consulta = repository.findById(id);
		if (consulta.isPresent()) {
			return consulta.get();
		} else {
			throw new EntidadeNaoEncontradaException("Cliente", id);
		}
	}

	@Transactional
	public List<Cliente> consultarPorChaveEmpresa(String chaveEmpresa) {
		return repository.findByChaveEmpresaOrderByNomeAsc(chaveEmpresa);
	}

	@Transactional
	public void update(Long id, Cliente clienteAlterado) {
		Cliente cliente = this.obterClientePorID(id);
		cliente.updateFrom(clienteAlterado);
		super.preencherCamposAuditoria(cliente);
		repository.save(cliente);
	}

	@Transactional
	public void delete(Long id) {
		Cliente cliente = this.obterClientePorID(id);
		cliente.setHabilitado(Boolean.FALSE);
		super.preencherCamposAuditoria(cliente);
		repository.save(cliente);
	}
}

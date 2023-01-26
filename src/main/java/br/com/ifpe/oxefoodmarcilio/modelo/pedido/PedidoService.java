package br.com.ifpe.oxefoodmarcilio.modelo.pedido;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefoodmarcilio.util.entity.GenericService;

@Service
public class PedidoService extends GenericService {

  @Autowired
  private PedidoRepository pedidoRepository;

  @Transactional
  public Pedido save(Pedido pedido) {
    super.preencherCamposAuditoria(pedido);
    return pedidoRepository.save(pedido);
  }

  @Transactional
  public Pedido findById(Long id) {
    return pedidoRepository.findById(id).get();
  }

  @Transactional
  public List<Pedido> findAllByClienteId(Long id) {
    return pedidoRepository.findAllByClienteId(id);
  }

  @Transactional
  public Pedido update(Long id, Pedido pedidoAlterado) {
    Pedido pedido = this.findById(id);
    pedido.updateFrom(pedidoAlterado);
    super.preencherCamposAuditoria(pedido);
    pedidoRepository.save(pedido);
    return pedido;
  }
}

package br.com.ifpe.oxefoodmarcilio.servicos.pedido;

import br.com.ifpe.oxefoodmarcilio.modelo.pedido.Pedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequest {

  private String status;
  private String formaPagamento;
  private String endereco;
  private String valorTotal;
  private String valorFrete;
  private String tempoEntrega;
  private Long empresaId;

  public Pedido buildPedido() {
    return Pedido.builder()
        .status(status)
        .formaPagamento(formaPagamento)
        .endereco(endereco)
        .valorTotal(valorTotal)
        .valorFrete(valorFrete)
        .tempoEntrega(tempoEntrega)
        .build();
  }
}

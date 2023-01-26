package br.com.ifpe.oxefoodmarcilio.modelo.pedido;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ifpe.oxefoodmarcilio.modelo.empresa.Empresa;
import br.com.ifpe.oxefoodmarcilio.util.entity.EntidadeAuditavel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Pedido")
@Where(clause = "habilitado = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido extends EntidadeAuditavel {

  @NotNull
  @Column(nullable = false, length = 20)
  private String status;

  @NotNull
  @Column(nullable = false, length = 255)
  private String formaPagamento;

  @NotNull
  @Column(nullable = false, length = 255)
  private String endereco;

  @NotNull
  @Column(nullable = false, length = 20)
  private String valorTotal;

  @NotNull
  @Column(nullable = false, length = 20)
  private String valorFrete;

  @NotNull
  @Column(nullable = false, length = 20)
  private String tempoEntrega;

  @JsonIgnore
  @ManyToOne
  private Empresa empresa;

  public void updateFrom(Pedido param) {
    this.setStatus(param.getStatus());
    this.setFormaPagamento(param.getFormaPagamento());
    this.setEndereco(param.getEndereco());
    this.setValorTotal(param.getValorTotal());
    this.setValorFrete(param.getValorFrete());
    this.setTempoEntrega(param.getTempoEntrega());
  }
}

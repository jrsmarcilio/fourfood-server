package br.com.ifpe.oxefoodmarcilio.modelo.produto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import br.com.ifpe.oxefoodmarcilio.modelo.empresa.Empresa;
import br.com.ifpe.oxefoodmarcilio.util.entity.EntidadeAuditavel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Produto")
@Where(clause = "habilitado = true")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Produto extends EntidadeAuditavel {

  private static final long serialVersionUID = -9200344334923335616L;
  public static final String LABEL = "Produto";

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "chaveEmpresa")
  private Empresa empresa;

  @Column(length = 300, nullable = false)
  private String imagem;

  @Column(length = 300, nullable = false)
  private String titulo;

  @Column(length = 100000)
  private String descricao;

  @Column(nullable = false)
  private double valorUnitario;

  public void updateFrom(Produto param) {
    this.setTitulo(param.getTitulo());
    this.setImagem(param.getImagem());
    this.setDescricao(param.getDescricao());
    this.setValorUnitario(param.getValorUnitario());
  }
}

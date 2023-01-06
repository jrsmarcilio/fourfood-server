package br.com.ifpe.oxefoodmarcilio.modelo.produto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@ManyToOne
	@JoinColumn
	private CategoriaProduto categoria;

	@JsonIgnore
	@Column
	private String chaveEmpresa;

	@Column
	private String codigo;

	@Column(length = 300, nullable = false)
	private String titulo;

	@Column(length = 100000)
	private String descricao;

	@Column(nullable = false)
	private double valorUnitario;

	@Column(length = 50)
	private String tempoEntrega;

	@Column
	private Boolean temComplemento;

	@Column
	private Boolean emDestaque;

	public void updateFrom(Produto param) {
		this.setCategoria(param.getCategoria());
		this.setTitulo(param.getTitulo());
		this.setCodigo(param.getCodigo());
		this.setDescricao(param.getDescricao());
		this.setValorUnitario(param.getValorUnitario());
		this.setTempoEntrega(param.getTempoEntrega());
		this.setTemComplemento(param.getTemComplemento());
		this.setEmDestaque(param.getEmDestaque());
	}
}

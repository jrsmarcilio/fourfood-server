package br.com.ifpe.oxefoodmarcilio.modelo.cliente;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import br.com.ifpe.oxefoodmarcilio.util.entity.EntidadeNegocio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Builder
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "habilitado = true")
public class EnderecoCliente extends EntidadeNegocio implements Serializable {

	private static final long serialVersionUID = -7327761359456234333L;
	public static final String LABEL = "EnderecoCliente";

	@ManyToOne
	@JoinColumn
	private Cliente cliente;

	@NotNull
	@Column(nullable = false, length = 100)
	private String rua;

	@NotNull
	@Column(nullable = false, length = 100)
	private String numero;

	@NotNull
	@Column(nullable = false, length = 100)
	private String bairro;

	@NotNull
	@Column(nullable = false, length = 100)
	private String cep;

	@NotNull
	@Column(nullable = false, length = 100)
	private String cidade;

	@NotNull
	@Column(nullable = false, length = 100)
	private String estado;

	@NotNull
	@Column(nullable = false, length = 100)
	private String complemento;

	public void updateFrom(EnderecoCliente param) {
		this.setRua(param.getRua());
		this.setNumero(param.getNumero());
		this.setBairro(param.getBairro());
		this.setCep(param.getCep());
		this.setCidade(param.getCidade());
		this.setEstado(param.getEstado());
		this.setComplemento(param.getComplemento());
	}

}

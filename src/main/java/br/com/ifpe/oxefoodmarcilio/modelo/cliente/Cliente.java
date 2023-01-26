package br.com.ifpe.oxefoodmarcilio.modelo.cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import br.com.ifpe.oxefoodmarcilio.modelo.acesso.Usuario;
import br.com.ifpe.oxefoodmarcilio.util.entity.EntidadeAuditavel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Cliente")
@Where(clause = "habilitado = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends EntidadeAuditavel {

	private static final long serialVersionUID = -2168789211116655513L;
	public static final String LABEL = "Cliente";

	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;

	@Column
	private String email;

	@NotNull
	@Column(nullable = false, length = 100)
	private String nome;

	@NotNull
	@Column(nullable = false, length = 20)
	private String cpf;

	@NotNull
	@Column(nullable = false, length = 20)
	private String fone;

	@NotNull
	@Column(nullable = false, length = 20)
	private String foneAlternativo;

	public void updateFrom(Cliente param) {
		this.setEmail(param.getEmail());
		this.setNome(param.getNome());
		this.setCpf(param.getCpf());
		this.setFone(param.getFone());
		this.setFoneAlternativo(param.getFoneAlternativo());
	}
}

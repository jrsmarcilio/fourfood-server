package br.com.ifpe.oxefoodmarcilio.modelo.empresa;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ifpe.oxefoodmarcilio.modelo.acesso.Usuario;
import br.com.ifpe.oxefoodmarcilio.modelo.produto.Produto;
import br.com.ifpe.oxefoodmarcilio.util.entity.EntidadeAuditavel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Empresa")
@Where(clause = "habilitado = true")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Empresa extends EntidadeAuditavel {

	private static final long serialVersionUID = -1391900242151423745L;
	public static final String LABEL = "Empresa";

	@JsonIgnore
	@NotNull
	@Column(nullable = false)
	private String chave;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;

	@OneToMany(mappedBy = "empresa", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<Produto> produtos;

	@ManyToOne
	@JoinColumn(nullable = false)
	private CategoriaEmpresa categoria;

	@NotNull
	@Column(nullable = false, length = 18)
	private String cnpj;

	@NotNull
	@Column(nullable = false, length = 100)
	private String nomeFantasia;

	@NotNull
	@Column(nullable = false, length = 20)
	private String fone;

	public void updateFrom(Empresa params) {
		this.setCnpj(params.getCnpj());
		this.setNomeFantasia(params.getNomeFantasia());
		this.setFone(params.getFone());
	}
}

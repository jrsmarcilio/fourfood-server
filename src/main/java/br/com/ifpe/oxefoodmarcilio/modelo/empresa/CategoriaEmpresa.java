package br.com.ifpe.oxefoodmarcilio.modelo.empresa;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ifpe.oxefoodmarcilio.util.entity.EntidadeAuditavel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CategoriaEmpresa")
@Where(clause = "habilitado = true")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaEmpresa extends EntidadeAuditavel {

	private static final long serialVersionUID = 428068840377028579L;
	public static final String LABEL = "Categoria de Empresa";

	@NotNull
	@Column(nullable = false, length = 100)
	private String descricao;

	@NotNull
	@Column(nullable = false)
	private String imagem;

	@JsonIgnore
	@ElementCollection(fetch = FetchType.EAGER)
	@Builder.Default
	private List<Empresa> empresas = new ArrayList<>();

	public void updateFrom(CategoriaEmpresa param) {
		this.setDescricao(param.getDescricao());
		this.setImagem(param.getImagem());
	}
}

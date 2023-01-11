package br.com.ifpe.oxefoodmarcilio.modelo.empresa;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	@JsonIgnore
	@NotNull
	@Column(nullable = false)
	private String chaveEmpresa;

	@NotNull
	@Column(nullable = false, length = 100)
	private String descricao;

	public void updateFrom(CategoriaEmpresa param) {
		this.setDescricao(param.getDescricao());
	}

}

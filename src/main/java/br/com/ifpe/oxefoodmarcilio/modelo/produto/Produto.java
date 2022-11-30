package br.com.ifpe.oxefoodmarcilio.modelo.produto;

import br.com.ifpe.oxefoodmarcilio.util.entity.EntidadeAuditavel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaProduto extends EntidadeAuditavel {

	private static final long serialVersionUID = 5118202318879054032L;
	private String chaveEmpresa;
	private String descricao;
}

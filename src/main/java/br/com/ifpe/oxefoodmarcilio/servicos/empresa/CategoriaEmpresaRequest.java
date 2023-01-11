package br.com.ifpe.oxefoodmarcilio.servicos.empresa;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.ifpe.oxefoodmarcilio.modelo.empresa.CategoriaEmpresa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaEmpresaRequest {

	@NotNull(message = "O Chave Empresa é de preenchimento obrigatório")
	@NotBlank(message = "O Chave Empresa é de preenchimento obrigatório")
	private String chaveEmpresa;

	@Size(max = 1000)
	@NotNull(message = "A descrição é de preenchimento obrigatório")
	@NotBlank(message = "A descrição é de preenchimento obrigatório")
	private String descricao;

	public CategoriaEmpresa buildCategoriaEmpresa() {
		return CategoriaEmpresa.builder().chaveEmpresa(chaveEmpresa).descricao(descricao).build();
	}
}

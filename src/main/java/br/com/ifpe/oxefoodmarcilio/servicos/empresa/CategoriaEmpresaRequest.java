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

	@Size(max = 1000)
	@NotNull(message = "A descrição é de preenchimento obrigatório")
	@NotBlank(message = "A descrição é de preenchimento obrigatório")
	private String descricao;

	@Size(max = 1000)
	@NotNull(message = "A imagem é de preenchimento obrigatório")
	@NotBlank(message = "A imagem é de preenchimento obrigatório")
	private String imagem;

	public CategoriaEmpresa buildCategoriaEmpresa() {
		return CategoriaEmpresa.builder().descricao(descricao).imagem(imagem).build();
	}
}

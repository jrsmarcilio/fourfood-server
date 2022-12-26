package br.com.ifpe.oxefoodmarcilio.servicos.produto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.ifpe.oxefoodmarcilio.modelo.produto.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequest {

	@NotNull(message = "A identificação da categoria é de preenchimento obrigatório")
	@NotBlank(message = "A identificação da categoria é de preenchimento obrigatório")
	private Long idCategoria;

	@NotNull(message = "O Chave Empresa é de preenchimento obrigatório")
	@NotBlank(message = "O Chave Empresa é de preenchimento obrigatório")
	private String chaveEmpresa;

	@NotNull(message = "O Chave Empresa é de preenchimento obrigatório")
	@NotBlank(message = "O Chave Empresa é de preenchimento obrigatório")
	private String codigo;
	
	@NotNull(message = "O Chave Empresa é de preenchimento obrigatório")
	@NotBlank(message = "O Chave Empresa é de preenchimento obrigatório")
	private String titulo;

	@NotNull(message = "O valor unitário é de preenchimento obrigatório")
	@NotBlank(message = "O valor unitário é de preenchimento obrigatório")
	double valorUnitario;

	@NotNull(message = "A descrição é de preenchimento obrigatório")
	@NotBlank(message = "A descrição é de preenchimento obrigatório")
	private String descricao;

	public Produto buildProduto() {
		return Produto.builder().chaveEmpresa(chaveEmpresa).codigo(codigo).titulo(titulo).descricao(descricao)
				.valorUnitario(valorUnitario).build();
	}
}

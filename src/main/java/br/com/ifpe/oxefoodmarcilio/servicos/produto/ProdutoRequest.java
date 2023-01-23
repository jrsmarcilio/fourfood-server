package br.com.ifpe.oxefoodmarcilio.servicos.produto;

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

	private String chaveEmpresa;

	private String imagem;

	private String titulo;

	private String descricao;

	private double valorUnitario;

	private String tempoEntrega;

	private Boolean temComplemento;

	private Boolean emDestaque;

	public Produto buildProduto() {
		return Produto.builder().imagem(imagem).titulo(titulo).descricao(descricao)
				.valorUnitario(valorUnitario).tempoEntrega(tempoEntrega).temComplemento(temComplemento)
				.emDestaque(emDestaque).build();
	}
}

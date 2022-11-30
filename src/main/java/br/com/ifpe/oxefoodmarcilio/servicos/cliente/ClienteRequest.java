package br.com.ifpe.oxefoodmarcilio.servicos.cliente;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.ifpe.oxefoodmarcilio.modelo.cliente.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {

	private String chaveEmpresa;

	@Size(max = 1000)
	@NotNull
	@NotEmpty
	private String nome;

	@Size(max = 20)
	@NotNull
	private String cpf;

	@Size(max = 20)
	@NotNull
	private String fone;

	@Size(max = 20)
	@NotNull
	private String foneAlternativo;

	public Cliente buildCliente() {
		return Cliente.builder().chaveEmpresa(chaveEmpresa).nome(nome).cpf(cpf).fone(fone)
				.foneAlternativo(foneAlternativo).build();
	}

}

package br.com.ifpe.oxefoodmarcilio.servicos.empresa;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.ifpe.oxefoodmarcilio.modelo.acesso.Usuario;
import br.com.ifpe.oxefoodmarcilio.modelo.empresa.Empresa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaRequest {

	@NotNull(message = "A Chave é de preenchimento obrigatório")
	@NotBlank(message = "A Chave é de preenchimento obrigatório")
	private String chave;

	@NotNull(message = "O CNPJ é de preenchimento obrigatório")
	@NotBlank(message = "O CNPJ é de preenchimento obrigatório")
	private String cnpj;

	@NotNull(message = "O Nome fantasia é de preenchimento obrigatório")
	@NotBlank(message = "O Nome fantasia é de preenchimento obrigatório")
	@Length(max = 100, message = "O Nome fantasia deverá ter no máximo {max} caracteres")
	private String nomeFantasia;

	@NotNull(message = "O fone é de preenchimento obrigatório")
	@NotBlank(message = "O fone é de preenchimento obrigatório")
	private String fone;

	@NotBlank(message = "O e-mail é de preenchimento obrigatório")
	@Email
	private String email;

	@NotBlank(message = "A senha é de preenchimento obrigatório")
	private String password;

	@NotNull(message = "O perfil é de preenchimento obrigatório")
	@NotBlank(message = "O perfil é de preenchimento obrigatório")
	private String perfil;

	private Long idCategoria;

	public Empresa buildEmpresa() {
		return Empresa.builder().chave(chave).cnpj(cnpj).usuario(buildUsuario()).nomeFantasia(nomeFantasia).fone(fone)
				.build();
	}

	public Usuario buildUsuario() {
		return Usuario.builder().username(email).password(password).build();
	}
}

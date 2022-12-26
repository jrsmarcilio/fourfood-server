package br.com.ifpe.oxefoodmarcilio.servicos.acesso;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest implements Serializable {
	private static final long serialVersionUID = 5143608926588093260L;
	private String username;
	private String password;

}

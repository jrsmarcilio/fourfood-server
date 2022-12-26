package br.com.ifpe.oxefoodmarcilio.security.jwt;

import org.springframework.security.core.AuthenticationException;

public class InvalidJwtAuthenticationException extends AuthenticationException {

	private static final long serialVersionUID = 1670842635531866478L;

	public InvalidJwtAuthenticationException(String e) {
		super(e);
	}
}
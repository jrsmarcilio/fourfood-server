package br.com.ifpe.oxefoodmarcilio.util.entity;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = { "id" })
public abstract class EntidadeNegocio implements Serializable {

	private static final long serialVersionUID = -7435510029424995886L;
	private Long id;
	private Boolean habilitado;
}

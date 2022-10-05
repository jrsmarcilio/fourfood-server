package br.com.ifpe.oxefoodmarcilio.util.entity;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class EntidadeAuditavel extends EntidadeNegocio {

	private static final long serialVersionUID = 8934812310152304058L;
	private long versao;
	private LocalDate dataCriacao;
	private LocalDate dataUltimaModificacao;
	private Long criadoPor;
	private Long ultimaModificacaoPor;
	
}

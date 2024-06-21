package br.com.brunobrasilweb.api.core.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@AllArgsConstructor
@Getter
public enum TypeContract {

	OUT("OUT", "Outsourcing (terceirizado)"), CLT("CLT", "CLT"), PJ("PJ", "Pessoa Jurídica");

	private final String value;

	private final String label;

}

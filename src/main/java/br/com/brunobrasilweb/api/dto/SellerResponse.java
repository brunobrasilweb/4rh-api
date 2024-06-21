package br.com.brunobrasilweb.api.dto;

import br.com.brunobrasilweb.api.core.enums.TypeContract;
import br.com.brunobrasilweb.api.entity.Seller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static java.util.Objects.isNull;
import static lombok.AccessLevel.PUBLIC;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = PUBLIC, staticName = "of")
public class SellerResponse {

	private Long id;

	private String registration;

	private String name;

	private LocalDate birthDate;

	private String docNumber;

	private String email;

	private TypeContract typeContract;

	private FilialResponse filial;

	public static SellerResponse of(Seller entity) {
		if (isNull(entity)) {
			return null;
		}

		return SellerResponse.of(entity.getId(), entity.getRegistration(), entity.getName(), entity.getBirthDate(),
				entity.getDocNumber(), entity.getEmail(), entity.getTypeContract(),
				FilialResponse.of(entity.getFilial()));
	}

}

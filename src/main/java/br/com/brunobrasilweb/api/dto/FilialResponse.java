package br.com.brunobrasilweb.api.dto;

import br.com.brunobrasilweb.api.entity.Filial;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static java.util.Objects.isNull;
import static lombok.AccessLevel.PUBLIC;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = PUBLIC, staticName = "of")
public class FilialResponse {

	private Long id;

	private String name;

	private String cnpj;

	private String city;

	private String uf;

	private String type;

	private Boolean enable;

	private LocalDate AddedDate;

	private LocalDate UpdatedDate;

	public static FilialResponse of(Filial entity) {
		if (isNull(entity)) {
			return null;
		}

		return FilialResponse.of(entity.getId(), entity.getName(), entity.getCnpj(), entity.getCity(), entity.getUf(),
				entity.getType(), entity.getEnable(), entity.getAddedDate(), entity.getUpdatedDate());
	}

}

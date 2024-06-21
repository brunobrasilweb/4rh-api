package br.com.brunobrasilweb.api.dto;

import br.com.brunobrasilweb.api.core.enums.TypeContract;
import br.com.brunobrasilweb.api.core.validator.CpfCnpj;
import br.com.brunobrasilweb.api.entity.Filial;
import br.com.brunobrasilweb.api.entity.Seller;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static lombok.AccessLevel.PUBLIC;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = PUBLIC, staticName = "of")
public class SellerRequest {

	@NotEmpty
	private String name;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

	@NotEmpty
	@CpfCnpj
	private String docNumber;

	@NotEmpty
	@Email
	private String email;

	@NotNull
	private String typeContract;

	@NotNull
	private Long filialId;

	public Seller toEntity() {
		return Seller.of(null, null, name, birthDate, docNumber, email, TypeContract.valueOf(typeContract),
				new Filial().withId(filialId));
	}

}

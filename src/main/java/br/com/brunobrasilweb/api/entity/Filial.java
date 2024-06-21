package br.com.brunobrasilweb.api.entity;

import br.com.brunobrasilweb.api.core.enums.TypeContract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

import static lombok.AccessLevel.PUBLIC;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = PUBLIC, staticName = "of")
@Entity(name = "filial")
public class Filial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String cnpj;

	private String city;

	private String uf;

	private String type;

	private Boolean enable;

	@Column(name = "added_date")
	private LocalDate AddedDate;

	@Column(name = "updated_date")
	private LocalDate UpdatedDate;

	public Filial withId(Long id) {
		this.id = id;
		return this;
	}

}

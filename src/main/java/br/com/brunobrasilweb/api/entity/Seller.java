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
@Entity(name = "seller")
public class Seller {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String registration;

	private String name;

	@Column(name = "birth_date")
	private LocalDate birthDate;

	@Column(name = "doc_number")
	private String docNumber;

	private String email;

	@Column(name = "type_contract")
	@Enumerated(EnumType.STRING)
	private TypeContract typeContract;

	@ManyToOne
	@JoinColumn(name = "filial_id")
	private Filial filial;

	public Seller withId(Long id) {
		this.id = id;
		return this;
	}

	public Seller markAsNew() {
		this.id = null;
		return this;
	}

	public Seller withRegistration(String registration) {
		this.registration = registration;
		return this;
	}

}

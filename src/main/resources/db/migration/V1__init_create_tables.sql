CREATE TABLE filial (
	id BIGINT auto_increment NOT NULL,
	name varchar(100) NOT NULL,
	cnpj varchar(20) NULL,
	city varchar(100) NULL,
	uf varchar(2) NULL,
	`type` varchar(30) NULL,
	enable BOOL NOT NULL,
	added_date DATETIME NULL,
	updated_date DATETIME NULL,
	CONSTRAINT filial_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=latin1
COLLATE=latin1_swedish_ci;

CREATE TABLE seller (
	id BIGINT auto_increment NOT NULL,
	registration varchar(100) NOT NULL,
	name varchar(100) NOT NULL,
	birth_date DATE NULL,
	doc_number varchar(30) NOT NULL,
	email varchar(100) NOT NULL,
	type_contract varchar(30) NOT NULL,
	filial_id BIGINT NOT NULL,
	CONSTRAINT seller_pk PRIMARY KEY (id),
	CONSTRAINT seller_un UNIQUE KEY (registration),
	CONSTRAINT seller_FK FOREIGN KEY (filial_id) REFERENCES filial(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE=InnoDB
DEFAULT CHARSET=latin1
COLLATE=latin1_swedish_ci;

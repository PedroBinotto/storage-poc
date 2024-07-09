package com.storage.poc.model;

import java.util.Set;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "TB_PRONTUARIO")
public class Prontuario {
	private static final String SEQUENCE_NAME = "SQ_PRONTUARIO";

	@Id
	@SequenceGenerator(allocationSize = 1, sequenceName = SEQUENCE_NAME, name = SEQUENCE_NAME)
	@GeneratedValue(generator = SEQUENCE_NAME, strategy = GenerationType.SEQUENCE)
	@Column(name = "CO_SEQ_PRONTUARIO")
	private Long id;

	@OneToMany(mappedBy = "prontuario")
	private Set<Arquivo> arquivos;
}

package com.storage.poc.model;

import java.time.LocalDateTime;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "TB_ARQUIVO")
public class Arquivo {
	private static final String SEQUENCE_NAME = "SQ_ARQUIVO";

	@Id
	@SequenceGenerator(allocationSize = 1, sequenceName = SEQUENCE_NAME, name = SEQUENCE_NAME)
	@GeneratedValue(generator = SEQUENCE_NAME, strategy = GenerationType.SEQUENCE)
	@Column(name = "CO_SEQ_ARQUIVO")
	private Long id;

	@Column(name = "NO_ARQUIVO")
	private String nome;

	@Column(name = "NU_TAMANHO")
	private Long tamanho;

	@Column(name = "NU_HASH")
	private String hash;

	@Column(name = "DT_CRIACAO")
	private LocalDateTime dataCriacao = LocalDateTime.now();

	@ManyToOne
	@JoinColumn(name="CO_PRONTUARIO", nullable = false)
	private Prontuario prontuario;
}

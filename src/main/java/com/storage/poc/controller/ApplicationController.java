package com.storage.poc.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.io.IOException;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.val;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.storage.poc.model.Arquivo;
import com.storage.poc.repository.arquivo.ArquivoRepository;
import com.storage.poc.service.arquivo.ArquivoCreateService;
import com.storage.poc.service.storage.StorageReadService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApplicationController {
	private final ArquivoCreateService arquivoCreateService;
	private final StorageReadService storageReadService;
	private final ArquivoRepository arquivoRepository;

	@PostMapping("/{prontuarioId}/arquivos")
	public ResponseEntity<Arquivo> saveArquivo(
			@PathVariable Long prontuarioId,
			@RequestPart MultipartFile pdf
	) throws IOException {
		return ResponseEntity
				.status(CREATED)
				.body(this.arquivoCreateService.create(pdf.getBytes(), prontuarioId));
	}

	@GetMapping("/arquivos/{arquivoId}")
	public ResponseEntity<byte[]> getArquivo(
			@PathVariable Long arquivoId
	) throws IOException {
		val arquivo = arquivoRepository
				.findById(arquivoId)
				.orElseThrow(EntityNotFoundException::new);
		return ResponseEntity.ok(storageReadService.read(arquivo.getHash() + "_" + arquivo.getTamanho()));
	}
}

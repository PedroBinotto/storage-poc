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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/{prontuarioId}/arquivos")
public class ApplicationController {
	private final ArquivoCreateService arquivoCreateService;
	private final StorageReadService storageReadService;
	private final ArquivoRepository arquivoRepository;

	@PostMapping("/{nomeArquivo}")
	public ResponseEntity<Arquivo> saveArquivo(
			@PathVariable Long prontuarioId,
			@PathVariable String nomeArquivo,
			@RequestPart MultipartFile pdf
	) throws IOException {
		return ResponseEntity
				.status(CREATED)
				.body(this.arquivoCreateService.create(nomeArquivo, pdf.getBytes(), prontuarioId));
	}

	@GetMapping("/{arquivoId}")
	public ResponseEntity<byte[]> getArquivos(
			@PathVariable Long prontuarioId,
			@PathVariable Long arquivoId
	) throws IOException {
		val arquivo = arquivoRepository.findById(arquivoId).get();
		val response = storageReadService.read(arquivo.getHash() + "_" + arquivo.getTamanho());
		return ResponseEntity.ok(response);
	}
}

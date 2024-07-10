package com.storage.poc.service.arquivo;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import lombok.val;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.storage.poc.model.Arquivo;
import com.storage.poc.repository.arquivo.ArquivoRepository;
import com.storage.poc.repository.prontuario.ProntuarioRepository;
import com.storage.poc.service.storage.StorageWriteService;

@Service
@RequiredArgsConstructor
public class ArquivoCreateService {
	private final ArquivoRepository arquivoRepository;
	private final StorageWriteService storageWriteService;
	private final ArquivoHashService arquivoHashService;
	private final ProntuarioRepository prontuarioRepository;

	@Transactional
	public Arquivo create(byte[] data, Long prontuarioId) throws IOException {
		val arquivo = new Arquivo();
		val tamanho = (long) data.length;
		val hash = this.arquivoHashService.hash(data);
		val nomeArquivo = hash + "_" + tamanho;

		arquivo.setProntuario(prontuarioRepository.getReferenceById(prontuarioId));
		arquivo.setTamanho(tamanho);
		arquivo.setNome(nomeArquivo);
		arquivo.setHash(hash);

		this.storageWriteService.write(nomeArquivo, data);

		return this.arquivoRepository.save(arquivo);
	}
}

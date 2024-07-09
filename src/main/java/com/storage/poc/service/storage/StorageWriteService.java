package com.storage.poc.service.storage;

import java.io.IOException;
import java.nio.file.Files;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorageWriteService {
	private final StorageResolveService storageResolveService;

	public void write(String filename, byte[] bytes) throws IOException {
		Files.write(this.storageResolveService.resolve(filename), bytes);
	}
}

package com.storage.poc.service.storage;

import java.io.IOException;
import java.nio.file.Files;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorageReadService {
	private final StorageResolveService storageResolveService;

	public byte[] read(String filename) throws IOException {
		return Files.readAllBytes(this.storageResolveService.resolve(filename));
	}
}

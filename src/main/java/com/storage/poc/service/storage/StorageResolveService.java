package com.storage.poc.service.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.RequiredArgsConstructor;
import lombok.val;

import org.springframework.stereotype.Service;

import com.storage.poc.config.RuntimeConstants;
import jakarta.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class StorageResolveService {
	private final RuntimeConstants runtimeConstants;

	public Path resolve(String path) {
		return this.getPath().resolve(path).toAbsolutePath();
	}

	private Path getPath() {
		return Paths.get(runtimeConstants.getStorageRoot());
	}

	@PostConstruct
	private void init() throws IOException {
		val storageRoot = this.getPath();
		if (!storageRoot.toFile().exists()) {
			Files.createDirectories(storageRoot);
		}
	}
}

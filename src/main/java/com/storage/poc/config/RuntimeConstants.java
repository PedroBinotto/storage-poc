package com.storage.poc.config;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class RuntimeConstants {
	@Value("${storage.root}")
	private String storageRoot;
}

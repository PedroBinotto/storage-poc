package com.storage.poc.service.arquivo;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class ArquivoHashService {
	public String hash(byte[] bytes) {
		return DigestUtils.sha256Hex(bytes);
	}
}

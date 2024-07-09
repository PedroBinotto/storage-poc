package com.storage.poc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	private static final String INDEX_HTML = "/index.html";

	@GetMapping(value = "/")
	public String index() {
		return INDEX_HTML;
	}

	@GetMapping(value = "/{dummy:(?!api|assets|oauth2)[a-zA-Z0-9-]+$}/**")
	public String frontendRoute() {
		return INDEX_HTML;
	}

}

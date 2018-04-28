package com.fpamungkas.tutorial08.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

public class PageController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	

}

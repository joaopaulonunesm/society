package com.society.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/admin")
	public String admin() {
		return "admin";
	}
	
	@RequestMapping("/usuario")
	public String usuario() {
		return "usuario";
	}

	@RequestMapping("/moderador")
	public String moderador() {
		return "moderador";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}
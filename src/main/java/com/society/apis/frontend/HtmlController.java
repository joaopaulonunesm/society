package com.society.apis.frontend;

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

	@RequestMapping("/moderator")
	public String moderator() {
		return "moderator";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}
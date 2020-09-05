package io.raut.security.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

	@RequestMapping("/")
	public String show() {
		return "<h1>Hello</h1>";
	}
	
	@RequestMapping("/user")
	public String userInfo() {
		return "<h1>User</h1>";
	}
	
	@RequestMapping("/admin")
	public String adminInfo() {
		return "<h1>admin</h1>";
	}
}

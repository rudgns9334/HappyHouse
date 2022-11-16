package com.mycom.project.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	@GetMapping(value = "/")
	public String home() {
		return "index.html";
	}
	
	@GetMapping(value = "/login")
	public String login() {
		return "/login.html";
	}
	
	@GetMapping(value = "/register")
	public String register() {
		System.out.println("Register");
		return "/register.html";
	}
	
	@GetMapping(value = "/main")
	public String board() {
		return "main.html";
	}
	
	@GetMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "/login.html";
	}
	
	@GetMapping(value = "/seeAll")
	public String seeAll() {
		return "/seeAll.html";
	}
	
	@GetMapping(value = "/notice")
	public String notice() {
		return "/notice.html";
	}
	
	@GetMapping(value = "/mypage")
	public String mypage() {
		return "/mypage.html";
	}
	
	@GetMapping(value = "/event")
	public String event() {
		return "/event.html";
	}
	
	@GetMapping(value = "/review")
	public String review() {
		return "/reviewEvent.html";
	}
	
	@GetMapping(value = "/admin/event")
	public String adminEvent() {
		return "/admin/event.html";
	}
	
	@GetMapping(value = "/properties")
	public String prop(@RequestParam int propNum) {
		String dest = "/properties/prop" +  propNum + ".html";
		System.out.println(dest);
		return dest;
	}
	
	@GetMapping(value = "/properties/prop")
	public String prop() {
		return "/properties/prop.html";
	}
	

}

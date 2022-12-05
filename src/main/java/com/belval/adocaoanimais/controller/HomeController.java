package com.belval.adocaoanimais.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.belval.adocaoanimais.repository.PostagemRepository;

@Controller
public class HomeController {
	@Autowired
	private PostagemRepository repository;

	@GetMapping("/pet/home")
	public String list(Model model) {
		model.addAttribute("p", repository.findAll());
		System.out.println("p é -> "+repository.findAll());
		return "home";
	}
//	@PostMapping("/pet/home")
//	public String list2(Model model) {
//		model.addAttribute("id", 3);
//		model.addAttribute("p", repository.findAll());
//		return "home";
//	}


	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("permissao", 1);
		return "home";
	}
 

	@GetMapping("/pet")
	public String home3(Model model) {
		model.addAttribute("permissao", 2);
		return "home";
	}

}

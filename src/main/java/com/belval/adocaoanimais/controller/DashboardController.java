package com.belval.adocaoanimais.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.belval.adocaoanimais.auxiliar.Menu;
import com.belval.adocaoanimais.model.Adotar;
import com.belval.adocaoanimais.model.Animal;
import com.belval.adocaoanimais.model.Usuario;
import com.belval.adocaoanimais.repository.AdotarRepository;
import com.belval.adocaoanimais.repository.AnimalRepository;
import com.belval.adocaoanimais.repository.UsuarioRepository;

import org.springframework.data.domain.Sort;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class DashboardController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private AnimalRepository animalRepository;
	@Autowired
	private AdotarRepository adotarRepository;

	Menu menu = new Menu();

	@GetMapping("/pet/dashboard")
	
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("dashboard");
		List<Usuario> usuarios = this.usuarioRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		mv.addObject("usuario", usuarios);
		return mv;
	}

	/* ANIMAL */
	@GetMapping("/pet/admin/animal")
	public ModelAndView indexAnimalAll() {
		menu.setTitulo("Todos os anúncios de animais");
		menu.setSelecao("anuncioAll");
		List<Animal> animais = this.animalRepository.findAll();
		ModelAndView mv = new ModelAndView("animal/index");
		mv.addObject("animais", animais);
		mv.addObject("menu", menu);
		return mv;
	}

	/* INTENÇÃO DE ADOTAR */
	@GetMapping("/pet/admin/intencao-adotar")
	public ModelAndView index() {
		menu.setTitulo("Todas as solicitações de adotação");
		menu.setSelecao("intencaoAll");
		List<Adotar> animais = this.adotarRepository.findAll();
		ModelAndView mv = new ModelAndView("intencao/index");
		mv.addObject("animais", animais);
		mv.addObject("menu", menu);
		return mv;
	}

}

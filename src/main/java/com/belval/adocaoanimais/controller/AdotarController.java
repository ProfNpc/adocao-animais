package com.belval.adocaoanimais.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.belval.adocaoanimais.auxiliar.Menu;
import com.belval.adocaoanimais.dto.RequisicaoFormAdotar;
import com.belval.adocaoanimais.model.Adotar;
import com.belval.adocaoanimais.model.Animal;
import com.belval.adocaoanimais.model.PetImagem;
import com.belval.adocaoanimais.repository.AdotarRepository;
import com.belval.adocaoanimais.repository.AnimalRepository;
import com.belval.adocaoanimais.repository.PetImagemRepository;

@Controller
@RequestMapping(value = "/pet/private/intencao-adotar")
public class AdotarController {
	@Autowired
	private AdotarRepository adotarRepository;
	@Autowired
	private AnimalRepository animalRepository;
	 
	@Autowired
	private PetImagemRepository petImagemRepository;

	Menu menu = new Menu();
	@GetMapping("")
	public ModelAndView index() {
		
		menu.setTitulo("Minhas solicitações de adotação");
		menu.setSelecao("intencao");
		List<Adotar> animais = this.adotarRepository.findAll();
		ModelAndView mv = new ModelAndView("intencao/index");
		mv.addObject("animais", animais);
		mv.addObject("menu", menu);
		return mv;
	}

	@GetMapping("/new/{id}")
	public ModelAndView nnew(@PathVariable Long id,RequisicaoFormAdotar requisicao) {
		List<Animal> animais = this.animalRepository.findAll();
		ModelAndView mv = new ModelAndView("intencao/new");
		mv.addObject("petId", id);
		mv.addObject("animais", animais);
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView show(@PathVariable Long id) {
		System.out.println("**** ID: " + id);
		Optional<Adotar> optional= this.adotarRepository.findById(id);
		System.out.println("*************************\n\n\n\n"+optional.get().getId());
		// Optional<Animal> optional = this.animalRepository.findById(optionalAdotar.get().getAnimal().getId());
		// System.out.println("*************************\n\n\n"+optional.get());
		if (optional.isPresent()) {
			Animal animal = optional.get().getAnimal();
			ModelAndView mv = new ModelAndView("intencao/show");
			mv.addObject(optional.get());
			mv.addObject(animal);
			mv.addObject(animal);
			List<PetImagem> petImagem = this.petImagemRepository.findByAnimal(animal);
			mv.addObject("petImagem", petImagem);
			return mv;
		} else {
			System.out.println("$$$$$$$$$$$ Não achou animal");
			return null;
		}
	}

	@PostMapping("/{id}")
	public ModelAndView create(@PathVariable Long id,@Valid RequisicaoFormAdotar requisicao, BindingResult bindingResult) {
		System.out.println("Salvando");
		//bindingResult.addError(null);
		if (bindingResult.hasErrors()) {
			System.out.println("\n************************TEM ERROS**********************\n");
			System.out.println("ERRO \n\n" + bindingResult + "\n\n");
			ModelAndView mv = new ModelAndView("intencao/new");
			return mv;
		} else {
			Adotar adotar = requisicao.toAdotar();
			adotar.setAtivo(true);
			adotar.setUserId((long) 1);
			// adotar.setAnimalId((long) 2);  
			Optional<Animal> optionalAnimal = animalRepository.findById(id);
			adotar.setAnimal(optionalAnimal.get());
			this.adotarRepository.save(adotar);
			System.out.println("salvo");
			return new ModelAndView("redirect:/pet/private/intencao-adotar");
		}

		// return new ModelAndView("redirect:/pet/home" + animal.getId());

	}

}

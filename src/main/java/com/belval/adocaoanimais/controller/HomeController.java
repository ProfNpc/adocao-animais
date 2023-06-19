package com.belval.adocaoanimais.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.belval.adocaoanimais.enums.Permissao;
import com.belval.adocaoanimais.model.Animal;
import com.belval.adocaoanimais.model.PetImagem;
import com.belval.adocaoanimais.model.Usuario;
import com.belval.adocaoanimais.repository.AnimalRepository;
import com.belval.adocaoanimais.repository.CorRepository;
import com.belval.adocaoanimais.repository.PetImagemRepository;
import com.belval.adocaoanimais.repository.PostagemRepository;
import com.belval.adocaoanimais.repository.RacaRepository;
import com.belval.adocaoanimais.repository.UsuarioRepository;

@Controller
public class HomeController {
	@Autowired
	private PostagemRepository postagemRepository;

	@Autowired
	private AnimalRepository animalRepository;
	@Autowired
	private RacaRepository racaRepository;
	@Autowired
	private CorRepository corRepository;
	@Autowired
	private PetImagemRepository petImagemRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/pet/home")
//	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public ModelAndView home(Authentication authentication, Model model, HttpServletRequest request) {
		String nomeUsuario = null;
		try {
			nomeUsuario = authentication.getName();
			Usuario user = usuarioRepository.findByEmail(nomeUsuario);
			System.out.println(user.getNome());
			nomeUsuario=user.getNome();
		} catch (Exception e) {
		}
		ModelAndView mv = new ModelAndView("home");
		model.addAttribute("postagem", postagemRepository.findAllAtivas());
		model.addAttribute("request", request);
		mv.addObject("nomeUsuario", nomeUsuario);
		model.addAttribute("postagem", postagemRepository.findAllAtivas());
		System.out.println("p é -> " + postagemRepository.findAllAtivas());
		
//		Usuario usuario2 = new Usuario();
//		List<Usuario> usuarios = this.usuarioRepository.findAll();
//		ArrayList<Usuario> usuarios2 = new ArrayList<>();
//		for (Usuario usuario : usuarios) {
//			System.out.println("AQUI VER ::::::::::::::::::::::::::::::: "+usuario.getPermissao());
//			usuario2=usuario;
//			if(usuario.getPermissao() == null) {usuario2.setPermissao(Permissao.USUARIO);}
//			usuarios2.add(usuario2);
//		}
//		
//		for (Usuario usuario : usuarios2) {
//			System.out.println("AQUI VER 2 ::::::::::::::::::::::::::::::: "+usuario.getPermissao());
//		}
//		System.out.println("AQUI VER ::::::::::::::::::::::::::::::: "+usuarios);
		return mv;

	}
	
	@GetMapping("/")
//	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public ModelAndView home2(Authentication authentication, Model model, HttpServletRequest request) {
		String nomeUsuario = "";
		try {
			nomeUsuario = authentication.getName();
			Usuario user = usuarioRepository.findByEmail(nomeUsuario);
			System.out.println(user.getNome());
			nomeUsuario=user.getNome();
		} catch (Exception e) {
		}
		ModelAndView mv = new ModelAndView("home");
		model.addAttribute("postagem", postagemRepository.findAllAtivas());
		model.addAttribute("request", request);
		mv.addObject("nomeUsuario", nomeUsuario);
		model.addAttribute("postagem", postagemRepository.findAllAtivas());
		System.out.println("p é -> " + postagemRepository.findAllAtivas());
		return mv;

	}

//	@GetMapping("/pet/home")
//	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
//	public String list(Model model) {
//		model.addAttribute("postagem", postagemRepository.findAllAtivas());
//		System.out.println("p é -> " + postagemRepository.findAllAtivas());
//		return "home";
//	}
	 

	@GetMapping("/pet/public/galeria")
	public ModelAndView index() {
		List<Animal> animais = this.animalRepository.findAllAtivas();
		ModelAndView mv = new ModelAndView("galeria/index");
		mv.addObject("animais", animais);
		return mv;
	}

	@GetMapping("/pet/public/galeria/{id}")
	public ModelAndView show(@PathVariable Long id) {
		System.out.println("**** ID: " + id);
		Optional<Animal> optional = this.animalRepository.findById(id);
		if (optional.isPresent()) {
			Animal animal = optional.get();
			ModelAndView mv = new ModelAndView("galeria/show");

			Date dataNascimento = animal.getNascimento(); // obtém o valor de animal.nascimento
			LocalDate localDataNascimento = dataNascimento.toLocalDate();
			LocalDate hoje = LocalDate.now();
			Period periodo = Period.between(localDataNascimento, hoje);
			int anos = periodo.getYears();
			int meses = periodo.getMonths();
			String idadeFormatada = anos + " anos e " + meses + " meses";

			System.out.println("anos: " + idadeFormatada);
			mv.addObject("idadeFormatada", idadeFormatada);
			mv.addObject(animal);
			List<PetImagem> petImagem = this.petImagemRepository.findByAnimal(animal);
			mv.addObject("petImagem", petImagem);
			System.out.println("$$$$$$$$$$$ CERTO");
			return mv;
		} else {
			System.out.println("$$$$$$$$$$$ Não achou animal");
			return index();
		}
	}

	@GetMapping("/pet/fique-por-dentro")
	public String fiquePorDentro(Model model) {
		return "fique-por-dentro";
	}

}

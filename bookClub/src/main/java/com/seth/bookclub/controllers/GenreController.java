package com.seth.bookclub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seth.bookclub.models.Genre;
import com.seth.bookclub.services.GenreService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/genres")
public class GenreController {

	@Autowired
	private GenreService genreService;

	// Create form
	// route: /genres/new
	@GetMapping("/new")
	public String newGenre(@ModelAttribute("newGenre") Genre newGenre, HttpSession session) {
		if (session.getAttribute("name") == null) {

			return "redirect:/";
		}
		return "newGenre.jsp";
	}

	// Create
	// route: /genres/new
	@PostMapping("/new")
	public String create(@Valid @ModelAttribute("newGenre") Genre newGenre, BindingResult result, HttpSession session) {
		if (session.getAttribute("name") == null) {

			return "redirect:/";
		}
		if (result.hasErrors()) {
			return "newGenre.jsp";
		} else {
			genreService.createGenre(newGenre);
			return "redirect:/books";
		}
	}
	
	// Read One
	// route: genres/genreId
	@GetMapping("{genreId}")
	public String readOne(@PathVariable("genreId") Long genreId, Model model, HttpSession session) {
		if (session.getAttribute("name") == null) {

			return "redirect:/";
		}
		Genre genre = genreService.getOneGenre(genreId);
		model.addAttribute(genre);
		return "oneGenre.jsp";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

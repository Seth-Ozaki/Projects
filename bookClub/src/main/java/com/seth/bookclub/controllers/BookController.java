package com.seth.bookclub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.seth.bookclub.models.Book;
import com.seth.bookclub.models.Genre;
import com.seth.bookclub.services.BookService;
import com.seth.bookclub.services.GenreService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private GenreService genreService;

	// Create form
	// route: /books/new
	@GetMapping("/new")
	public String newBook(@ModelAttribute("newBook") Book newBook, HttpSession session) {
		if (session.getAttribute("name") == null) {

			return "redirect:/";
		}
		return "newBook.jsp";
	}

	// Create
	// route: /books/new
	@PostMapping("/new")
	public String create(@Valid @ModelAttribute("newBook") Book newBook, BindingResult result, HttpSession session) {
		if (session.getAttribute("name") == null) {

			return "redirect:/";
		}
		if (result.hasErrors()) {
			return "newBook.jsp";
		} else {
			bookService.createBook(newBook);
			return "redirect:/books";
		}
	}

	// Read all
	// route: /books
	@GetMapping("")
	public String dashboard(HttpSession session, Model model) {
		if (session.getAttribute("name") == null) {

			return "redirect:/";
		}
		List<Book> books = bookService.getAllBooks();
		List<Genre> genres = genreService.getAllGenres();
		model.addAttribute("books", books);
		model.addAttribute("genres", genres);
		return "dashboard.jsp";
	}

	// Read One
	// route: /books/bookId
	@GetMapping("/{bookId}")
	public String readOne(Model model, @PathVariable("bookId") Long bookId, HttpSession session) {
		if (session.getAttribute("name") == null) {

			return "redirect:/";
		}
		Book book = bookService.getOneBook(bookId);
		List<Genre> genres = genreService.getMissingGenres(book);
		model.addAttribute(book);
		model.addAttribute("genres", genres);
		return "oneBook.jsp";
	}
	
	// Add Genre
	// route/books/genre/bookId/genreId
	@PostMapping("/genre/{bookId}")
	public String addGenre(@PathVariable("bookId") Long bookId,
			@RequestParam("genreId")Long genreId,HttpSession session,Model model) {
		if (session.getAttribute("name") == null) {

			return "redirect:/";
		}
		bookService.addGenre(bookId, genreId);
		return "redirect:/books/"+bookId;
	}
	

	// Update Page
	// route: /books/edit/bookId
	@GetMapping("/edit/{bookId}")
	public String editBook(@PathVariable("bookId") Long bookId, Model model, HttpSession session) {
		if (session.getAttribute("name") == null) {

			return "redirect:/";
		}
		Book editBook = bookService.getOneBook(bookId);
		model.addAttribute("editBook", editBook);
		return "editBook.jsp";
	}

	// Update
	// route: /books/edit/bookId
	@PutMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("editBook") Book editedBook, BindingResult result, Model model,
			@PathVariable("id") Long id, HttpSession session) {
		if (session.getAttribute("name") == null) {

			return "redirect:/";
		}
		if (result.hasErrors()) {
			model.addAttribute("editedBook", editedBook);
			return "editBook.jsp";
		} else {
			bookService.updateBook(editedBook);
			return "redirect:/books/{id}";
		}
	}

	// Delete
	// route: /books/delete/bookId
	@DeleteMapping("/delete/{bookId}")
	public String delete(@PathVariable("bookId") Long bookId, RedirectAttributes flash, HttpSession session) {
		if (session.getAttribute("name") == null) {

			return "redirect:/";
		}
		bookService.deleteBook(bookId);
		flash.addFlashAttribute("deleted", "{Object} has been deleted.");
		return "redirect:/books";
	}

}

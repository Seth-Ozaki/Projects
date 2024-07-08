package com.seth.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seth.bookclub.models.Book;
import com.seth.bookclub.models.Genre;
import com.seth.bookclub.repositories.BookRepository;

@Service
public class BookService {
	// Member variable that contains all our CRUD tools
		private final BookRepository bookRepo;
		
		public BookService(BookRepository bR) {
			this.bookRepo = bR;
		}
		
		@Autowired
		private GenreService genreService;
		
		// CRUD----------
		
		// Create
		public Book createBook(Book b) {
			return bookRepo.save(b);
		}
		
		// Read All
		public List<Book> getAllBooks() {
			return bookRepo.findAll();
		}
		
		// Read One
		public Book getOneBook(Long id) {
			Optional<Book> optBook = bookRepo.findById(id);
			if (optBook.isPresent()) {
				return optBook.get();
			} else {
				return null;
			}
		}
		
		// Update
		public Book updateBook(Book b) {
			return bookRepo.save(b);
		}
		
		// Delete
		public void deleteBook(Long id) {
			bookRepo.deleteById(id);
		}
		
		// Add Genre
		public void addGenre(Long bookId, Long genreId) {
			Book thisBook = this.getOneBook(bookId);
			Genre thisGenre = genreService.getOneGenre(genreId);
			if(thisBook.getGenres().contains(thisGenre)) {
				return;
			}
			thisBook.getGenres().add(thisGenre);
			this.updateBook(thisBook);
			
		}
		
		// RemoceGenre
		public void removeGenre(Long bookId, Long genreId) {
			Book thisBook = this.getOneBook(bookId);
			Genre thisGenre = genreService.getOneGenre(genreId);
			thisBook.getGenres().remove(thisGenre);
			this.updateBook(thisBook);
		}
}
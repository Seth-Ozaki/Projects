package com.seth.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.seth.bookclub.models.Book;
import com.seth.bookclub.models.Genre;
import com.seth.bookclub.repositories.GenreRepository;

@Service
public class GenreService {
	// Member variable that contains all our CRUD tools
			private final GenreRepository genreRepo;
			
			public GenreService(GenreRepository gR) {
				this.genreRepo = gR;
			}
			// Create
			public Genre createGenre(Genre g) {
				return genreRepo.save(g);
			}
			
			// Read One
			public Genre getOneGenre(Long id) {
				Optional<Genre> optGenre = genreRepo.findById(id);
				if(optGenre.isPresent()) {
					return optGenre.get();
				}else {
					return null;
				}
			}
			
			// Read All
			public List<Genre> getAllGenres(){
				return genreRepo.findAll();
			}
			
			// Read all genres book doesnt have
			public List<Genre> getMissingGenres(Book book){
				return genreRepo.findByGenreBooksNotContains(book);
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
}

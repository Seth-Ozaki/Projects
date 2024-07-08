package com.seth.bookclub.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.seth.bookclub.models.Book;
import com.seth.bookclub.models.Genre;

public interface GenreRepository extends CrudRepository<Genre, Long> {
	List<Genre> findAll();
	List<Genre> findByGenreBooksNotContains(Book book);
}

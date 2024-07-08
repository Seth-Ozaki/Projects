package com.seth.bookclub.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="genres")
public class Genre {
	// Member variables
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		@NotNull
		@Size(min = 1, max = 200, message = "There must be a genre.")
		private String genre;
		
		// M : M
		@ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(
				name = "books_genres",
				joinColumns = @JoinColumn(name = "genre_id"),
				inverseJoinColumns = @JoinColumn(name = "book_id")
				)
		private List<Book> genreBooks;
		
		@Column(updatable = false)
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date createdAt;
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date updatedAt;
		
		// Constructors
		public Genre() {
		}

		public Genre(String genre,
				List<Book> genreBooks) {
			this.genre = genre;
			this.genreBooks = genreBooks;
		}
		
		
		// Methods
		@PrePersist // run on create
		protected void onCreate() {
			this.createdAt = new Date();
		}

		@PreUpdate // run on update
		protected void onUpdate() {
			this.updatedAt = new Date();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getGenre() {
			return genre;
		}

		public void setGenre(String genre) {
			this.genre = genre;
		}

		public List<Book> getGenreBooks() {
			return genreBooks;
		}

		public void setGenreBooks(List<Book> genreBooks) {
			this.genreBooks = genreBooks;
		}

		public Date getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}

		public Date getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}

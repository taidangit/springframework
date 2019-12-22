package com.ebookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebookstore.domain.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	List<Book> findByTitleContaining(String keyword); //from Book where title like '%keyword%'
	
	List<Book> findByCategoryContaining(String category);
}

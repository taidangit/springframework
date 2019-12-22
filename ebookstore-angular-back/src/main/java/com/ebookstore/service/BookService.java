package com.ebookstore.service;

import java.util.List;

import com.ebookstore.domain.Book;

public interface BookService {

	List<Book> findAll();
	
	void save(Book book);

	Book findById(int bookId);
	
	void deleteById(int bookId);
	
	List<Book> blurrySearch(String keyword);
	
	List<Book> searchByCategory(String category);
}

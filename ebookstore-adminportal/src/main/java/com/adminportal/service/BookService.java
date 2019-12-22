package com.adminportal.service;

import java.util.List;

import com.adminportal.domain.Book;

public interface BookService {

	List<Book> findAll();
	
	void save(Book book);

	Book findById(int bookId);
	
	void deleteById(int bookId);
}

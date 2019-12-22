package com.adminportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.domain.Book;
import com.adminportal.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	@Override
	public void save(Book book) {
		bookRepository.save(book);
	}

	@Override
	public Book findById(int bookId) {
		return bookRepository.findById(bookId).get();
	}

	@Override
	public void deleteById(int bookId) {
		bookRepository.deleteById(bookId);;
	}
	
	

}

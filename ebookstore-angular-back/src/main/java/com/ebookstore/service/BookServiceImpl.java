package com.ebookstore.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebookstore.domain.Book;
import com.ebookstore.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> findAll() {
		List<Book> books = bookRepository.findAll();
		List<Book> activeBooks = new ArrayList<Book>();
		for(Book book : books) {
			if(book.isActive()) {
				activeBooks.add(book);
			}
		}
		return activeBooks;
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
		bookRepository.deleteById(bookId);
	}

	@Override
	public List<Book> blurrySearch(String keyword) {
		List<Book> books = bookRepository.findByTitleContaining(keyword);
		
		List<Book> activeBooks = new ArrayList<Book>();
		for(Book book : books) {
			if(book.isActive()) {
				activeBooks.add(book);
			}
		}
		return activeBooks;
	}

	@Override
	public List<Book> searchByCategory(String category) {
		List<Book> books = bookRepository.findByCategoryContaining(category);
		
		List<Book> activeBooks = new ArrayList<Book>();
		for(Book book : books) {
			if(book.isActive()) {
				activeBooks.add(book);
			}
		}
		return activeBooks;
	}
	
	

}

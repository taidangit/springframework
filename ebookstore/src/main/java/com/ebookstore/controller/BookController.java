package com.ebookstore.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ebookstore.domain.Book;
import com.ebookstore.domain.Category;
import com.ebookstore.domain.User;
import com.ebookstore.service.BookService;
import com.ebookstore.service.CategoryService;
import com.ebookstore.service.UserService;
import com.ebookstore.utility.MessageConstants;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/bookshelf")
	public String bookshelf(Principal principal, Model model) {
		List<Book> books = bookService.findAll();
		if(books.size() == 0) {
			model.addAttribute("emptyList", true);
		}
		
		List<Category> categories = categoryService.findAll();
		User user = userService.findByUsername(principal.getName());
		
		model.addAttribute("user", user);
		model.addAttribute("categories", categories);
		model.addAttribute("books", books);
		model.addAttribute("classActiveAll", true);
		
		return "bookshelf";
	}
	
	@GetMapping("/bookDetail/{id}")
	public String bookDetail(@PathVariable("id") int bookId, Model model, Principal principal,  HttpSession session) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);	
		
		if(session.getAttribute(MessageConstants.ADD_BOOK_SUCCESS) != null) {
			model.addAttribute(MessageConstants.ADD_BOOK_SUCCESS, session.getAttribute(MessageConstants.ADD_BOOK_SUCCESS));
			session.removeAttribute(MessageConstants.ADD_BOOK_SUCCESS);
		}
		
		if(session.getAttribute(MessageConstants.NOT_ENOUGH_STOCK) != null) {
			model.addAttribute(MessageConstants.NOT_ENOUGH_STOCK, session.getAttribute(MessageConstants.NOT_ENOUGH_STOCK));
			session.removeAttribute(MessageConstants.NOT_ENOUGH_STOCK);
		}
		
		Book book = bookService.findById(bookId);
		model.addAttribute("book", book);
				
		List<Integer> qtyList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		model.addAttribute("qtyList", qtyList);
		
		return "bookDetail";
	}
	
//	@GetMapping("/searchByCategory/{id}")
//	public String searchByCategory(@PathVariable("id") int categoryId, Model model, Principal principal) {
//		User user=userService.findByUsername(principal.getName());
//		model.addAttribute("user", user);
//		
//		Category category = categoryService.findById(categoryId);
//		
//		List<Book> books = category.getBooks();
//		if(books.size() == 0) {
//			List<Category> categories = categoryService.findAll();
//			
//			model.addAttribute("categories", categories);
//			model.addAttribute("emptyList", true);
//			return "bookshelf";
//		}
//		
//		List<Book> activeBooks = new ArrayList<Book>();
//		for(Book book : books) {
//			if(book.isActive()) {
//				activeBooks.add(book);
//			}
//		}
//		
//		List<Category> categories = categoryService.findAll();
//		
//		model.addAttribute("categories", categories);
//		model.addAttribute("books", activeBooks);
//		
//		return "bookshelf";
//	}
	
	@GetMapping("/searchByCategory")
	@ResponseBody
	public String searchByCategory(@RequestParam int categoryId) {

		String htmlResult="";
		
		Category category = categoryService.findById(categoryId);
		List<Book> books;
		if(category == null) {
			books = bookService.findAll();
		} else {
			books = category.getBooks();
		}
		
		if(books.size() == 0) {
			return "emptyList";
		}
		
		List<Book> activeBooks = new ArrayList<Book>();
		for(Book book : books) {
			if(book.isActive()) {
				activeBooks.add(book);
			}
		}
		
		for(Book book : activeBooks) {
			htmlResult += "<tr>";
			htmlResult +="<td><div class='row' style='margin-bottom: 50px;'> <div class='col-sm-3 col-md-3'> <a href='/book/bookDetail/"+book.getBookId()+"'><img class='img-fluid' src='http://localhost:8082/adminportal/image/books/"+book.getBookId()+".png' /></div> <div class='col-sm-9 col-md-9'> <a href='/book/bookDetail/"+book.getBookId()+"'><span>"+book.getTitle()+"</span> </a> <br/><span>"+book.getPublicationDate()+"</span> <p>by <span>"+book.getAuthor()+"</span></p><a href='/book/bookDetail/"+book.getBookId()+"'> <span>"+book.getFormat()+"</span> </a> <p><span>"+book.getNumberOfPages()+"</span> pages</p> <a href='/book/bookDetail/"+book.getBookId()+"'> <span style='font-size:x-large;color:#db3208;'>$<span>"+book.getOurPrice()+"</span></span></a> <span style='text-decoration: line-through;'>$<span>"+book.getListPrice()+"</span></span><p>"+book.getDescription().substring(0, 200)+"...</p></div> </div></td>";
			htmlResult += "</tr>";
		}
		
		return htmlResult;
	}
	
//	@PostMapping("/searchBook")
//	public String searchBook(@RequestParam("keyword") String keyword, Principal principal, Model model) {
//		User user = userService.findByUsername(principal.getName());
//		model.addAttribute("user", user);
//		
//		List<Book> books = bookService.blurrySearch(keyword.trim());
//		if(books.size() == 0) {
//			model.addAttribute("emptyList", true);
//			List<Category> categories = categoryService.findAll();
//			model.addAttribute("categories", categories);
//			
//			return "bookshelf";
//		}
//		List<Book> activeBooks = new ArrayList<Book>();
//
//		for(Book book : books) {
//			if(book.isActive()) {
//				activeBooks.add(book);
//			}
//		}
//		model.addAttribute("books", activeBooks);
//		
//		
//		List<Category> categories = categoryService.findAll();
//		model.addAttribute("categories", categories);
//		
//		return "bookshelf";
//		
//	}
	
	@GetMapping("/searchBook")
	@ResponseBody
	public String searchBook(@RequestParam("keyword") String keyword) {
		String htmlResult="";
		List<Book> books = bookService.blurrySearch(keyword.trim());
		if(books.size() == 0) {
			return "emptyList";
		}
		List<Book> activeBooks = new ArrayList<Book>();

		for(Book book : books) {
			if(book.isActive()) {
				activeBooks.add(book);
			}
		}
		for(Book book : activeBooks) {
			htmlResult += "<tr>";
			htmlResult +="<td><div class='row' style='margin-bottom: 50px;'> <div class='col-sm-3 col-md-3'> <a href='/book/bookDetail/"+book.getBookId()+"'><img class='img-fluid' src='http://localhost:8082/adminportal/image/books/"+book.getBookId()+".png' /></div> <div class='col-sm-9 col-md-9'> <a href='/book/bookDetail/"+book.getBookId()+"'><span>"+book.getTitle()+"</span> </a> <br/><span>"+book.getPublicationDate()+"</span> <p>by <span>"+book.getAuthor()+"</span></p><a href='/book/bookDetail/"+book.getBookId()+"'> <span>"+book.getFormat()+"</span> </a> <p><span>"+book.getNumberOfPages()+"</span> pages</p> <a href='/book/bookDetail/"+book.getBookId()+"'> <span style='font-size:x-large;color:#db3208;'>$<span>"+book.getOurPrice()+"</span></span></a> <span style='text-decoration: line-through;'>$<span>"+book.getListPrice()+"</span></span><p>"+book.getDescription().substring(0, 200)+"...</p></div> </div></td>";
			htmlResult += "</tr>";
		}
		
		return htmlResult;
		
	}
}

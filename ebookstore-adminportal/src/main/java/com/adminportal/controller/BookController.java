package com.adminportal.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.adminportal.domain.Book;
import com.adminportal.domain.Category;
import com.adminportal.service.BookService;
import com.adminportal.service.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/list")
	public String bookList(Model model) {
		List<Book> books = bookService.findAll();
		model.addAttribute("books", books);
		
		return "bookList";
	}
	
	@GetMapping("/add")
	public String addBook(Model model) {
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		model.addAttribute("book", new Book());
		model.addAttribute("titlePage", "New Book Information");
		model.addAttribute("addBook", true);
		
		return "addBook";
	}
	
	@GetMapping("/view/{id}")
	public String bookInfo(@PathVariable("id") int bookId, Model model) {
		Book book = bookService.findById(bookId);
		model.addAttribute("book", book);
		model.addAttribute("titlePage", "Book Info");
		
		return "bookInfo";
	}
	
	@GetMapping("/edit/{id}")
	public String editBook(@PathVariable("id") int bookId, Model model) {
		Book book = bookService.findById(bookId);
		List<Category> categories = categoryService.findAll();
		
		model.addAttribute("categories", categories);
		model.addAttribute("book", book);
		model.addAttribute("titlePage", "Update Book Information");
		model.addAttribute("updateBook", true);
		
		return "addBook";
	}
	
	@PostMapping("/save")
	public String saveBook(@Valid @ModelAttribute("book") Book book, BindingResult result,
			Model model, HttpServletRequest request) {
		if(result.hasErrors()) {
			List<Category> categories = categoryService.findAll();
			model.addAttribute("categories", categories);
			if(book.getBookId() != 0) {
				model.addAttribute("titlePage", "Update Book Information");
				model.addAttribute("updateBook", true);
			} else {
				model.addAttribute("titlePage", "New Book Information");
				model.addAttribute("addBook", true);
			}
			
			return "addBook";
		}
		
		bookService.save(book);
		
		MultipartFile bookImage = book.getImage();
		
		if(bookImage != null && !bookImage.isEmpty()) {
			try {
				byte[] bytes = bookImage.getBytes();
				String name = book.getBookId()+".png";
				
				BufferedOutputStream stream =
						new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/books/"+name)));
				stream.write(bytes);
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		return "redirect:/book/list";
	}
	
//	@GetMapping("/delete/{id}")
//	public String deleteBook(@PathVariable("id") int bookId, Model model) {
//		try {
//			Files.delete(Paths.get("src/main/resources/static/image/books/"+bookId+".png"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		bookService.deleteById(bookId);
//		
//		return "redirect:/book/list";
//	}
	
	@GetMapping("/delete")
	@ResponseBody
	public String deleteBook(@RequestParam("bookId") int bookId, Model model) {
		try {
			if(Files.exists(Paths.get("src/main/resources/static/image/books/"+bookId+".png"))) {
				Files.delete(Paths.get("src/main/resources/static/image/books/"+bookId+".png"));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		bookService.deleteById(bookId);
		
		return "deleteSuccess";
	}
	
	@GetMapping("/deleteAll")
	@ResponseBody
	public String removeList(@RequestParam String bookIdListJson, Model model){
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode bookIdList;
		try {
			bookIdList = objectMapper.readTree(bookIdListJson);
			for (JsonNode bookId : bookIdList) {
				try {
					if(Files.exists(Paths.get("src/main/resources/static/image/books/"+bookId.asInt()+".png"))) {
						Files.delete(Paths.get("src/main/resources/static/image/books/"+bookId.asInt()+".png"));
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				bookService.deleteById(bookId.asInt());
			}
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return "deleteSuccess";
	}
	
	
}

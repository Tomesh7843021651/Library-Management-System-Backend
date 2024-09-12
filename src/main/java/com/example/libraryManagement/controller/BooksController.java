package com.example.libraryManagement.controller;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.libraryManagement.model.Books;
import com.example.libraryManagement.projection.BooksUI;
import com.example.libraryManagement.repository.BooksRepo;

@RestController
@CrossOrigin
@RequestMapping("books")
public class BooksController {
	
	@Autowired
	BooksRepo booksRepo;
	
	@RequestMapping("getAllBooks{userid}")
	public List<BooksUI> getAllBooks(@PathVariable int userid){
		List<BooksUI> books = booksRepo.getCatNameById(userid);
		System.out.println(books);
		return books;
	}
	
	@RequestMapping("AddNewBooks")
	public Books addnewbook(@RequestBody Books book) {
		book.setDate(new Date());
		return booksRepo.save(book);
	}

}

package com.example.libraryManagement.controller;

import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.libraryManagement.model.Category;
import com.example.libraryManagement.projection.CategoryUI;
import com.example.libraryManagement.repository.CategoryRepo;



@RestController
@CrossOrigin
@RequestMapping("admin")

public class AdminController {
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@RequestMapping("getAllCategories")
	public List<CategoryUI> getAll(){
		List<CategoryUI> lists = categoryRepo.GetAllCategoryByUserid();
		System.out.println(lists);
		return categoryRepo.GetAllCategoryByUserid();
	}
	
	@RequestMapping("addNewCategory{userid}")
	public Category addNewCategory(@PathVariable int userid, @RequestBody String name) {
		Category c = new Category();
		c.setDate(new Date());
		c.setName(name);
		c.setUserid(userid);
		return categoryRepo.save(c);}

}

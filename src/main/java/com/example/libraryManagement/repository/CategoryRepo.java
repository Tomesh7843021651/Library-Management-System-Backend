package com.example.libraryManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.libraryManagement.model.Category;
import com.example.libraryManagement.projection.CategoryUI;

public interface CategoryRepo extends JpaRepository<Category, Integer>{
	
	
	@Query(value = "select c.id,c.name,u.name as addedBy from libraryManagement.category c join libraryManagement.user u  on c.userid=u.id ", nativeQuery = true)
	List<CategoryUI> GetAllCategoryByUserid();

}

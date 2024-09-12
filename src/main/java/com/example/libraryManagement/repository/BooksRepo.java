package com.example.libraryManagement.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.libraryManagement.projection.BooksUI;
import com.example.libraryManagement.projection.BooksUIBuyer;
import com.example.libraryManagement.model.Books;

public interface BooksRepo extends JpaRepository<Books, Integer>{
	
	@Query(value = "SELECT b.id,b.name,b.description,b.price,b.quantity,b.rating,b.discount,c.name as catName FROM libraryManagement.books b join libraryManagement.category c on b.categoryid = c.id where b.userid = ?1;", nativeQuery = true)
	List<BooksUI> getCatNameById(int userid);
	
	@Query(value = "SELECT id,b.name,b.price,b.rating,b.discount,b.description,b.quantity, datediff(now(),date) as days FROM libraryManagement.books as b where categoryid=?1 and price >?2 and price < ?3 and rating >= ?4;",nativeQuery = true)
	List<BooksUIBuyer> getProductByFilter(int categoryid, int minprice, int maxprice, int minrating);


}

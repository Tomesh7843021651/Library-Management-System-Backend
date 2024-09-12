package com.example.libraryManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.libraryManagement.model.OrderBooks;
import com.example.libraryManagement.projection.BuyerHistory;

public interface OrderBooksRepo extends JpaRepository<OrderBooks, Integer> {
	
	@Query(value= "SELECT o.id,b.name,b.description,b.price,b.discount,o.quantity,o.amount as total,b.id as productid FROM libraryManagement.order_books as o join libraryManagement.my_orders as m on o.myorderid=m.id join libraryManagement.books as b on b.id=o.productid where m.userid=:userid;", nativeQuery = true)
	List<BuyerHistory> getProductHistory(@Param("userid") int userid);

}

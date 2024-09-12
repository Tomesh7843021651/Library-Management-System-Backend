package com.example.libraryManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.libraryManagement.projection.CartProduct;
import com.example.libraryManagement.model.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer>{
	
	
	int countByProductidAndUserid(int userid, int productid);
	
	@Query(value="select c.id,b.name,b.price,b.quantity,b.description,b.discount,b.rating,b.id as booksid from libraryManagement.books as b join libraryManagement.cart as c on c.productid=b.id where c.userid=:userid", nativeQuery = true)
	List<CartProduct> findCartProductByUserId(@Param("userid") int id);

}

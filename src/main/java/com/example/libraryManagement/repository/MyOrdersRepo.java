package com.example.libraryManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.libraryManagement.model.MyOrders;

public interface MyOrdersRepo extends JpaRepository<MyOrders, Integer>{
	public List<MyOrders> findByUserid(int userid);

}

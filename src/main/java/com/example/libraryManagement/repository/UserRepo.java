package com.example.libraryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.libraryManagement.model.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	public User findByUsername(String username);

	public int countByUsername(String username);

}

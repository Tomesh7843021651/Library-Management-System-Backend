package com.example.libraryManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.libraryManagement.model.LoginReturn;
import com.example.libraryManagement.model.User;
import com.example.libraryManagement.repository.UserRepo;

@RestController
@CrossOrigin
@RequestMapping("login")

public class LoginController {
	@Autowired
	UserRepo userRepo;
	
	
	@RequestMapping("getName{id}")
	public String[] getName(@PathVariable int id) {
		User user = userRepo.findById(id).get();
		String[] sa = new String[1];
		sa[0] = user.getName();
		return sa;
		
	}
	
	@RequestMapping("log")
	public LoginReturn login(@RequestBody String[] sa) {
		
		if(sa == null) {
			return new LoginReturn(-1,false,"currupt Data",-1);
		}
		String Username = sa[0];
		if(Username == null || Username.length()<1) {
			return new LoginReturn(-1,false,"Enter Username",-1);
		}
		String Password = sa[1];
		if(Password == null || Password.length()<1) {
			return new LoginReturn(-1,false,"Enter Password",-1);
		}
		
		int count = userRepo.countByUsername(Username);
		if(count ==0) {
			return new LoginReturn(-1,false,"UserNameWrong",-1);
		}
		
		if(count >1) {
			return new LoginReturn(-1,false,"something is wrong with Username",-1);
		}
		
		User user = userRepo.findByUsername(Username);
		if(user.getPassword().equals(Password)) {
			return new LoginReturn(user.getId(), true, null,user.getAccountType());
		}
		else {
			return new LoginReturn(-1,false,"PasswordWrong",-1);
		}
		
		
	}
	@RequestMapping("register")
	public int login(@RequestBody User user) {
		if(user==null) {
			return 0;
		}
		String username=user.getUsername();
		if (username==null || username.length()<1) {
			return 1;
		}
		String password=user.getPassword();
		if (password==null || password.length()<1) {
			return 2;
		}
		
		int count = userRepo.countByUsername(username);
		
		if (count==0) {
			userRepo.save(user);
			return 4;
		}else {
			return  3;
		}

	}
 
}

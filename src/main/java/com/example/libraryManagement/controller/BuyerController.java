package com.example.libraryManagement.controller;

import java.util.Calendar;
import java.util.Date;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.libraryManagement.model.Books;
import com.example.libraryManagement.model.Cart;
import com.example.libraryManagement.model.MyOrders;
import com.example.libraryManagement.model.OrderBooks;
import com.example.libraryManagement.model.Ratings;
import com.example.libraryManagement.model.Rent;
import com.example.libraryManagement.projection.BooksUIBuyer;
import com.example.libraryManagement.projection.BuyerHistory;
import com.example.libraryManagement.projection.CartProduct;

import com.example.libraryManagement.repository.BooksRepo;
import com.example.libraryManagement.repository.CartRepo;
import com.example.libraryManagement.repository.MyOrdersRepo;
import com.example.libraryManagement.repository.OrderBooksRepo;
import com.example.libraryManagement.repository.RatingRepo;
import com.example.libraryManagement.repository.RentRepo;

@RestController
@CrossOrigin
@RequestMapping("buyer")
public class BuyerController {
	@Autowired
	BooksRepo booksRepo;
	
	@Autowired
	CartRepo cartRepo;
	
	@Autowired
	RatingRepo ratingRepo;
	
	@Autowired
	OrderBooksRepo orderBooksRepo;
	
	@Autowired
	MyOrdersRepo myOrdersRepo;
	
	@Autowired
	RentRepo rentRepo;
	
	@RequestMapping("getBooksByFilter")
	public List<BooksUIBuyer> getBooksByFilter(@RequestBody int[] a){
		return booksRepo.getProductByFilter(a[0], a[1], a[2], a[3]);
	}
	
	@RequestMapping("addToCart{userid}and{productid}")
	public int addToCart(@PathVariable int userid, @PathVariable int productid) {
		try {
			int count = cartRepo.countByProductidAndUserid(userid, productid);
			if(count ==0) {
				Cart cart = new Cart();
				cart.setProductid(productid);
				cart.setUserid(userid);
				cartRepo.save(cart);
				return 1;
				
			}
			else {
				return 0;
			}
		}
		catch(Exception e) {
			e.getStackTrace();
			return 0;
		}
	}
	
	
	
	
	@RequestMapping("getCart/{id}")
	public List<CartProduct> getCartProduct(@PathVariable int id){
		Cart ct = new Cart();
		
		
		return cartRepo.findCartProductByUserId(id);
		
	}
	
	
	@RequestMapping("addRating")
	public int countByUseridAndProductid(@RequestBody int[] a) {
		try {
			int userid=a[0];
			int productid =a[1];
			int stars=a[3];
			System.out.println(a[1]);
			int count = ratingRepo.countByUseridAndProductid(userid, productid);
			
			if(count ==1) {
				Ratings rev = ratingRepo.findByUseridAndProductid(userid, productid);
				rev.setDate(new Date());
				rev.setStars(stars);
				ratingRepo.save(rev);
			}
			else if(count ==0) {
				Ratings r = new Ratings();
				r.setDate(new Date());
				r.setUserid(userid);
				r.setProductid(productid);;
				r.setStars(stars);
				ratingRepo.save(r);
			}
			else {
				return 0;
			}
			
			double avg = ratingRepo.getAvgRatingByProductid(productid);
			Books books = booksRepo.findById(productid).get();
			books.setRating(avg);
			booksRepo.save(books);
			
			return 1;
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	@RequestMapping("placeOrder/{id}")
	public int placeOrder(@PathVariable int id, @RequestBody int[][] a) {
		try {
			MyOrders order = new MyOrders();
			order.setDate(new Date());
			order.setUserid(id);
			myOrdersRepo.save(order);
			
			double totalAmount =0;
			for(int i= 0 ; i< a.length ;i++) {
				int[] a1 = a[i];
				int cartid = a1[0];
				int quantity = a1[1];
				Cart cart = cartRepo.findById(cartid).get();
				
				int productid = cart.getProductid();
				Books books = booksRepo.findById(productid).get();
				OrderBooks obj = new OrderBooks();
				
				double amount = books.getPrice()*quantity;
				amount = amount - (amount*books.getDiscount()/100);
				obj.setAmount(amount);				
				totalAmount += amount;
				
				obj.setDate(new Date());
				obj.setProductid(productid);
				obj.setMyorderid(order.getId());
				obj.setQuantity(quantity);
				
				orderBooksRepo.save(obj);
				
				cartRepo.delete(cart);
				
				
			}
			
			order.setAmount(totalAmount);
			myOrdersRepo.save(order);
			return 1;
			
		}catch(Exception e) {
			e.printStackTrace();
			return 0;			
		}
	}
	
	
	
	@RequestMapping("history/{id}")
	public List<BuyerHistory> historyProduct(@PathVariable int id){
		return orderBooksRepo.getProductHistory(id);
	}
	
	@RequestMapping("renting/{booksid}/{userid}")
	public int renting(@PathVariable int booksid ,@PathVariable int userid) {
		
		//Rent r =rentRepo.findByUseridAndBooksid(userid, booksid);
		
		int count = rentRepo.countByUseridAndBooksid(userid, booksid);
		
		if(count ==1) {
			return 2;
			
		}
		
		if(count == 0) {
			
			Rent rent = new Rent();
			Books book= booksRepo.findById(booksid).get();
		    int amount = 	book.getPrice();
		    
		    amount = (10*amount/100);
		    rent.setAmount(amount);
		    rent.setBooksid(booksid);
		    rent.setUserid(userid);
		    rent.setLatestcurrentDate(new Date());
		    
		    
		    
		    int theDaysThatIWillSet = 10;

	        // Get the current date
	        Date currentDate = new Date();

	        // Create a Calendar instance and set it to the current date
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(currentDate);

	        // Add the specified number of days to the current date
	        calendar.add(Calendar.MONTH, 1);

	        // Get the new date
//	        Date newDate = calendar.getTime();
	        Date dateAfterOneMonth = calendar.getTime();
	        rent.setTillValidDate(dateAfterOneMonth);
	        
	        rent.setIsValid(1);
	        
	        
	        
	        rentRepo.save(rent);
	        
	        List<Rent> listr = rentRepo.findAll();
	        for(int i=0; i<listr.size();i++) {
	        	//System.out.println(listr.get(i).getTillValidDate().compareTo(listr.get(i).getLatestcurrentDate())+"  check the result");
	        	//System.out.println(listr.get(i).getLatestcurrentDate().compareTo(listr.get(i).getTillValidDate())+"check the result");
	        	if(listr.get(i).getTillValidDate().compareTo(listr.get(i).getLatestcurrentDate()) >= 0) {
	            listr.get(i).setIsValid(1);
	        		
	        	}
	        	else {
	        		listr.get(i).setIsValid(0);
	        	}
	        }

	        // Output the new date
	        System.out.println("The new date is: " + dateAfterOneMonth);
		    
		    
//		    rent.setDate(new Date() + )
		    
		    return 1;
			
			
			
		}
		
		else {
			return -1;
			
		}
		
//		return 1;
		
	}
//	RequestMapping

	

}

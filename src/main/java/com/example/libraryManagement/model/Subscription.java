package com.example.libraryManagement.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity@Getter@Setter@NoArgsConstructor@AllArgsConstructor@ToString
public class Subscription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	int id;
	Date date;
	int userid;
	int booksid;
	Date currentdate;
	double amount;

}

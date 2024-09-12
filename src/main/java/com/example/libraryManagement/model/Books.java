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


@Entity@Getter@Setter@ToString@NoArgsConstructor@AllArgsConstructor

public class Books {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	Date date;
	int userid;
	int price;
	int quantity;
	double rating;
	String description;
	double discount;
	int categoryid;
	String uid;

}

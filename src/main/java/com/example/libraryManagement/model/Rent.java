package com.example.libraryManagement.model;

import java.util.Date;

import java.util.Calendar;

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
public class Rent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	private Date tillValidDate;
	private Date LatestcurrentDate;
	double amount;
	int booksid;
	int userid;
	int isValid;
	/*
	 * if valid date > curr date then isvalid =1;
	 * valid date < curr date then isvalid =0;
	 * */
	
	
//	 int theDaysThatIWillSet = 30;

     // Get the current date
//     Date currentDate = new Date();
//
//     // Create a Calendar instance and set it to the current date
//     Calendar cal = Calendar.getInstance();
//     cal.set
//     // Add the specified number of days to the current date
//     calendar.add(Calendar.DAY_OF_MONTH, theDaysThatIWillSet);

     // Get the new date
//     Date newDate = calendar.getTime();
	

}

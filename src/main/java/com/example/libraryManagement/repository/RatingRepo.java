package com.example.libraryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.libraryManagement.model.Ratings;

public interface RatingRepo extends JpaRepository<Ratings, Integer>{
	
	@Query(value="select * from libraryManagement.ratings where userid =?1 and productid =?2;", nativeQuery = true)
	Ratings findByUseridAndProductid(int userid, int productid); 
	
	@Query(value=" select count(*) from libraryManagement.ratings where userid =?1 and productid =?2;", nativeQuery = true)
	int countByUseridAndProductid(int userid,int productid);

	
	@Query(value="select avg(stars) from libraryManagement.ratings where productid = ?1;", nativeQuery = true)
	double getAvgRatingByProductid(int productid);


}

package com.example.libraryManagement.repository;

import org.aspectj.weaver.tools.Trace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.libraryManagement.model.Rent;

public interface RentRepo extends JpaRepository<Rent, Integer> {
	
	public Rent findByUseridAndBooksid(int userid, int booksid);

	
	@Query(value = "SELECT count(*) FROM librarymanagement.rent where rent.userid=?1 and rent.booksid=?2 and rent.is_valid=1;", nativeQuery = true)
	public int countByUseridAndBooksid(int userid, int booksid);

}

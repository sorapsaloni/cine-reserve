package com.example.ticket_booking_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ticket_booking_system.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{
	
	Optional<Movie> findByTitle(String title);
	
	List<Movie> findByGenre(String genre); 
	
	
}

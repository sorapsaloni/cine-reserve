package com.example.ticket_booking_system.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticket_booking_system.dto.MovieRequestDTO;
import com.example.ticket_booking_system.dto.MovieResponseDTO;
import com.example.ticket_booking_system.service.MovieService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movies")
@Validated
public class MovieController {
	
	private final MovieService movieService;
	
	@PostMapping
	public ResponseEntity<MovieResponseDTO> createMovie(@RequestBody MovieRequestDTO movieRequestDTO) {
		return new ResponseEntity<>(movieService.createMovie(movieRequestDTO),HttpStatus.CREATED);
	}
	
	@PostMapping("/bulk")
	public ResponseEntity<List<MovieResponseDTO>> createMovies(
	        @Valid @RequestBody List<MovieRequestDTO> movieRequestDTOs) {

	    List<MovieResponseDTO> responses = movieRequestDTOs.stream()
	            .map(movieService::createMovie)
	            .toList();

	    return new ResponseEntity<>(responses, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<MovieResponseDTO>> getAllMovies() {
		List<MovieResponseDTO> movieResponseDTOs = movieService.getAllMovies();
		return new ResponseEntity<>(movieResponseDTOs,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MovieResponseDTO> getMovieById(@PathVariable @Positive Long id) {
		return new ResponseEntity<>(movieService.getMovieById(id), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MovieResponseDTO> updateMovieById(
			@PathVariable @Positive Long id, 
			@Valid @RequestBody MovieRequestDTO movieRequestDTO) {
		
		return new ResponseEntity<>(movieService.updateMovieById(id, movieRequestDTO),HttpStatus.OK);
	}
	
	@GetMapping("/title/{title}")
	public ResponseEntity<MovieResponseDTO> getByTitle(@PathVariable @Positive String title) {
		MovieResponseDTO movieResponseDTO = movieService.getMovieByTitle(title); 
		return new ResponseEntity<>(movieResponseDTO, HttpStatus.OK); 
	}
	
	@GetMapping("/genre/{genre}")
	public ResponseEntity<List<MovieResponseDTO>> getMovieByGenre(
	        @PathVariable String genre) {
	
	    List<MovieResponseDTO> movieResponseDTOs =
	            movieService.getMovieByGenre(genre);
	
	    return new ResponseEntity<>(movieResponseDTOs, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMovieById(@PathVariable Long id) {
		movieService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	
	
	

}

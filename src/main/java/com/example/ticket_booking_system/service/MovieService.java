package com.example.ticket_booking_system.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.ticket_booking_system.dto.MovieRequestDTO;
import com.example.ticket_booking_system.dto.MovieResponseDTO;
import com.example.ticket_booking_system.entity.Movie;
import com.example.ticket_booking_system.exception.ResourceNotFoundException;
import com.example.ticket_booking_system.repository.MovieRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {
	
	private final ModelMapper modelMapper; 
	
	private final MovieRepository movieRepository; 
	
	public MovieResponseDTO createMovie(MovieRequestDTO movieRequestDTO ) {
		Movie movie = modelMapper.map(movieRequestDTO, Movie.class);
		movie.setMovieStatus(com.example.ticket_booking_system.enums.MovieStatus.ACTIVE);
		Movie savedMovie = movieRepository.save(movie); 
		return modelMapper.map(savedMovie, MovieResponseDTO.class);
	}
	
	//TO RETRIVE ALL THE MOVIES 
	public List<MovieResponseDTO> getAllMovies() {
		List<Movie> movies = movieRepository.findAll();
		List<MovieResponseDTO> movieResponseDTOs = new ArrayList<>();
		for(Movie movie : movies) {
			movieResponseDTOs.add(modelMapper.map(movie, MovieResponseDTO.class));
		}
			return movieResponseDTOs;
	}
		
		
		//using streams
		// return movies.stream().map(movie->modelMapper.map(movie, MovieResponseDTO.class)).toList();
		
	//TO FIND MOVIE USING ID
	public MovieResponseDTO getMovieById(Long id) {

		    Movie movie = movieRepository.findById(id).orElseThrow(
		    		() -> new ResourceNotFoundException("Movie id " + id + " is not found"));

		    return modelMapper.map(movie, MovieResponseDTO.class);
	}
	
	//UPDATING THE MOVIE BY USING ID 
	public MovieResponseDTO updateMovieById(Long id, MovieRequestDTO movieRequestDTO) {
		Movie movie = movieRepository.findById(id).orElseThrow(
	    		() -> new ResourceNotFoundException("Movie id " + id + " is not found"));
		
//		movie.setDescription(movieRequestDTO.getDescription());
//		movie.setCertificate(movieRequestDTO.getCertificate());
//		movie.setDuration(movieRequestDTO.getDuration());
//		movie.setGenre(movieRequestDTO.getGenre());
//		movie.setLanguage(movieRequestDTO.getLanguage());
//		movie.setReleaseDate(movieRequestDTO.getReleaseDate());
//		movie.setTitle(movieRequestDTO.getTitle());
		
		//instead of doing the upper lines there is a better way =>
		modelMapper.map(movieRequestDTO, movie);  // better way 
		
		Movie updatedMovie = movieRepository.save(movie); 

	    return modelMapper.map(updatedMovie, MovieResponseDTO.class);
	}
	
	//FINDING THE MOVIE BY USING TITLE	
	public MovieResponseDTO getMovieByTitle(String title) {
		
		Movie movie = movieRepository.findByTitle(title).orElseThrow(
				()->new ResourceNotFoundException(title+" Movie NOT FOUND")); 
		
		return modelMapper.map(movie, MovieResponseDTO.class); 		
	}
	
	//FINDING THE MOVIE BY USING GENRE
	public List<MovieResponseDTO> getMovieByGenre(String genre) {
	
	    List<Movie> movies = movieRepository.findByGenre(genre);
	
	    return movies.stream()
	            .map(movie -> modelMapper.map(movie, MovieResponseDTO.class))
	            .toList();
	}
	
	//DELETING THE MOVIE BY USING ID
	public void deleteById(Long id) {
		Movie movie = movieRepository.findById(id).orElseThrow(
				()->new ResourceNotFoundException("Movie id: "+id+ " NOT FOUND"));
		
		movieRepository.delete(movie);		
	}

		
		
	
	

}

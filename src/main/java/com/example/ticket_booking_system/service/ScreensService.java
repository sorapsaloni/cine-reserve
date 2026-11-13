package com.example.ticket_booking_system.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.ticket_booking_system.dto.ScreenRequestDTO;
import com.example.ticket_booking_system.dto.ScreenResponseDTO;
import com.example.ticket_booking_system.entity.Screen;
import com.example.ticket_booking_system.entity.Theatre;
import com.example.ticket_booking_system.exception.ResourceNotFoundException;
import com.example.ticket_booking_system.repository.ScreensRepository;
import com.example.ticket_booking_system.repository.TheatreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScreensService {
	
	private final ScreensRepository screensRepository; 
	private final TheatreRepository theatreRepository; 
	private final ModelMapper modelMapper; 
	
	public ScreenResponseDTO addScreen(Long theatreId, ScreenRequestDTO screenRequestDTO) {
		
		Theatre theatre = theatreRepository.findById(theatreId)
				.orElseThrow(() -> new RuntimeException("Theatre Not Found")); 
		
		Screen screen = modelMapper.map(screenRequestDTO, Screen.class);
		screen.setTheatre(theatre);
		
		Screen savedScreen = screensRepository.save(screen);
		
		return modelMapper.map(savedScreen, ScreenResponseDTO.class); 
		
	}
	
	//get all screens 
	
	public List<ScreenResponseDTO> getAllScreens() {
		
		List<Screen> screens = screensRepository.findAll(); 
		
		return screens.stream()
				.map(screen -> modelMapper.map(screen, ScreenResponseDTO.class))
				.toList();
	}
	
	//get screen by id 
	
	public ScreenResponseDTO getScreenById(Long id) {
		
		Screen screen = screensRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Screen not found")); 
		
		return modelMapper.map(screen, ScreenResponseDTO.class); 
				
	}
	
	//update screen 
	
	public ScreenResponseDTO updateScreenById(Long id, ScreenRequestDTO screenRequestDTO) {
		
		Screen screen = screensRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Screen Not Found"));
		
		modelMapper.map(screenRequestDTO, screen); 
		
		Screen updatedScreen = screensRepository.save(screen); 
		
		return modelMapper.map(updatedScreen, ScreenResponseDTO.class); 
	}
	
	
	//delete screen 
	
	public void deleteScreen(Long id) {
		
		Screen screen = screensRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Screen not found"));
		
		screensRepository.delete(screen);		
	}
		

}

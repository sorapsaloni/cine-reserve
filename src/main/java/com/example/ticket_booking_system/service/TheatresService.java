package com.example.ticket_booking_system.service;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.ticket_booking_system.dto.TheatreRequestDTO;
import com.example.ticket_booking_system.dto.TheatreResponseDTO;
import com.example.ticket_booking_system.entity.Theatre;
import com.example.ticket_booking_system.repository.TheatreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TheatresService {
	
	private final TheatreRepository theatreRepository; 
	private final ModelMapper modelMapper; 
	
	public TheatreResponseDTO createTheatre(TheatreRequestDTO theatreRequestDTO) {
		Theatre theatre = modelMapper.map(theatreRequestDTO, Theatre.class); 
		Theatre savedTheatre = theatreRepository.save(theatre); 
		
		return modelMapper.map(savedTheatre, TheatreResponseDTO.class); 
	}
	
	public List<TheatreResponseDTO> getAllTheatres() {

        List<Theatre> theatres = theatreRepository.findAll();

        return theatres.stream()
                .map(theatre -> modelMapper.map(
                        theatre, TheatreResponseDTO.class))
                .toList();
    }

}

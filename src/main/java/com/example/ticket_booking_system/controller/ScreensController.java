package com.example.ticket_booking_system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticket_booking_system.dto.ScreenRequestDTO;
import com.example.ticket_booking_system.dto.ScreenResponseDTO;
import com.example.ticket_booking_system.service.ScreensService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/theatres/{theatreId}/screens")
public class ScreensController {
	
	 private final ScreensService screensService;

	    @PostMapping
	    public ResponseEntity<ScreenResponseDTO> createScreen(
	            @PathVariable @Positive Long theatreId,
	            @Valid @RequestBody ScreenRequestDTO screenRequestDTO) {

	        return new ResponseEntity<>(
	                screensService.addScreen(theatreId, screenRequestDTO),
	                HttpStatus.CREATED);
	    }

}

package com.expo.expoapp.controller;

import com.expo.expoapp.dto.ProfessorDTO;
import com.expo.expoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/professors")
public class ProfessorController {

	private final UserService userService;

	@Autowired
	public ProfessorController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<ProfessorDTO> getProfessorById(@AuthenticationPrincipal String professorId) {
		return ResponseEntity.ok((ProfessorDTO) userService.findById(professorId));
	}

}

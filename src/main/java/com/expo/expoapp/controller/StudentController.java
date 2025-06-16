package com.expo.expoapp.controller;

import com.expo.expoapp.dto.StudentDTO;
import com.expo.expoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	private final UserService userService;

	@Autowired
	public StudentController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<StudentDTO> getStudentById(@AuthenticationPrincipal String studentId) {
		return ResponseEntity.ok(
				(StudentDTO) userService.findById(studentId)
		);
	}

}

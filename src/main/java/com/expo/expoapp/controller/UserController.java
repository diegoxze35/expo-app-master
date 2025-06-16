package com.expo.expoapp.controller;

import com.expo.expoapp.dto.NewUserDTO;
import com.expo.expoapp.request.UserRequest;
import com.expo.expoapp.service.UserService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<NewUserDTO> save(
			@RequestPart("userRequest") UserRequest userRequest,
			@RequestPart("profilePhoto") MultipartFile profilePhoto
	) throws IOException {
		return ResponseEntity.status(HttpStatus.CREATED).body(
				userService.save(userRequest, profilePhoto)
		);
	}

}

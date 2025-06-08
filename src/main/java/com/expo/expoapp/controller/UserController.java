package com.expo.expoapp.controller;

import com.expo.expoapp.model.ExpoUser;
import com.expo.expoapp.request.UserRequest;
import com.expo.expoapp.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<List<ExpoUser>> findALl() {
		return ResponseEntity.ok(userService.findAll());
	}

	@PostMapping/*(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)*/
	public ResponseEntity<ExpoUser> save(
			/*@RequestPart("userRequest")*/@RequestBody UserRequest userRequest/*,*/
			/*@RequestPart("profilePhoto") MultipartFile profilePhoto*/
	) /*throws IOException*/ {
		return ResponseEntity.status(HttpStatus.CREATED).body(
				this.userService.save(userRequest/*, profilePhoto*/)
		);
	}

}

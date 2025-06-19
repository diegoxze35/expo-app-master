package com.expo.expoapp.controller;

import com.expo.expoapp.dto.ProfessorDTO;
import com.expo.expoapp.dto.ProjectDTO;
import com.expo.expoapp.request.ProjectRequest;
import com.expo.expoapp.service.ProjectService;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

	private final ProjectService projectService;

	@Autowired
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}

	@GetMapping
	public ResponseEntity<List<ProjectDTO>> getProjectsByUserId(@AuthenticationPrincipal String userId) {
		return ResponseEntity.ok(projectService.getProjectsByUserId(userId));
	}

	@PostMapping
	public ResponseEntity<ProjectDTO> saveProject(
			@AuthenticationPrincipal String currentStudent,
			@RequestPart("projectRequest") ProjectRequest projectRequest,
			@RequestPart("document") MultipartFile document
	) throws IOException {
		return ResponseEntity.ok(projectService.save(currentStudent, projectRequest, document));
	}

	@GetMapping("/professors")
	public ResponseEntity<Set<ProfessorDTO>> getAvailableProfessors() {
		return ResponseEntity.ok(projectService.getAvailableProfessors());
	}

	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_PDF_VALUE, MediaType.IMAGE_PNG_VALUE})
	public ResponseEntity<byte []> getDocumentById(@PathVariable("id") String id) throws IOException {
		return ResponseEntity.ok(projectService.getDocument(id));
	}

}

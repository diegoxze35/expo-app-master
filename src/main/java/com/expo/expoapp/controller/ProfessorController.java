package com.expo.expoapp.controller;

import com.expo.expoapp.dto.CriterionDTO;
import com.expo.expoapp.dto.EvaluationDTO;
import com.expo.expoapp.dto.ProfessorDTO;
import com.expo.expoapp.dto.ProjectDTO;
import com.expo.expoapp.model.ProjectType;
import com.expo.expoapp.request.CriterionData;
import com.expo.expoapp.request.EvaluationRequest;
import com.expo.expoapp.service.CriterionService;
import com.expo.expoapp.service.ProfessorService;
import com.expo.expoapp.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/professors")
public class ProfessorController {

	private final UserService userService;
	private final CriterionService criterionService;
	private final ProfessorService professorService;

	@Autowired
	public ProfessorController(UserService userService, CriterionService criterionService, ProfessorService professorService) {
		this.userService = userService;
		this.criterionService = criterionService;
		this.professorService = professorService;
	}

	@GetMapping
	public ResponseEntity<ProfessorDTO> getProfessorById(@AuthenticationPrincipal String professorId) {
		return ResponseEntity.ok((ProfessorDTO) userService.findById(professorId));
	}

	@GetMapping("/criteria/{projectType}")
	public ResponseEntity<List<CriterionData>> getCriteriaByProjectType(
			@PathVariable("projectType") ProjectType projectType,
			@AuthenticationPrincipal String professorId
	) {
		return ResponseEntity.ok(criterionService.getByProjectType(projectType, professorId));
	}

	@PostMapping
	public ResponseEntity<CriterionDTO> saveCriteria(
			@RequestBody CriterionDTO request,
			@AuthenticationPrincipal String professorId
	) {
		return ResponseEntity.ok(
				criterionService.save(request.getCriteria(), request.getProjectType(), professorId)
		);
	}

	@GetMapping("/projects/{projectType}")
	public ResponseEntity<List<ProjectDTO>> getProjectsByType(
			@AuthenticationPrincipal String professorId,
			@PathVariable("projectType") ProjectType projectType
	) {
		return ResponseEntity.ok(professorService.findByProjectTypeAndProfessor(projectType, professorId));
	}

	@PostMapping("/evaluate")
	public ResponseEntity<List<EvaluationDTO>> evaluate(@RequestBody EvaluationRequest request) {
		return ResponseEntity.ok(professorService.evaluateProject(
				request.comment(),
				request.projectId(),
				request.evaluateRequests())
		);
	}
}

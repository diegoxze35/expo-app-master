package com.expo.expoapp.controller;

import com.expo.expoapp.model.Project;
import com.expo.expoapp.repository.ProjectRepository;
import com.expo.expoapp.request.ProjectRequest;
import com.expo.expoapp.response.TeamResponse;
import com.expo.expoapp.service.ProjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects")
public class ProjectsController {

	/*
	private final ProjectRepository projectRepository;
	private final ProjectService projectService;

	@Autowired
	public ProjectsController(ProjectRepository projectRepository, ProjectService projectService) {
		this.projectRepository = projectRepository;
		this.projectService = projectService;
	}

	@GetMapping
	public List<TeamResponse> findAll() {
		return projectService.findAll();
	}

	@PostMapping
	public ResponseEntity<TeamResponse> save(@RequestParam String studentId, @RequestBody ProjectRequest project) {
		TeamResponse result = this.projectService.save(studentId, project);
		return result == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(result);
	}

	@GetMapping("/{id}")
	public Project getById(@PathVariable Long id) {
		return projectRepository.findById(id).orElse(null);
	}

	@PutMapping("/{id}")
	public Project updateById(@PathVariable Long id, @RequestBody Project project) {
		project.setTeamId(id);
		return projectRepository.save(project);
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		projectRepository.deleteById(id);
	}
	*/

}

package com.expo.expoapp.service.impl;

import com.expo.expoapp.model.Project;
import com.expo.expoapp.model.Student;
import com.expo.expoapp.repository.StudentRepository;
import com.expo.expoapp.repository.ProjectRepository;
import com.expo.expoapp.request.ProjectRequest;
import com.expo.expoapp.response.TeamResponse;
import com.expo.expoapp.service.ProjectService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

	private final StudentRepository studentRepository;
	private final ProjectRepository projectRepository;

	public ProjectServiceImpl(StudentRepository studentRepository, ProjectRepository projectRepository) {
		this.studentRepository = studentRepository;
		this.projectRepository = projectRepository;
	}

	@Override
	public List<TeamResponse> findAll() {
		return projectRepository.findAll().stream().map(
				(p) -> new TeamResponse(
						p.getTitle(),
						p.getDescription(),
						p.getExpositionDate(),
						p.getTeam().getName(),
						p.getTeam().getMembers().stream().map((e) ->
								e.getName() + " " + e.getSurname()
						).toList()
				)
		).toList();
	}

	@Override
	public TeamResponse save(String studentId, ProjectRequest projectRequest) {
		Optional<Student> studentOptional = studentRepository.findById(studentId);
		if (studentOptional.isEmpty()) {
			return null;
		}
		Student student = studentOptional.get();
		if (student.getTeam() == null) {
			return null;
		}

		Project p = new Project();
		p.setTitle(projectRequest.title());
		p.setDescription(projectRequest.description());
		p.setTeam(student.getTeam());
		p.setExpositionDate(projectRequest.expositionDate());

		Project newTeam = projectRepository.save(p);
		return new TeamResponse(
				newTeam.getTitle(),
				newTeam.getDescription(),
				newTeam.getExpositionDate(),
				newTeam.getTeam().getName(),
				newTeam
						.getTeam()
						.getMembers()
						.stream()
						.map((e) -> e.getName() + " " + e.getSurname())
						.toList()
		);
	}

}

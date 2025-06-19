package com.expo.expoapp.service.impl;

import com.expo.expoapp.dto.EvaluationDTO;
import com.expo.expoapp.dto.ProjectDTO;
import com.expo.expoapp.mapper.ProjectMapper;
import com.expo.expoapp.model.Criterion;
import com.expo.expoapp.model.Professor;
import com.expo.expoapp.model.Project;
import com.expo.expoapp.model.ProjectType;
import com.expo.expoapp.model.State;
import com.expo.expoapp.repository.CriterionRepository;
import com.expo.expoapp.repository.ProjectRepository;
import com.expo.expoapp.repository.UserRepository;
import com.expo.expoapp.request.EvaluateRequest;
import com.expo.expoapp.service.ProfessorService;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorServiceImpl implements ProfessorService {

	private final ProjectRepository projectRepository;
	private final CriterionRepository criterionRepository;
	private final UserRepository userRepository;

	@Autowired
	public ProfessorServiceImpl(ProjectRepository projectRepository, CriterionRepository criterionRepository, UserRepository userRepository) {
		this.projectRepository = projectRepository;
		this.criterionRepository = criterionRepository;
		this.userRepository = userRepository;
	}

	@Override
	public List<EvaluationDTO> evaluateProject(String comment, String projectId, List<EvaluateRequest> evaluateRequests) {
		List<EvaluationDTO> result = new LinkedList<>();
		double evaluationScore = 0.0;
		for (EvaluateRequest e : evaluateRequests) {
			Criterion c = criterionRepository.findById(e.criterionId()).orElseThrow();
			EvaluationDTO evaluationDTO = new EvaluationDTO();
			evaluationDTO.setCriterionName(c.getName());
			evaluationDTO.setCriterionValue(c.getBalancing());
			evaluationDTO.setScore(e.value());
			result.add(evaluationDTO);
			evaluationScore += e.value() * (c.getBalancing() / 100.0);
		}
		Project project = projectRepository.findById(UUID.fromString(projectId)).orElseThrow();
		project.setEvaluation(evaluationScore);
		project.setComment(comment);
		project.setState(State.Evaluated);
		projectRepository.save(project);
		return result;
	}

	@Override
	public List<ProjectDTO> findByProjectTypeAndProfessor(ProjectType projectType, String professorId) {
		Professor p = (Professor) userRepository.findById(professorId).orElseThrow();
		return projectRepository.findByTypeAndProfessor(projectType, p)
				.stream()
				.map(pr -> ProjectMapper.toDTO(pr, false))
				.collect(Collectors.toList());
	}
}

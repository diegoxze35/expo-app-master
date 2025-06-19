package com.expo.expoapp.service;

import com.expo.expoapp.dto.EvaluationDTO;
import com.expo.expoapp.dto.ProjectDTO;
import com.expo.expoapp.model.ProjectType;
import com.expo.expoapp.request.EvaluateRequest;
import java.util.List;

public interface ProfessorService {
	public List<EvaluationDTO> evaluateProject(String comment, String projectId, List<EvaluateRequest> evaluateRequests);
	public List<ProjectDTO> findByProjectTypeAndProfessor(ProjectType projectType, String professorId);
}

package com.expo.expoapp.service;

import com.expo.expoapp.dto.ProfessorDTO;
import com.expo.expoapp.dto.ProjectResponse;
import com.expo.expoapp.request.ProjectRequest;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.springframework.web.multipart.MultipartFile;

public interface ProjectService {
	public List<ProjectResponse> getProjectsByUserId(String userId);
	public ProjectResponse save(String leaderId, ProjectRequest projectRequest, MultipartFile document) throws IOException;
	public Set<ProfessorDTO> getAvailableProfessors();
}

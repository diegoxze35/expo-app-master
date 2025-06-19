package com.expo.expoapp.service;

import com.expo.expoapp.dto.ProfessorDTO;
import com.expo.expoapp.dto.ProjectDTO;
import com.expo.expoapp.request.ProjectRequest;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.springframework.web.multipart.MultipartFile;

public interface ProjectService {
	public List<ProjectDTO> getProjectsByUserId(String userId);
	public ProjectDTO save(String leaderId, ProjectRequest projectRequest, MultipartFile document) throws IOException;
	public Set<ProfessorDTO> getAvailableProfessors();
	public byte [] getDocument(String projectId) throws IOException;
}

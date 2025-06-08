package com.expo.expoapp.service;

import com.expo.expoapp.request.ProjectRequest;
import com.expo.expoapp.response.TeamResponse;
import java.util.List;

public interface ProjectService {
	public List<TeamResponse> findAll();
	public TeamResponse save(String studentId, ProjectRequest projectRequest);
}

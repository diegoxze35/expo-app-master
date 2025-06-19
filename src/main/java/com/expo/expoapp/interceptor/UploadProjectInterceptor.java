package com.expo.expoapp.interceptor;

import com.expo.expoapp.mapper.ProjectMapper;
import com.expo.expoapp.model.ExpoUser;
import com.expo.expoapp.model.Professor;
import com.expo.expoapp.model.Project;
import com.expo.expoapp.model.Student;
import com.expo.expoapp.repository.UserRepository;
import com.expo.expoapp.request.ProjectRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerInterceptor;

@Component("uploadProjectInterceptor")
public class UploadProjectInterceptor implements HandlerInterceptor {

	private final UserRepository userRepository;

	@Autowired
	public UploadProjectInterceptor(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (!request.getMethod().equals("POST"))
			return true;
		String currentUserId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Student currentUser = (Student) userRepository.findById(currentUserId).orElseThrow();
		Project currentProject = currentUser.getTeam().getProject();
		if (currentProject == null)
			return true;
		ObjectMapper mapper = new ObjectMapper();
		ProjectRequest projectRequest = mapper.readValue(
				request.getPart("projectRequest").getInputStream(), ProjectRequest.class
		);
		Optional<ExpoUser> professor = userRepository.findById(projectRequest.professorId());
		if (professor.isEmpty() || !(professor.get() instanceof Professor)) {
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().write(projectRequest.professorId());
			return false;
		}
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		response.getWriter().write(mapper.writeValueAsString(ProjectMapper.toDTO(currentProject, false)));
		return false;
	}
}

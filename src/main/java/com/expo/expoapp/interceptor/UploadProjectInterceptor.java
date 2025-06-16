package com.expo.expoapp.interceptor;

import com.expo.expoapp.mapper.ProjectMapper;
import com.expo.expoapp.model.Project;
import com.expo.expoapp.model.Student;
import com.expo.expoapp.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
		String currentUserId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Student currentUser = (Student) userRepository.findById(currentUserId).orElseThrow();
		Project currentProject = currentUser.getTeam().getProject();
		if (currentProject == null)
			return true;
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		ObjectMapper mapper = new ObjectMapper();
		response.getWriter().write(mapper.writeValueAsString(ProjectMapper.toDTO(currentProject)));
		return false;
	}
}

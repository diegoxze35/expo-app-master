package com.expo.expoapp.mapper;

import com.expo.expoapp.dto.ProfessorDTO;
import com.expo.expoapp.dto.ProjectResponse;
import com.expo.expoapp.model.Project;

public class ProjectMapper {
	public static ProjectResponse toDTO(Project project) {
		//ProjectDTO projectDTO = new ProjectDTO();
		ProjectResponse projectDTO = new ProjectResponse();
		projectDTO.setTitle(project.getTitle());
		projectDTO.setProjectType(project.getType());
		/*projectDTO.setState(project.getState());
		projectDTO.setType(project.getType());*/
		projectDTO.setLesson(project.getLesson());
		projectDTO.setProfessor((ProfessorDTO) ExpoUserMapper.toDTO(project.getProfessor()));
		projectDTO.setDescription(project.getDescription());
		projectDTO.setMembers(TeamMapper.mapMembers(project.getTeam().getMembers()));
		return projectDTO;
	}
}

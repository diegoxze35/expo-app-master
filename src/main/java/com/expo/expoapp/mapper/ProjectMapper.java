package com.expo.expoapp.mapper;

import com.expo.expoapp.dto.ProfessorDTO;
import com.expo.expoapp.dto.ProjectResponse;
import com.expo.expoapp.model.Project;

public class ProjectMapper {
	public static ProjectResponse toDTO(Project project, boolean withMembers) {
		ProjectResponse projectDTO = new ProjectResponse();
		projectDTO.setTitle(project.getTitle());
		projectDTO.setProjectType(project.getType());
		projectDTO.setState(project.getState());
		projectDTO.setLesson(project.getLesson());
		projectDTO.setProfessor((ProfessorDTO) ExpoUserMapper.toDTO(project.getProfessor()));
		projectDTO.setDescription(project.getDescription());
		if (withMembers) {
			projectDTO.setMembers(TeamMapper.mapMembers(project.getTeam().getMembers()));
		}
		return projectDTO;
	}
}

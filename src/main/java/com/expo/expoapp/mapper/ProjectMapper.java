package com.expo.expoapp.mapper;

import com.expo.expoapp.dto.ProfessorDTO;
import com.expo.expoapp.dto.ProjectDTO;
import com.expo.expoapp.model.Project;

public class ProjectMapper {
	public static ProjectDTO toDTO(Project project, final boolean withMembers) {
		ProjectDTO projectDTO = new ProjectDTO();
		projectDTO.setId(project.getProjectId().toString());
		projectDTO.setTitle(project.getTitle());
		projectDTO.setProjectType(project.getType());
		projectDTO.setState(project.getState());
		projectDTO.setLesson(project.getLesson());
		projectDTO.setProfessor((ProfessorDTO) ExpoUserMapper.toDTO(project.getProfessor(), false));
		projectDTO.setDescription(project.getDescription());
		if (withMembers) {
			projectDTO.setMembers(TeamMapper.mapMembers(project.getTeam().getMembers()));
		}
		return projectDTO;
	}
}

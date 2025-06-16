package com.expo.expoapp.dto;

import com.expo.expoapp.model.ProjectType;
import com.expo.expoapp.model.State;
import lombok.Data;

@Data
public class ProjectDTO {
	private String title;
	private String description;
	private State state;
	private ProjectType type;
	private String lesson;
	private ProfessorDTO professor;
	private StudentDTO leader;
}

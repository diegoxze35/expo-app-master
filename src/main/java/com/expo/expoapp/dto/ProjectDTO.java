package com.expo.expoapp.dto;

import com.expo.expoapp.model.ProjectType;
import com.expo.expoapp.model.State;
import java.util.List;
import lombok.Data;

@Data
public class ProjectDTO {
	private String id;
	private String title;
	private String stand;
	private String location;
	private String zone;
	private ProjectType projectType;
	private String lesson;
	private State state;
	private ProfessorDTO professor;
	private String description;
	private List<TeamMemberDTO> members;
}

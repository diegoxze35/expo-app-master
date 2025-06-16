package com.expo.expoapp.dto;

import com.expo.expoapp.model.ProjectType;
import java.util.List;
import lombok.Data;

@Data
public class ProjectResponse {
	private String title;
	private String stand;
	private String location;
	private String zone;
	private ProjectType projectType;
	private String lesson;
	private ProfessorDTO professor;
	private String description;
	private List<TeamMemberDTO> members;
}

package com.expo.expoapp.request;

import com.expo.expoapp.dto.NewMemberDTO;
import com.expo.expoapp.model.ProjectType;
import java.util.List;

public record ProjectRequest(
		String title,
		ProjectType projectType,
		String lesson,
		String professorId,
		String description,
		List<NewMemberDTO> members
) {
}

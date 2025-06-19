package com.expo.expoapp.mapper;

import com.expo.expoapp.dto.CriterionDTO;
import com.expo.expoapp.model.Criterion;
import com.expo.expoapp.model.ProjectType;
import com.expo.expoapp.request.CriterionData;
import java.util.Set;

public class CriterionMapper {
	public static CriterionDTO toDTO(Set<Criterion> data, ProjectType projectType) {
		CriterionDTO criterionDTO = new CriterionDTO();
		criterionDTO.setProjectType(projectType);
		criterionDTO.setCriteria(
				data.stream()
						.map(c -> new CriterionData(
								c.getId(),
								c.getName(),
								c.getBalancing(),
								c.getDescription()
						)).toList()
		);
		return criterionDTO;
	}
}

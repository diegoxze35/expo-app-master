package com.expo.expoapp.service;

import com.expo.expoapp.dto.CriterionDTO;
import com.expo.expoapp.request.CriterionData;
import com.expo.expoapp.model.ProjectType;
import java.util.List;

public interface CriterionService {
	public List<CriterionData> getByProjectType(ProjectType projectType, String professorId);
	public CriterionDTO save(List<CriterionData> criterionData, ProjectType projectType, String professorId);
}

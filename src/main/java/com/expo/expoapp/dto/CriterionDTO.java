package com.expo.expoapp.dto;

import com.expo.expoapp.model.ProjectType;
import com.expo.expoapp.request.CriterionData;
import java.util.List;
import lombok.Data;

@Data
public class CriterionDTO {
	private ProjectType projectType;
	private List<CriterionData> criteria;
}

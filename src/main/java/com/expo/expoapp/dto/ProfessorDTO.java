package com.expo.expoapp.dto;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ProfessorDTO extends ExpoUserDTO {
	private List<ProjectDTO> projects;
}

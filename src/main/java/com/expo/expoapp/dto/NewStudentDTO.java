package com.expo.expoapp.dto;

import com.expo.expoapp.model.Career;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class NewStudentDTO extends NewUserDTO {
	private String groupNumber;
    private Integer semester;
    private Career career;
	private String teamName;
}

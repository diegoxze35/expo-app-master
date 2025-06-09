package com.expo.expoapp.dto;

import com.expo.expoapp.model.Career;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StudentDTO extends ExpoUserDTO {
    private String groupNumber;
    private Integer semester;
    private Career career;
    private TeamDTO team;
}
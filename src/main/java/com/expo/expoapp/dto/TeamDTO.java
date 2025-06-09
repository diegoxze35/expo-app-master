package com.expo.expoapp.dto;

import java.util.List;
import lombok.Data;

@Data
public class TeamDTO {
    private String name;
    private List<TeamMemberDTO> members;
}
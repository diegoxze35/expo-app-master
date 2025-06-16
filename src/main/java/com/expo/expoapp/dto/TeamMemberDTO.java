package com.expo.expoapp.dto;

import lombok.Data;

@Data
public class TeamMemberDTO {
    private String matriculate;
    private String fullName;
    private Boolean isLeader;
}
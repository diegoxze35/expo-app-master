package com.expo.expoapp.dto;

import lombok.Data;

@Data
public abstract class ExpoUserDTO {
    private String matriculate;
    private String email;
    private String fullName;
    private String photoUrl;
}
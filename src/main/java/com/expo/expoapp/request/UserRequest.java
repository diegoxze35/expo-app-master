package com.expo.expoapp.request;

import com.expo.expoapp.model.Career;

public record UserRequest(
    UserType userType,
    String name,
    String email,
    String matriculate,
    String password,
    String group,
    Integer semester,
    Career career,
    String teamName
) {
}

/*
 * {
 *   "userType": "Student",
 *   "name": "Diego Alejandro Moreno Martinez",
 *   "email": "diegoalv1217@gmail.com",
 *   "email": "2022101217",
 *   "password": "12345678",
 *   "group": "5CV2",
 *   "semester": "5",
 *   "career": "ISC",
 *   "teamName": "Sample name"
 * }
 * 
 * {
 *   "userType": "Professor",
 *   "name": "Diego Alejandro Moreno Martinez",
 *   "email": "diegoalv1217@gmail.com",
 *   "email": "2022101217",
 *   "password": "12345678",
 *   "group": null,
 *   "semester": null,
 *   "career": null,
 *   "name": null
 * }
 * 
 */
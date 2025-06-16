package com.expo.expoapp.mapper;

import com.expo.expoapp.dto.NewProfessorDTO;
import com.expo.expoapp.dto.NewStudentDTO;
import com.expo.expoapp.dto.NewUserDTO;
import com.expo.expoapp.model.ExpoUser;
import com.expo.expoapp.model.Student;

public class NewUserMapper {

	public static NewUserDTO toDTO(ExpoUser expoUser, String newTeamName) {
		if (expoUser instanceof Student student) {
			NewStudentDTO newStudentDTO = new NewStudentDTO();
			newStudentDTO.setFullName(expoUser.getName() + " " + expoUser.getSurname());
			newStudentDTO.setEmail(expoUser.getEmail());
			newStudentDTO.setMatriculate(expoUser.getMatriculate());
			newStudentDTO.setGroupNumber(student.getGroupNumber());
			newStudentDTO.setSemester(student.getSemester());
			newStudentDTO.setCareer(student.getCareer());
			newStudentDTO.setTeamName(newTeamName);
			return newStudentDTO;
		} else {
			NewProfessorDTO newProfessorDTO = new NewProfessorDTO();
			newProfessorDTO.setFullName(expoUser.getName() + " " + expoUser.getSurname());
			newProfessorDTO.setEmail(expoUser.getEmail());
			newProfessorDTO.setMatriculate(expoUser.getMatriculate());
			return newProfessorDTO;
		}
	}
}

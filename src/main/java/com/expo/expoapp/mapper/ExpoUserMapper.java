package com.expo.expoapp.mapper;

import com.expo.expoapp.dto.ExpoUserDTO;
import com.expo.expoapp.dto.ProfessorDTO;
import com.expo.expoapp.dto.StudentDTO;
import com.expo.expoapp.model.ExpoUser;
import com.expo.expoapp.model.Professor;
import com.expo.expoapp.model.Student;

public class ExpoUserMapper {
	public static ExpoUserDTO toDTO(ExpoUser expoUser) {
		if (expoUser instanceof Student) {
			return mapStudentToDto((Student) expoUser);
		} else {
			return mapProfessorToDto((Professor) expoUser);
		}
	}

	private static StudentDTO mapStudentToDto(Student student) {
		StudentDTO dto = new StudentDTO();
		mapBaseProperties(student, dto);
		dto.setGroupNumber(student.getGroupNumber());
		dto.setSemester(student.getSemester());
		dto.setCareer(student.getCareer());
		dto.setTeam(TeamMapper.toDTO(student.getTeam()));
		return dto;
	}

	private static ProfessorDTO mapProfessorToDto(Professor professor) {
		ProfessorDTO dto = new ProfessorDTO();
		mapBaseProperties(professor, dto);
		return dto;
	}

	protected static void mapBaseProperties(ExpoUser source, ExpoUserDTO target) {
		target.setMatriculate(source.getMatriculate());
		target.setEmail(source.getEmail());
		target.setFullName(source.getName() + " " + source.getSurname());
	}

}

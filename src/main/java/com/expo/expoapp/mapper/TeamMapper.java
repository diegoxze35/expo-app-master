package com.expo.expoapp.mapper;

import com.expo.expoapp.dto.TeamDTO;
import com.expo.expoapp.dto.TeamMemberDTO;
import com.expo.expoapp.model.Student;
import com.expo.expoapp.model.Team;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class TeamMapper {
	public static TeamDTO toDTO(Team team) {
		if (team == null) return null;

		TeamDTO dto = new TeamDTO();
		dto.setName(team.getName());
		dto.setMembers(mapMembers(team.getMembers()));
		return dto;
	}

	public static List<TeamMemberDTO> mapMembers(Set<Student> members) {
		if (members == null) return Collections.emptyList();

		return members.stream()
				.map(TeamMapper::mapTeamMember)
				.toList();
	}

	private static TeamMemberDTO mapTeamMember(Student student) {
		TeamMemberDTO dto = new TeamMemberDTO();
		dto.setMatriculate(student.getMatriculate());
		dto.setFullName(student.getName() + " " + student.getSurname());
		dto.setIsLeader(student.getIsLeader());
		return dto;
	}
}

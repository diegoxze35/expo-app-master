package com.expo.expoapp.service.impl;

import com.expo.expoapp.dto.TeamDTO;
import com.expo.expoapp.mapper.TeamMapper;
import com.expo.expoapp.model.Student;
import com.expo.expoapp.model.Team;
import com.expo.expoapp.repository.StudentRepository;
import com.expo.expoapp.repository.TeamRepository;
import com.expo.expoapp.service.TeamService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {

	private final TeamRepository teamRepository;
	private final StudentRepository studentRepository;

	@Autowired
	public TeamServiceImpl(TeamRepository teamRepository, StudentRepository studentRepository) {
		this.teamRepository = teamRepository;
		this.studentRepository = studentRepository;
	}

	@Override
	public List<TeamDTO> findAll() {
		return teamRepository.findAll().stream().map(TeamMapper::toDTO).toList();
	}

	@Override
	public TeamDTO save(String teamName, String matriculate) {
		Optional<Student> s = studentRepository.findById(matriculate);
		if (s.isEmpty()) {
			return null;
		}
		Team newTeam = new Team();
		newTeam.setName(teamName);
		Student student = s.get();
		student.setTeam(newTeam);
		studentRepository.save(student);
		return TeamMapper.toDTO(newTeam);
	}
}

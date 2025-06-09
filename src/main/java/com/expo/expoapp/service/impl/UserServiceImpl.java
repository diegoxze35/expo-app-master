package com.expo.expoapp.service.impl;

import com.expo.expoapp.mapper.ExpoUserMapper;
import com.expo.expoapp.dto.ExpoUserDTO;
import com.expo.expoapp.model.Professor;
import com.expo.expoapp.model.Student;
import com.expo.expoapp.model.Team;
import com.expo.expoapp.repository.TeamRepository;
import com.expo.expoapp.repository.UserRepository;
import com.expo.expoapp.request.UserRequest;
import com.expo.expoapp.service.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl implements UserService {

	private final PasswordEncoder encoder;
	private final UserRepository userRepository;
	private final TeamRepository teamRepository;

	@Autowired
	public UserServiceImpl(
			PasswordEncoder encoder,
			UserRepository userRepository,
			TeamRepository teamRepository
	) {
		this.encoder = encoder;
		this.userRepository = userRepository;
		this.teamRepository = teamRepository;
	}

	@Override
	public List<ExpoUserDTO> findAll() {
		return userRepository.findAll().stream().map(ExpoUserMapper::toDTO).toList();
	}

	@Override
	public ExpoUserDTO save(UserRequest user/*, MultipartFile profilePhoto*/) /*throws IOException*/ {
		//Diego Alejandro Moreno Martinez
		String[] fullName = user.name().split(" ");
		String name, surname;
		//[Diego, Alejandro, Moreno, Martinez]
		if (fullName.length == 4) {
			name = fullName[0] + " " + fullName[1];
			surname = fullName[2] + " " + fullName[3];
		} else {
			name = fullName[0];
			surname = fullName[1] + " " + fullName[2];
		}
		switch (user.userType()) {
			case Student:
				Student newStudent = new Student();
				newStudent.setMatriculate(user.matriculate());
				newStudent.setEmail(user.email());
				newStudent.setPassword(encoder.encode(user.password()));
				newStudent.setName(name);
				newStudent.setSurname(surname);
				newStudent.setGroupNumber(user.group());
				newStudent.setSemester(user.semester());
				newStudent.setCareer(user.career());
				//newStudent.setProfilePhoto(profilePhoto.getBytes());
				if (user.teamName() != null) {
					Optional<Team> team = teamRepository.findByName(user.teamName());
					if (team.isPresent()) {
						newStudent.setTeam(team.get());
					} else {
						Team newTeam = new Team();
						newTeam.setName(user.teamName());
						Team e = teamRepository.save(newTeam);
						newStudent.setTeam(e);
					}
				}
				return ExpoUserMapper.toDTO(userRepository.save(newStudent));
			case Professor:
				Professor newProfessor = new Professor();
				newProfessor.setMatriculate(user.matriculate());
				newProfessor.setEmail(user.email());
				newProfessor.setPassword(encoder.encode(user.password()));
				newProfessor.setName(name);
				newProfessor.setSurname(surname);
				//newProfessor.setProfilePhoto(profilePhoto.getBytes());
				return ExpoUserMapper.toDTO(userRepository.save(newProfessor));
		}
		return null;
	}

}

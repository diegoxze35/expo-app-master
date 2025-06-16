package com.expo.expoapp.service.impl;

import com.expo.expoapp.dto.ExpoUserDTO;
import com.expo.expoapp.dto.NewUserDTO;
import com.expo.expoapp.mapper.ExpoUserMapper;
import com.expo.expoapp.mapper.NewUserMapper;
import com.expo.expoapp.model.ExpoUser;
import com.expo.expoapp.model.Professor;
import com.expo.expoapp.model.Student;
import com.expo.expoapp.model.Team;
import com.expo.expoapp.repository.TeamRepository;
import com.expo.expoapp.repository.UserRepository;
import com.expo.expoapp.request.UserRequest;
import com.expo.expoapp.service.UserService;
import com.expo.expoapp.utils.Utils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class UserServiceImpl implements UserService {

	@Value("${file.upload-dir}")
	private String uploadDir;

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
	@Transactional
	public NewUserDTO save(UserRequest user, MultipartFile profilePhoto) throws IOException {
		Path uploadPath = Paths.get(uploadDir + "/images");
		if (!Files.exists(uploadPath))
			Files.createDirectories(uploadPath);
		String fileName = UUID.randomUUID() + "_" + profilePhoto.getOriginalFilename();
		Path filePath = uploadPath.resolve(fileName);
		InputStream inputStream = profilePhoto.getInputStream();
		Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		inputStream.close();
		String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path(uploadDir + "/images/")
				.path(fileName)
				.toUriString();
		/*Check for new user is already in the database*/
		Optional<ExpoUser> u = userRepository.findById(user.matriculate());
		if (u.isPresent()) {
			ExpoUser expoUser = u.get();
			fillNullableFields(expoUser, user, fileUrl);
			return NewUserMapper.toDTO(userRepository.save(expoUser), user.teamName());
		}
		/*Check for new user is already in the database*/
		List<String> nameAndSurname = Utils.parseNameAndSurname(user.name());

		return switch (user.userType()) {
			case Student -> {
				Student newStudent = new Student();
				newStudent.setMatriculate(user.matriculate());
				newStudent.setName(nameAndSurname.get(0));
				newStudent.setSurname(nameAndSurname.get(1));
				fillNullableFields(newStudent, user, fileUrl);
				/*newStudent.setEmail(user.email());
				newStudent.setPassword(encoder.encode(user.password()));
				newStudent.setGroupNumber(user.group());
				newStudent.setSemester(user.semester());
				newStudent.setCareer(user.career());
				newStudent.setPhotoUrl(fileUrl);*/
				if (user.teamName() != null) {
					Optional<Team> team = teamRepository.findByName(user.teamName());
					if (team.isPresent()) {
						Team t = team.get();
						t.setMembers(Set.of(newStudent));
						newStudent.setTeam(t);
					} else {
						newStudent.setIsLeader(true);
						Team newTeam = new Team();
						newTeam.setName(user.teamName());
						newTeam.setMembers(Set.of(newStudent));
						newStudent.setTeam(newTeam);
					}
				}
				yield NewUserMapper.toDTO(userRepository.save(newStudent), user.teamName());
			}
			case Professor -> {
				Professor newProfessor = new Professor();
				newProfessor.setMatriculate(user.matriculate());
				fillNullableFields(newProfessor, user, fileUrl);
				/*newProfessor.setEmail(user.email());
				newProfessor.setPassword(encoder.encode(user.password()));
				newProfessor.setName(nameAndSurname.get(0));
				newProfessor.setSurname(nameAndSurname.get(1));
				newProfessor.setPhotoUrl(fileUrl);*/
				ExpoUser s = userRepository.save(newProfessor);
				yield NewUserMapper.toDTO(s, null);
			}
		};
	}

	private void fillNullableFields(ExpoUser expoUser, UserRequest user, String photoUrl) {
		if (expoUser instanceof Student student) {
			student.setEmail(user.email());
			student.setPassword(encoder.encode(user.password()));
			student.setGroupNumber(user.group());
			student.setSemester(user.semester());
			student.setCareer(user.career());
			student.setPhotoUrl(photoUrl);
		} else if (expoUser instanceof Professor professor) {
			professor.setEmail(user.email());
			professor.setPassword(encoder.encode(user.password()));
			professor.setPhotoUrl(photoUrl);
		} else throw new RuntimeException("Unknown user");
	}

	@Override
	@Transactional(readOnly = true)
	public ExpoUserDTO findById(String id) {
		return ExpoUserMapper.toDTO(userRepository.findById(id).orElseThrow());
	}

}

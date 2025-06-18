package com.expo.expoapp.service.impl;

import com.expo.expoapp.dto.NewMemberDTO;
import com.expo.expoapp.dto.ProfessorDTO;
import com.expo.expoapp.dto.ProjectResponse;
import com.expo.expoapp.mapper.ExpoUserMapper;
import com.expo.expoapp.mapper.ProjectMapper;
import com.expo.expoapp.model.ExpoUser;
import com.expo.expoapp.model.Professor;
import com.expo.expoapp.model.Project;
import com.expo.expoapp.model.State;
import com.expo.expoapp.model.Student;
import com.expo.expoapp.model.Team;
import com.expo.expoapp.repository.TeamRepository;
import com.expo.expoapp.repository.UserRepository;
import com.expo.expoapp.request.ProjectRequest;
import com.expo.expoapp.service.ProjectService;
import com.expo.expoapp.utils.Utils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Value("${file.upload-dir}")
	private String uploadDir;

	private final UserRepository userRepository;
	private final TeamRepository teamRepository;

	@Autowired
	public ProjectServiceImpl(UserRepository userRepository, TeamRepository teamRepository) {
		this.userRepository = userRepository;
		this.teamRepository = teamRepository;
	}

	@Override
	public List<ProjectResponse> getProjectsByUserId(String userId) {
		ExpoUser user = userRepository.findById(userId).orElseThrow();
		if (user instanceof Student s) {
			Team t = s.getTeam();
			Project p;
			return t != null ?
					(p = t.getProject()) != null ?
							List.of(ProjectMapper.toDTO(p, false)) :
							Collections.emptyList() : Collections.emptyList();
		}
		Professor p = (Professor) user;
		return p.getProjects().stream().map(pr -> ProjectMapper.toDTO(pr, true)).toList();
	}

	@Override
	@Transactional
	public ProjectResponse save(String leaderId, ProjectRequest projectRequest, MultipartFile document) throws IOException {
		Student leader = (Student) userRepository.findById(leaderId).orElseThrow();
		Team currentTeam = leader.getTeam();
		Set<Student> currentMembers = currentTeam.getMembers();
		Set<Student> newMembers = new LinkedHashSet<>();
		for (NewMemberDTO member : projectRequest.members()) {
			Student newMember;
			Optional<ExpoUser> user = userRepository.findById(member.matriculate());
			if (user.isPresent()) {
				newMember = (Student) user.get();
			} else {
				newMember = new Student();
				List<String> nameAndSurname = Utils.parseNameAndSurname(member.fullName());
				newMember.setMatriculate(member.matriculate());
				newMember.setName(nameAndSurname.get(0));
				newMember.setSurname(nameAndSurname.get(1));
			}
			newMember.setTeam(currentTeam);
			newMembers.add(newMember);
		}
		currentMembers.addAll(newMembers);
		currentTeam.setMembers(currentMembers);
		Project newProject = new Project();
		newProject.setTitle(projectRequest.title());
		newProject.setDescription(projectRequest.description());
		newProject.setState(State.Pending);
		newProject.setType(projectRequest.projectType());
		newProject.setLesson(projectRequest.lesson());
		Professor professor = (Professor) userRepository.findById(projectRequest.professorId()).orElseThrow();
		Set<Project> professorsProjects = professor.getProjects();
		professorsProjects.add(newProject);
		professor.setProjects(professorsProjects);
		newProject.setProfessor(professor);
		newProject.setTeam(currentTeam);
		currentTeam.setProject(newProject);
		Team updatedTeam = teamRepository.save(currentTeam);
		Project savedProject = updatedTeam.getProject();
		UUID projectId = savedProject.getProjectId();
		Path uploadPath = Paths.get(uploadDir + "/docs");
		if (!Files.exists(uploadPath))
			Files.createDirectories(uploadPath);
		Path filePath = uploadPath.resolve(projectId.toString());
		InputStream inputStream = document.getInputStream();
		Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		inputStream.close();
		return ProjectMapper.toDTO(savedProject, true);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<ProfessorDTO> getAvailableProfessors() {
		return userRepository.findByIsAvailable()
				.stream()
				.map(u -> (ProfessorDTO) ExpoUserMapper.toDTO(u))
				.collect(Collectors.toSet());
	}

}

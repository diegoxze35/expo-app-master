package com.expo.expoapp.repository;

import com.expo.expoapp.model.Professor;
import com.expo.expoapp.model.Project;
import com.expo.expoapp.model.ProjectType;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
	public List<Project> findByTypeAndProfessor(ProjectType type, Professor professor);
}

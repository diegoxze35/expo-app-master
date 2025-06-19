package com.expo.expoapp.repository;

import com.expo.expoapp.model.Criterion;
import com.expo.expoapp.model.Professor;
import com.expo.expoapp.model.ProjectType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CriterionRepository extends JpaRepository<Criterion, Long> {
	public List<Criterion> findByProjectTypeAndProfessor(ProjectType projectType, Professor professor);
}

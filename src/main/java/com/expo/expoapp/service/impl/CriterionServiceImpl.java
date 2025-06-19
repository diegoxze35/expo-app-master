package com.expo.expoapp.service.impl;

import com.expo.expoapp.dto.CriterionDTO;
import com.expo.expoapp.mapper.CriterionMapper;
import com.expo.expoapp.model.Criterion;
import com.expo.expoapp.model.Professor;
import com.expo.expoapp.model.ProjectType;
import com.expo.expoapp.repository.CriterionRepository;
import com.expo.expoapp.repository.UserRepository;
import com.expo.expoapp.request.CriterionData;
import com.expo.expoapp.service.CriterionService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CriterionServiceImpl implements CriterionService {

	private final CriterionRepository criterionRepository;
	private final UserRepository userRepository;

	@Autowired
	public CriterionServiceImpl(CriterionRepository criterionRepository, UserRepository userRepository) {
		this.criterionRepository = criterionRepository;
		this.userRepository = userRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CriterionData> getByProjectType(ProjectType projectType, String professorId) {
		Professor p = (Professor) userRepository.findById(professorId).orElseThrow();
		return criterionRepository.findByProjectTypeAndProfessor(projectType, p)
				.stream()
				.map(c -> new CriterionData(c.getId(), c.getName(), c.getBalancing(), c.getDescription()))
				.toList();
	}

	@Override
	@Transactional
	public CriterionDTO save(List<CriterionData> criterionData, ProjectType projectType, String professorId) {
		Professor professor = (Professor) userRepository.findById(professorId).orElseThrow();
		Set<Criterion> criterionSet = professor.getCriteria();
		if (criterionSet != null) {
			criterionSet.removeIf(c -> c.getProjectType() == projectType);
			professor.setCriteria(criterionSet);
			userRepository.save(professor);
		}
		Set<Criterion> result = new HashSet<>(criterionData.size());
		criterionData.forEach(request -> {
			Criterion criterion = new Criterion();
			criterion.setName(request.name());
			criterion.setBalancing(request.balancing());
			criterion.setDescription(request.description());
			criterion.setProjectType(projectType);
			criterion.setProfessor(professor);
			result.add(criterion);
		});
		professor.setCriteria(result);
		criterionRepository.saveAll(result);
		return CriterionMapper.toDTO(result, projectType);
	}

}

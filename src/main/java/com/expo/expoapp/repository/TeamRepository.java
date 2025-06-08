package com.expo.expoapp.repository;

import com.expo.expoapp.model.Team;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
	public Optional<Team> findByName(String name);
}

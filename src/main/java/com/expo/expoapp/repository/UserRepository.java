package com.expo.expoapp.repository;

import com.expo.expoapp.model.ExpoUser;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<ExpoUser, String> {
	public Optional<ExpoUser> findByEmail(String email);

	@Query("SELECT p FROM Professor p WHERE (SELECT COUNT(pr) FROM Project pr WHERE pr.professor = p) < 5")
	public Set<ExpoUser> findByIsAvailable();
}

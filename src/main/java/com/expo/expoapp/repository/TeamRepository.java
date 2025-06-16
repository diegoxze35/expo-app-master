package com.expo.expoapp.repository;

import com.expo.expoapp.model.Team;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
/*import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;*/

public interface TeamRepository extends JpaRepository<Team, Long> {
	public Optional<Team> findByName(String name);
	/*@Query("SELECT DISTINCT t FROM Team t JOIN t.members m WHERE m.matriculate = :studentId")
    public Optional<Team> findByAnyStudentMatriculate(@Param("studentId") String matriculate);*/
}

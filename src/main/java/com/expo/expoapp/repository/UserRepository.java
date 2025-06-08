package com.expo.expoapp.repository;

import com.expo.expoapp.model.ExpoUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<ExpoUser, String> {
}

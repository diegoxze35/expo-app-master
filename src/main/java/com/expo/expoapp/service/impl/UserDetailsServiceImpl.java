package com.expo.expoapp.service.impl;

import com.expo.expoapp.model.ExpoUser;
import com.expo.expoapp.model.Professor;
import com.expo.expoapp.model.Student;
import com.expo.expoapp.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<ExpoUser> userOptional = userRepository.findById(username);
		if (userOptional.isEmpty()) throw new UsernameNotFoundException("User not found");
		ExpoUser expoUser = userOptional.get();
		List<GrantedAuthority> roles = new ArrayList<>(1);
		if (expoUser instanceof Student)
			roles.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
		else if (expoUser instanceof Professor)
			roles.add(new SimpleGrantedAuthority("ROLE_PROFESSOR"));
		else throw new UsernameNotFoundException("Unknown User");
		return new org.springframework.security.core.userdetails.User(expoUser.getMatriculate(), expoUser.getPassword(), roles);
	}
}

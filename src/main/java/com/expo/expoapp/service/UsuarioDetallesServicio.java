package com.expo.expoapp.service;

import com.expo.expoapp.model.Estudiante;
import com.expo.expoapp.model.Persona;
import com.expo.expoapp.model.Profesor;
import com.expo.expoapp.repository.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioDetallesServicio implements UserDetailsService {

	private final UsuarioRepositorio usuarioRepositorio;

	@Autowired
	public UsuarioDetallesServicio(UsuarioRepositorio usuarioRepositorio) {
		this.usuarioRepositorio = usuarioRepositorio;
	}

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Persona> personaOpcional = usuarioRepositorio.findById(username);
		if (personaOpcional.isEmpty()) throw new UsernameNotFoundException("Usuario no encontrado");
		Persona usuario = personaOpcional.get();
		List<GrantedAuthority> roles = new ArrayList<>(1);
		if (usuario instanceof Estudiante)
			roles.add(new SimpleGrantedAuthority("ROLE_ESTUDIANTE"));
		else if (usuario instanceof Profesor)
			roles.add(new SimpleGrantedAuthority("ROLE_PROFESOR"));
		else throw new UsernameNotFoundException("Tipo de usuario no identificado");
		return new User(usuario.getMatricula(), usuario.getPassword(), roles);
	}
}

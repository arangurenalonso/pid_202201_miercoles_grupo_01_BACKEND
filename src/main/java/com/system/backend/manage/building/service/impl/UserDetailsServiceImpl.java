package com.system.backend.manage.building.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.system.backend.manage.building.entity.Usuario;
import com.system.backend.manage.building.repository.IUsuarioRepository;

import lombok.extern.slf4j.Slf4j;
/*
 * Authentication -> Verifies who you are
 * Authorization -> Decides if you have permission to access a resource
 * */

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private IUsuarioRepository usuarioRepo;
	
	/********************************************************************
	 * We told Spring how to find the user and we returned the spring security user 
	 * that it can take this information and then do the passowrd 
	 * */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepo.findByUsername(username);
		if(usuario==null) {
			log.error("Error en el login: no existe el usuario '"+username+"' en el sistema!");
			throw new UsernameNotFoundException("Error en el login: no existe el usuario '"+username+"' en el sistema!");
		}
		log.info("User found in the database: {}",username);
		List<GrantedAuthority> authorities=usuario.getRoles()
												.stream()
												.map(role->{
													return new SimpleGrantedAuthority(role.getName()); 
												})
												.collect(Collectors.toList());
		
		return new User(usuario.getUsername(),
						usuario.getPassword(), 
						true, 
						true, true, true, authorities);
	}
}

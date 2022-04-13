package com.system.backend.manage.building.service.impl;

import java.util.Date;
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

import com.system.backend.manage.building.constant.SecurityConstant;
import com.system.backend.manage.building.constant.UserImplConstant;
import com.system.backend.manage.building.entity.UserPrincipal;
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
	@Autowired
	private LoginAttemptService loginAttemptService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = usuarioRepo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(UserImplConstant.NO_USER_FOUND_BY_USERNAME + username);
		} else {
			validateLoginAttempt(user);
			user.setLastLoginDateDisplay(user.getLastLoginDate());
			user.setLastLoginDate(new Date());
			usuarioRepo.save(user);
			UserPrincipal userPrincipal = new UserPrincipal(user);
			return new User(userPrincipal.getUsername(), userPrincipal.getPassword(), userPrincipal.isEnabled(),
					userPrincipal.isAccountNonExpired(), userPrincipal.isCredentialsNonExpired(),
					userPrincipal.isAccountNonLocked(), userPrincipal.getAuthorities());
		}
	}

	private void validateLoginAttempt(Usuario user) {
		if (user.isNotLocked()) {
			if (loginAttemptService.hasExceededMaxAttempts(user.getUsername())) {
				System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
				user.setNotLocked(false);
			} else {
				user.setNotLocked(true);
			}
		} else {
			loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
		}
	}
	/*
	 * /******************************************************************** We told
	 * Spring how to find the user and we returned the spring security user that it
	 * can take this information and then do the passowrd *
	 * 
	 * @Override public UserDetails loadUserByUsername(String username) throws
	 * UsernameNotFoundException { System.out.
	 * println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Entramos en UserDetailsService: "
	 * ); Usuario usuario = usuarioRepo.findByUsername(username); if(usuario==null)
	 * { throw new
	 * UsernameNotFoundException("Error en el login: no existe el usuario '"
	 * +username+"' en el sistema!"); }
	 * log.info("User found in the database: {}",username); List<GrantedAuthority>
	 * authorities=usuario.getPermiso() .stream() .map(permiso->{ return new
	 * SimpleGrantedAuthority(permiso.getRole().getName()); })
	 * .collect(Collectors.toList());
	 * 
	 * return new User(usuario.getUsername(), usuario.getPassword(), true, true,
	 * true, true, authorities); }
	 */
}

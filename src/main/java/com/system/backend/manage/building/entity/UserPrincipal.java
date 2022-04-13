package com.system.backend.manage.building.entity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails{
	
	private Usuario user;

	    public UserPrincipal(Usuario user) {
	        this.user = user;
	    }

	    @Override
	    public List<GrantedAuthority> getAuthorities() {
	        return this.user.getPermiso()
					.stream()
					.map(permiso->{
						return new SimpleGrantedAuthority(permiso.getRole().getName()); 
					})
					.collect(Collectors.toList());
	    }
	    
	    @Override
	    public String getPassword() {
	        return this.user.getPassword();
	    }

	    @Override
	    public String getUsername() {
	        return this.user.getUsername();
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return this.user.isNotLocked();
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isEnabled() {
	        return this.user.isActive();
	    }

	    private static final long serialVersionUID = 1L;
		
}

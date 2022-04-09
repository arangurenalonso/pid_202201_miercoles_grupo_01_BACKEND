package com.system.backend.manage.building.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.system.backend.manage.building.security.GenerateToken;
import com.system.backend.manage.building.service.impl.UserDetailsServiceImpl;

public class CustomAuthorizationFilter extends OncePerRequestFilter {
	@Autowired
	private GenerateToken generateToken;

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> doFilterInternal: "+isPathNeedsToBeAuthorized(request));
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+request.getServletPath());
		if (!isPathNeedsToBeAuthorized(request)) {
			filterChain.doFilter(request, response);
			return;
		}
		// obtenemos el token de la solicitud HTTP
		String token = generateToken.getBearerTokend(request);
		 
		// Validamos el token haciendo la Decodificamos el token
		DecodedJWT decodedJWT = generateToken.validarToken(token, response);
		
		// Obtenemos el usuario
		String username = decodedJWT.getSubject();
		UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
				null, userDetails.getAuthorities());
		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		
		// establecemos la seguridad
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);

		filterChain.doFilter(request, response);

	}

	private boolean isPathNeedsToBeAuthorized(HttpServletRequest request) {
		
		if (
				   request.getServletPath().equals("/api/auth/login")
				|| request.getServletPath().equals("/api/auth/token/refresh")
				|| request.getServletPath().equals("/api/persona/list")
				|| request.getServletPath().startsWith("/api/propietario")
				|| request.getServletPath().startsWith("/api/visitante")
				) {
			return false;
		}
			
			
		return true;
	}
	/*
	 * OTRA FORMA DE OBTENER LOS ROLES DESDE EL TOKEN 
	 * String[]roles=decodedJWT.getClaim("roles").asArray(String.class);
	 * //get the roles that comes with the token
	 *  Collection<SimpleGrantedAuthority> authorities=new ArrayList<>(); 
	 *  Arrays.stream(roles).forEach(role->{ authorities.add(new SimpleGrantedAuthority(role)); });
	 */
}

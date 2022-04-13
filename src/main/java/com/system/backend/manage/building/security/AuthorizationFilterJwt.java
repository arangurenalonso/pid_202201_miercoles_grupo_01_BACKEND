package com.system.backend.manage.building.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.google.common.net.HttpHeaders;
import com.system.backend.manage.building.constant.SecurityConstant;
import com.system.backend.manage.building.excepciones.CustomAppException;
import com.system.backend.manage.building.excepciones.GlobalExceptionHandler;
import com.system.backend.manage.building.service.impl.UserDetailsServiceImpl;
import com.system.backend.manage.building.utils.GenerateToken;
import com.system.backend.manage.building.utils.JWTTokenProvider;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

@Component
public class AuthorizationFilterJwt extends OncePerRequestFilter {
//	@Autowired
//	private JWTTokenProvider jwtTokenProvider;
//
////	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> AuthorizationFilterJwt");
//		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Necesita ser authenticada "+isPathNeedsToBeAuthorized(request));
//		if (!isPathNeedsToBeAuthorized(request)) {
//			filterChain.doFilter(request, response);
//			return;
//		}
//		if (request.getMethod().equalsIgnoreCase(SecurityConstant.OPTIONS_HTTP_METHOD)) {
//			response.setStatus(HttpStatus.OK.value());
//		} else {
//			String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//			if (authorizationHeader == null || !authorizationHeader.startsWith(SecurityConstant.TOKEN_PREFIX)) {
//				filterChain.doFilter(request, response);
//				return;
//			}
//			String token = authorizationHeader.substring(SecurityConstant.TOKEN_PREFIX.length());
//			String username = jwtTokenProvider.getSubject(token);
//			if (jwtTokenProvider.isTokenValid(username, token)&& SecurityContextHolder.getContext().getAuthentication() == null) {
//				List<GrantedAuthority> authorities = jwtTokenProvider.getAuthorities(token);
//				Authentication authentication = jwtTokenProvider.getAuthentication(username, authorities, request);
//				SecurityContextHolder.getContext().setAuthentication(authentication);
//			} else {
//				SecurityContextHolder.clearContext();
//			}
//		}
//		filterChain.doFilter(request, response);
//	}

	private boolean isPathNeedsToBeAuthorized(HttpServletRequest request) {
		for (String path : SecurityConstant.PUBLIC_URLS) {
			if (request.getServletPath().startsWith(path)) {
				return false;
			}
		} /**
			 * if (request.getServletPath().equals("/api/auth/") ||
			 * request.getServletPath().equals("/api/auth/token/refresh") ||
			 * request.getServletPath().equals("/api/persona/list") ||
			 * request.getServletPath().startsWith("/api/propietario") ||
			 * request.getServletPath().startsWith("/api/visitante")) { return false; }
			 */

		return true;
	}

	@Autowired
	private GenerateToken generateToken;

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

//	 @Autowired
//	    @Qualifier("customExceptionResolver")
//	    private HandlerExceptionResolver resolver;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws CustomAppException,ServletException, IOException {
//		try {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Entramos a AuthorizationFilterJwt: ");

			if (!isPathNeedsToBeAuthorized(request)) {
				filterChain.doFilter(request, response);
				return;
			} 
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Continuamos con la obtencion del token: ");
			// obtenemos el token de la solicitud HTTP
			String token = generateToken.getBearerTokend(request,response);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Continuamos con Validar el token yeah: ");


//			 Validamos el token haciendo la Decodificamos el token
			DecodedJWT decodedJWT = generateToken.validarToken(token, request,response);
			
			// Obtenemos el usuario
			String username = decodedJWT.getSubject();
			UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
					null, userDetails.getAuthorities());
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			// establecemos la seguridad
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//		} catch (Exception e) {
//			 resolver.resolveException(request, response, null, e);
//		}
		

		filterChain.doFilter(request, response);
				
		

	}
	/*
	 * private boolean isPathNeedsToBeAuthorized(HttpServletRequest request) {
	 * 
	 * if ( request.getServletPath().equals("/api/auth/login") ||
	 * request.getServletPath().equals("/api/auth/token/refresh") ||
	 * request.getServletPath().equals("/api/persona/list") ||
	 * request.getServletPath().startsWith("/api/propietario") ||
	 * request.getServletPath().startsWith("/api/visitante") ) { return false; }
	 * 
	 * 
	 * return true; }
	 */
	/*
	 * OTRA FORMA DE OBTENER LOS ROLES DESDE EL TOKEN
	 * String[]roles=decodedJWT.getClaim("roles").asArray(String.class); //get the
	 * roles that comes with the token Collection<SimpleGrantedAuthority>
	 * authorities=new ArrayList<>(); Arrays.stream(roles).forEach(role->{
	 * authorities.add(new SimpleGrantedAuthority(role)); });
	 */

}

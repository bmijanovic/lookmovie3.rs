package com.ftn.sbnz.service.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftn.sbnz.service.Entities.Models.Role;
import com.ftn.sbnz.service.Entities.Models.User;
import com.ftn.sbnz.service.Exceptions.InvalidAccessTokenException;
import com.ftn.sbnz.service.Exceptions.InvalidTokenTypeException;
import com.ftn.sbnz.service.Exceptions.ResponseError;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private TokenProvider tokenProvider;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		System.out.println(request.getRequestURI());
		if (request.getRequestURI().equals("/api/auth/login") || request.getRequestURI().equals("/api/auth/register")  || request.getRequestURI().contains("swagger") || request.getRequestURI().contains("/api-doc")) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = readTokenFromCookie(request);
		if (token != null && !StringUtils.hasLength(token)) {
			token = readTokenFromRequest(request);
		}



		if (!StringUtils.hasLength(token)) {
			filterChain.doFilter(request, response);
			return;
		}
		try {
			tokenProvider.validateToken(token, TokenType.ACCESS);

			UUID userId = tokenProvider.getUserIdFromToken(token);

			UserDetails userDetails = customUserDetailsService.loadUserById(userId);
			User user = (User) userDetails;
			List<GrantedAuthority> authorities = new ArrayList<>();
			for(Role role : user.getRoles()){
				System.out.println(role.getName());
	        	authorities.add(new SimpleGrantedAuthority(role.getName().split("_")[1].toUpperCase()));
			}


			if (userDetails == null) {
				sendResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Invalid authorization.");
				return;
			}
//			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			filterChain.doFilter(request, response);
		} catch (InvalidTokenTypeException e) {
			e.printStackTrace();
			sendResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Invalid token type.");
		} catch (InvalidAccessTokenException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
			sendResponse(response, HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
		} catch (Exception e) {
			sendResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error has occurred.");
			e.printStackTrace();
		}
	}

	private String readTokenFromRequest(HttpServletRequest request) {
		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (StringUtils.hasLength(authHeader) && authHeader.startsWith("Bearer "))
			return authHeader.substring(7);

		return null;
	}

	private String readTokenFromCookie(HttpServletRequest request) {
		// Reads token value from cookie
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("token")) {
				return cookie.getValue();
			}
		}
		return null;
	}

	private String readSecretFromCookie(HttpServletRequest request) {
		// Reads secret value from cookie
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("secret")) {
				return cookie.getValue();
			}
		}
		return null;
	}


	private void sendResponse(HttpServletResponse response, Integer status, String message) throws IOException {
		ResponseError responseError = new ResponseError(status, message);
		response.setStatus(status);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		new ObjectMapper().writeValue(response.getOutputStream(), responseError);
	}

}
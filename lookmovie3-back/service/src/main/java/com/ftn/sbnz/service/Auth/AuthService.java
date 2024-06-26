package com.ftn.sbnz.service.Auth;

import com.ftn.sbnz.service.Controllers.DTOs.CreateUserDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.ftn.sbnz.service.Auth.DTOs.LoginRequest;
import com.ftn.sbnz.service.Entities.Models.User;
import com.ftn.sbnz.service.Auth.DTOs.TokenResponse;
import com.ftn.sbnz.service.Repositories.RoleRepository;
import com.ftn.sbnz.service.Repositories.UserRepository;
import com.ftn.sbnz.service.Security.CustomAuthenticationToken;
import com.ftn.sbnz.service.Security.TokenProvider;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;


@Service
public class AuthService {

    @Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenProvider tokenProvider;

	@Qualifier("kieSession")
	@Autowired
	private KieSession kieSession;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

    public TokenResponse login(LoginRequest loginRequest, HttpServletResponse response) {
		String secret = generateRandomToken(40);

		Authentication authentication = authenticationManager.authenticate(new CustomAuthenticationToken(
				loginRequest.getEmail(),
				loginRequest.getPassword(),
				secret
		));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String accessToken = tokenProvider.createAccessToken(authentication, secret);
		Long expiresAt = tokenProvider.readClaims(accessToken).getExpiration().getTime();
		// add cookie with secret
		Cookie cookie = new Cookie("token", accessToken);
		cookie.setPath("/");
		cookie.setSecure(false);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(60 * 60 * 24 * 30);
		response.addCookie(cookie);
		((User)authentication.getPrincipal()).setLoginAttempts(0);
		userRepository.save((User)authentication.getPrincipal());
		return new TokenResponse(accessToken, expiresAt);
	}

	public String generateRandomToken(int length) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!#$%&'()*+-./:<=>?@[]^_`{|}~";
		SecureRandom random = new SecureRandom();
		StringBuilder buffer = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(allowedChars.length());
			buffer.append(allowedChars.charAt(randomIndex));
		}
		String generatedString = buffer.toString();
		System.out.println(generatedString);
		return generatedString;
	}



	public void logout(User user, HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie("token", "");
		cookie.setPath("/");
		cookie.setSecure(false);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}


    public User register(CreateUserDTO register) {
		User user = new User(register.getName(), register.getEmail(), passwordEncoder.encode(register.getPassword()));
		user.getRoles().add(roleRepository.findByName("ROLE_USER").get());
		kieSession.insert(user);
		return userRepository.save(user);

    }
}

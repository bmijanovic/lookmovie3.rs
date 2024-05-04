package com.ftn.sbnz.service.Security;

import com.ftn.sbnz.service.Exceptions.InvalidLogin;
import com.ftn.sbnz.service.Models.User;
import com.ftn.sbnz.service.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Autowired
	private TaskScheduler taskScheduler;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		CustomAuthenticationToken customAuthenticationToken = (CustomAuthenticationToken) authentication;
		String name = customAuthenticationToken.getName();
		String password = customAuthenticationToken.getCredentials().toString();
		String secret = customAuthenticationToken.getSecret().toString();

		User user = (User) customUserDetailsService.loadUserByUsername(name);
		if (user == null) {
            try {
                throw new InvalidLogin("Invalid credentials.");
            } catch (InvalidLogin e) {
                throw new RuntimeException(e);
            }
        }
		if (!user.isEnabled()) {
            try {
                throw new InvalidLogin("Login failed. Email might be unverified.");
            } catch (InvalidLogin e) {
                throw new RuntimeException(e);
            }
        }

		if (!user.isAccountNonLocked()) {
            try {
                throw new InvalidLogin("Login failed. Account is locked.");
            } catch (InvalidLogin e) {
                throw new RuntimeException(e);
            }
        }
		if (passwordEncoder.matches(password, user.getPassword())) {
			return new CustomAuthenticationToken(user, password, secret, user.getAuthorities());
		}
		// reduce number of login attempts
		System.out.println("Ovo se pozove ako je pogresio sifru");
		user.setLoginAttempts(user.getLoginAttempts() + 1);
		// if number of login attempts is 0, disable user
		if (user.getLoginAttempts() >= 3) {
			// reset login attempts in 10 minutes
			System.out.println("Resetujem ga za 10 minuta, previse je gresio");
			taskScheduler.schedule(() -> resetLoginAttempts(user.getId()), Instant.ofEpochMilli(System.currentTimeMillis() + 1 * 60 * 1000));
		}

		userRepository.save(user);
        try {
            throw new InvalidLogin("Invalid credentials.");
        } catch (InvalidLogin e) {
            throw new RuntimeException(e);
        }
    }

	public void resetLoginAttempts(UUID userId) {
		User user = userRepository.findById(userId).orElseThrow();
		System.out.println("Resetovao sam se za usera" + user.getUsername());
		user.setLoginAttempts(0);
		userRepository.save(user);

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(CustomAuthenticationToken.class);
	}

}

package com.ftn.sbnz.service.Auth;


import com.ftn.sbnz.service.Entities.Models.User;
import com.ftn.sbnz.service.Auth.DTOs.LoginRequest;
import com.ftn.sbnz.service.Auth.DTOs.TokenResponse;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        return authService.login(loginRequest, response);
    }

    @PostMapping("/logout")
	public ResponseEntity<String> logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
		User user = (User) authentication.getPrincipal();
		authService.logout(user, request, response);
		return ResponseEntity.ok("Logged out");
	}

    @GetMapping("/me")
     public UUID me(Authentication authentication, HttpServletRequest request) {
        // Print cookies from the request
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println("Cookie Name: " + cookie.getName() + ", Value: " + cookie.getValue());
            }
        }

        // Return user ID if authenticated
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            return user.getId();
        } else {
            // Handle the case where authentication is not available or not valid
            return null;
        }
    }

}

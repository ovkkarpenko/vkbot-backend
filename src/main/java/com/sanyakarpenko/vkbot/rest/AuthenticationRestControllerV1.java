package com.sanyakarpenko.vkbot.rest;

import com.sanyakarpenko.vkbot.entities.User;
import com.sanyakarpenko.vkbot.resources.AuthenticationRequestResource;
import com.sanyakarpenko.vkbot.resources.AuthenticationResponseResource;
import com.sanyakarpenko.vkbot.resources.ErrorResponseResource;
import com.sanyakarpenko.vkbot.securities.jwt.JwtTokenProvider;
import com.sanyakarpenko.vkbot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth/",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationRestControllerV1 {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestResource requestResource) {
        try {
            String username = requestResource.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestResource.getPassword()));

            User user = userService.findUserByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found.");
            }

            String token = jwtTokenProvider.createToken(user);

            AuthenticationResponseResource responseResource = new AuthenticationResponseResource();
            responseResource.setUsername(username);
            responseResource.setToken(token);

            return ResponseEntity.ok(responseResource);
        } catch (AuthenticationException e) {
            ErrorResponseResource error = new ErrorResponseResource();
            error.setErrorCode(1L);
            error.setErrorMessage("Invalid username or password");

            return ResponseEntity.badRequest().body(error);
        }
    }
}

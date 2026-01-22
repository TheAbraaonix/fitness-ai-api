package com.carlosholanda.fitness_ai_api.application.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.auth.CreateUserRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.auth.LoginRequest;
import com.carlosholanda.fitness_ai_api.adapters.inbound.dto.auth.LoginResponse;
import com.carlosholanda.fitness_ai_api.application.usecases.AuthUseCases;
import com.carlosholanda.fitness_ai_api.domain.user.User;
import com.carlosholanda.fitness_ai_api.domain.user.UserRepository;
import com.carlosholanda.fitness_ai_api.infrastructure.exception.auth.InvalidCredentialsException;
import com.carlosholanda.fitness_ai_api.infrastructure.exception.domain.user.UserAlreadyExistsException;
import com.carlosholanda.fitness_ai_api.infrastructure.security.jwt.JwtTokenProvider;

@Service
public class AuthServiceImpl implements AuthUseCases {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.email())
            .orElseThrow(() -> new InvalidCredentialsException());
        
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password())
        );

        String token = jwtTokenProvider.generateToken(authentication);

        return new LoginResponse(
            token,
            "Bearer",
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getRole().name()
        );
    }

    @Override
    public LoginResponse register(CreateUserRequest createUserRequest) {
        if(userRepository.existsByEmail(createUserRequest.email())) {
            throw new UserAlreadyExistsException(createUserRequest.email());
        }

        User user = new User();
        user.setName(createUserRequest.name());
        user.setEmail(createUserRequest.email());
        user.setPassword(passwordEncoder.encode(createUserRequest.password()));
        user.setAge(createUserRequest.age());
        user.setWeight(createUserRequest.weight());
        user.setHeight(createUserRequest.height());
        user.setFitnessGoal(createUserRequest.fitnessGoal());
        user.setFitnessLevel(createUserRequest.fitnessLevel());
        user.setAvailableDaysPerWeek(createUserRequest.availableDaysPerWeek());

        User savedUser = userRepository.save(user);

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(createUserRequest.email(), createUserRequest.password())
        );

        String token = jwtTokenProvider.generateToken(authentication);

        return new LoginResponse(
            token,
            "Bearer",
            savedUser.getId(),
            savedUser.getName(),
            savedUser.getEmail(),
            savedUser.getRole().name()
        );
    }

}

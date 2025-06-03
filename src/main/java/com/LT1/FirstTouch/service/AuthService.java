package com.LT1.FirstTouch.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.LT1.FirstTouch.dto.AuthResponse;
import com.LT1.FirstTouch.dto.LoginRequest;
import com.LT1.FirstTouch.dto.RegisterRequest;
import com.LT1.FirstTouch.model.Role;
import com.LT1.FirstTouch.model.User;
import com.LT1.FirstTouch.repo.UserRepository;
import com.LT1.FirstTouch.security.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtTokenUtil jwtUtil;
    private final AuthenticationManager authManager;

    public AuthResponse login(LoginRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);
        return new AuthResponse(token);
    }

    public void register(RegisterRequest req) {
        if (userRepo.existsByUsername(req.getUsername()))
            throw new RuntimeException("Username already exists");

        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(encoder.encode(req.getPassword()));
        user.setRole(Role.USER);

        userRepo.save(user);        
    }
}

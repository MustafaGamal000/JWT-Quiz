package com.security.jwt.service;

import com.security.jwt.model.Question;
import com.security.jwt.model.Quiz;
import com.security.jwt.model.User;
import com.security.jwt.repository.QuestionDao;
import com.security.jwt.repository.QuizRepository;
import com.security.jwt.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final QuestionDao questionDao;
    private final AuthenticationManager authenticationManager;
    public AuthenticationService(QuizRepository quizRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, QuestionDao questionDao, AuthenticationManager authenticationManager) {
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.questionDao = questionDao;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(User request){
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(request);
        String token = jwtService.generateToken(request);
        return new AuthenticationResponse(token);
    }


    public AuthenticationResponse authenticate(User request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }

}

package com.security.jwt.controller;

import com.security.jwt.model.Quiz;
import com.security.jwt.model.User;
import com.security.jwt.repository.QuizRepository;
import com.security.jwt.service.AuthenticationResponse;
import com.security.jwt.service.AuthenticationService;
import com.security.jwt.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authService;
    private final QuizService quizService;

    public AuthenticationController(AuthenticationService authService, QuizService quizService) {
        this.authService = authService;
        this.quizService = quizService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User request){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User request){
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/create_quiz")
    public ResponseEntity<String> createQuiz(@RequestParam String category,
                                             @RequestParam int nQuestions,
                                             @RequestParam String title){
        return quizService.createQuiz(category, nQuestions, title);
    }
    @PostMapping("/hello")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello");
    }
}

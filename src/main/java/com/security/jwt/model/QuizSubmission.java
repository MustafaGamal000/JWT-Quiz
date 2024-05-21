package com.security.jwt.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class QuizSubmission {
    private int qID;
    private String answer;

    public QuizSubmission(int qID, String answer) {
        this.qID = qID;
        this.answer = answer;
    }

    public QuizSubmission() {
    }
}

package com.security.jwt.service;

import com.security.jwt.model.Question;
import com.security.jwt.model.Quiz;
import com.security.jwt.repository.QuestionDao;
import com.security.jwt.repository.QuizRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    private final QuestionDao questionDao;
    private final QuizRepository quizRepository;

    public QuizService(QuestionDao questionDao, QuizRepository quizRepository) {
        this.questionDao = questionDao;
        this.quizRepository = quizRepository;
    }

    public ResponseEntity<String> createQuiz(String category, int nQuestions, String title) {
        try{
            List<Question> questions = questionDao.findRandomQuestionsByCategory(category, nQuestions);
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);

            quizRepository.save(quiz);
            return new ResponseEntity<>("Quiz Created", HttpStatus.CREATED);
        }catch (Exception e){
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
        return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
    }
}

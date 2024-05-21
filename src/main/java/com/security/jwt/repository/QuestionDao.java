package com.security.jwt.repository;

import com.security.jwt.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> getQuestionsByCategory(String category);

    @Query(value = "SELECT TOP (?2) * FROM questions q where q.category = (?1) ORDER BY NEWID()", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int nQuestions);
}

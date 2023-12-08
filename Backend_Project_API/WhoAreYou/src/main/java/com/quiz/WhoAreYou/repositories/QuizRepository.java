package com.quiz.WhoAreYou.repositories;

import com.quiz.WhoAreYou.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository  extends JpaRepository<Quiz, Long> {
    List<Quiz> findByIsFinishedIsTrue();
    List<Quiz> findByUserNameEquals(String userName);
}

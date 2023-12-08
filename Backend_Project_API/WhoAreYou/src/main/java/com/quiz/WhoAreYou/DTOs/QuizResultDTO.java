package com.quiz.WhoAreYou.DTOs;

public class QuizResultDTO {

    private String trainerName;

    private Long quizId;

    public QuizResultDTO(String trainerName, Long quizId) {
        this.trainerName = trainerName;
        this.quizId = quizId;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public QuizResultDTO() {
    }
}


package com.quiz.WhoAreYou.DTOs;

public class AnswerDTO {

    private Long questionNumber;
    private String userAnswer;

    public AnswerDTO(Long questionNumber, String userAnswer) {
        this.questionNumber = questionNumber;
        this.userAnswer = userAnswer;
    }

    public AnswerDTO() {
    }

    public Long getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(Long questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }
}

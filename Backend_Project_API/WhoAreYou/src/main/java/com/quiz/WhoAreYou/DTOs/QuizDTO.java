package com.quiz.WhoAreYou.DTOs;

import jakarta.persistence.Column;

import java.util.List;

public class QuizDTO {

    private String userName;
    private int numberOfQuestions;
    private List<Long> questionIds;

    public QuizDTO(String userName, int numberOfQuestions, List<Long> questionIds) {
        this.userName = userName;
        this.numberOfQuestions = numberOfQuestions;
        this.questionIds = questionIds;
    }

    public QuizDTO() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public List<Long> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<Long> questionIds) {
        this.questionIds = questionIds;
    }
}

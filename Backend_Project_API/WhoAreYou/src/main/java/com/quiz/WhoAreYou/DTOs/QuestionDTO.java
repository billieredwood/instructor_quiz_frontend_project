package com.quiz.WhoAreYou.DTOs;

import jakarta.persistence.Column;

import java.util.List;

public class QuestionDTO {

    private String question;

    private String zsoltAnswer;

    private String annaAnswer;

    private String colinAnswer;

    private String thibyaaAnswer;

    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

    private List<Long> quizIds;

    public QuestionDTO(String question, String zsoltAnswer, String annaAnswer,
                       String colinAnswer, String thibyaaAnswer, String optionA,
                       String optionB, String optionC, String optionD, List<Long> quizIds) {
        this.question = question;
        this.zsoltAnswer = zsoltAnswer;
        this.annaAnswer = annaAnswer;
        this.colinAnswer = colinAnswer;
        this.thibyaaAnswer = thibyaaAnswer;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.quizIds = quizIds;
    }

    public QuestionDTO() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getZsoltAnswer() {
        return zsoltAnswer;
    }

    public void setZsoltAnswer(String zsoltAnswer) {
        this.zsoltAnswer = zsoltAnswer;
    }

    public String getAnnaAnswer() {
        return annaAnswer;
    }

    public void setAnnaAnswer(String annaAnswer) {
        this.annaAnswer = annaAnswer;
    }

    public String getColinAnswer() {
        return colinAnswer;
    }

    public void setColinAnswer(String colinAnswer) {
        this.colinAnswer = colinAnswer;
    }

    public String getThibyaaAnswer() {
        return thibyaaAnswer;
    }

    public void setThibyaaAnswer(String thibyaaAnswer) {
        this.thibyaaAnswer = thibyaaAnswer;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public List<Long> getQuizIds() {
        return quizIds;
    }

    public void setQuizIds(List<Long> quizIds) {
        this.quizIds = quizIds;
    }
}

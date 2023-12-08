package com.quiz.WhoAreYou.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column
    private Long id;

    @Column
    private int currentQuestionCounter;
    @Column
    private String question;
    @Column
    private String zsoltAnswer;
    @Column
    private String annaAnswer;
    @Column
    private String colinAnswer;
    @Column
    private String thibyaaAnswer;
    @Column
    private String optionA;
    @Column
    private String optionB;
    @Column
    private String optionC;
    @Column
    private String optionD;

    @ManyToMany(mappedBy = "questions")
    @JsonIgnoreProperties({"questions"})
    private List<Quiz> quizzes;

    public Question(String question,
                    String optionA,
                    String optionB,
                    String optionC,
                    String optionD,
                    String zsoltAnswer,
                    String annaAnswer,
                    String colinAnswer,
                    String thibyaaAnswer
                    ) {
        this.question = question;
        this.zsoltAnswer = zsoltAnswer;
        this.annaAnswer = annaAnswer;
        this.colinAnswer = colinAnswer;
        this.thibyaaAnswer = thibyaaAnswer;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.quizzes = new ArrayList<Quiz>();

    }

    public Question() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
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

    public void addQuiz(Quiz quiz){
        this.quizzes.add(quiz);
    }
}
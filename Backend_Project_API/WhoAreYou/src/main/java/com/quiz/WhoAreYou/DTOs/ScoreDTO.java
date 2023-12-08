package com.quiz.WhoAreYou.DTOs;

public class ScoreDTO {
    private int score;

    public ScoreDTO(int score) {
        this.score = score;
    }

    public ScoreDTO() {
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

package com.quiz.WhoAreYou.DTOs;

import java.util.List;

public class AddRemoveQuestionDTO {

    private List<Long> questionIds;

    public AddRemoveQuestionDTO(List<Long> questionIds) {
        this.questionIds = questionIds;
    }

    public AddRemoveQuestionDTO() {
    }

    public List<Long> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<Long> questionIds) {
        this.questionIds = questionIds;
    }
}

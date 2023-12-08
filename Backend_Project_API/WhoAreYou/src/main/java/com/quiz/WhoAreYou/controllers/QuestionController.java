package com.quiz.WhoAreYou.controllers;

import com.quiz.WhoAreYou.models.Question;
import com.quiz.WhoAreYou.DTOs.QuestionDTO;
import com.quiz.WhoAreYou.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions(){
        try {
            return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Question> makeNewQuestion(@RequestBody QuestionDTO questionDTO){
        return new ResponseEntity<>(questionService.addNewQuestion(questionDTO),HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteQuestion(@PathVariable Long id){
        Long removeById = questionService.removeQuestionFromQuiz(id);
        if (removeById != null) {
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id){
        Optional<Question> getQuestionById = questionService.findQuestionById(id);
        if(getQuestionById.isPresent()){
            return new ResponseEntity<>(getQuestionById.get(), HttpStatus.OK);
        } else{
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Question> updateQuestionById(@PathVariable Long id, @RequestBody QuestionDTO questionDTO){
        try {
            Question updateQuestion = questionService.updateQuestionById(id, questionDTO);
            return new ResponseEntity<>(updateQuestion, HttpStatus.OK);
        } catch (ResponseStatusException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

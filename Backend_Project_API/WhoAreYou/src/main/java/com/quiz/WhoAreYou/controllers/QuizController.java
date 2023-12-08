package com.quiz.WhoAreYou.controllers;

import com.quiz.WhoAreYou.DTOs.*;
import com.quiz.WhoAreYou.models.Question;
import com.quiz.WhoAreYou.models.Quiz;
import com.quiz.WhoAreYou.services.QuestionService;
import com.quiz.WhoAreYou.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/quizzes")
public class QuizController {
    @Autowired
    QuizService quizService;

    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizzes(){
        try {
            return new ResponseEntity<>(quizService.findAllQuizzes(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping (value = "/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id){
        try {
            Optional<Quiz> getQuizById = quizService.findQuizById(id);
            if (getQuizById.isPresent()) {
                return new ResponseEntity<>(getQuizById.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping
//    public ResponseEntity<Quiz> makeNewQuiz(@RequestBody QuizDTO quizDTO){
//        try {
//            return new ResponseEntity<>(quizService.addNewQuiz(quizDTO), HttpStatus.CREATED);
//            } catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteQuizById(@PathVariable Long id){
        try {
            Long removeById = quizService.removeQuizById(id);
            if (removeById != null) {
                return new ResponseEntity<Long>(id, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Quiz> addQuestionsToQuiz(@PathVariable Long id, @RequestBody AddRemoveQuestionDTO addQuestionDTO){
        Quiz addQuestionToQuiz = quizService.addQuestionsToQuiz(id,addQuestionDTO);
        try{
            return new ResponseEntity<>(quizService.addQuestionsToQuiz(id, addQuestionDTO),HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/removeQuestion/{id}")
    public ResponseEntity<Quiz> removeQuestionsFromQuiz(@PathVariable Long id, @RequestBody AddRemoveQuestionDTO removeQuestionDTO){
        try{
            return new ResponseEntity<>(quizService.removeQuestionFromQuiz(id,removeQuestionDTO),HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/startQuiz/{id}")
    public ResponseEntity<Quiz> startQuiz(@PathVariable Long id){
        try {
            Quiz startQuiz = quizService.startQuiz(id);
            return new ResponseEntity<>(startQuiz, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/random")
    public ResponseEntity<Quiz> createRandomQuiz(@RequestParam String userName, @RequestParam int numberOfQuestions) throws Exception {
        try {
            Quiz quiz = quizService.createRandomQuiz(userName,numberOfQuestions);
            return new ResponseEntity<>(quiz, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/takeQuiz/{quizId}")
    public ResponseEntity<Quiz> answerQuestionFromQuiz(
            @PathVariable Long quizId,
            @RequestBody AnswerDTO answerDTO){
        try{
            return new ResponseEntity<>(quizService.answerQuestionFromQuiz(quizId,answerDTO), HttpStatus.OK);
         } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/finishQuiz/{quizId}")
    public ResponseEntity<Quiz> finishQuiz(
            @PathVariable Long quizId){
        try{
            return new ResponseEntity<>(quizService.finishQuiz(quizId), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{quizId}/result")
    public ResponseEntity<MessageDTO> getTrainerResultById(@PathVariable Long quizId,
                                                           @RequestParam String trainerName){
        try {
            QuizResultDTO quizResultDTO = new QuizResultDTO(trainerName, quizId);
            return new ResponseEntity<>(quizService.mapQuizToQuizResult(quizResultDTO), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
     }



     @GetMapping(value = "/{quizId}/{questionNumber}")
    public ResponseEntity <Question> getQuestionByQuestionNumber(
            @PathVariable Long quizId,
            @PathVariable int questionNumber){
        try {
            return new ResponseEntity<>(quizService.findQuestionByNumber(quizId, questionNumber), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

     }

    @GetMapping(value = "displayQuestion/{quizId}/{questionNumber}")
    public ResponseEntity <DisplayQuestionDTO> getQuestionBodyAndOptionsByQuestionNumber(
            @PathVariable Long quizId,
            @PathVariable int questionNumber){
        try {
            return new ResponseEntity<>(quizService.findQuestionBodyByNumber(quizId, questionNumber), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

     @GetMapping(value = "/allResults")
    public ResponseEntity<ScoreDTO> getTotalResultByTrainer(@RequestParam String trainerName){
        try {
            return new ResponseEntity<>(quizService.getTotalResultByTrainer(trainerName), HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
     }

    @GetMapping(value = "/allQuizzes")
    public ResponseEntity<List<Quiz>> getAllQuizzesByUserName(@RequestParam String userName){
        try {
            return new ResponseEntity<>(quizService.getQuizzesByUserName(userName), HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}




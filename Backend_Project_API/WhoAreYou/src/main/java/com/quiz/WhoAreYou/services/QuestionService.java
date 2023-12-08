package com.quiz.WhoAreYou.services;

import com.quiz.WhoAreYou.DTOs.QuestionDTO;
import com.quiz.WhoAreYou.models.Quiz;
import com.quiz.WhoAreYou.repositories.QuestionRepository;
import com.quiz.WhoAreYou.models.Question;
import com.quiz.WhoAreYou.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    QuizRepository quizRepository;
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question addNewQuestion(QuestionDTO questionDTO) {
        Question question = new Question(questionDTO.getQuestion(),questionDTO.getOptionA(),questionDTO.getOptionB(),questionDTO.getOptionC(),questionDTO.getOptionD(),questionDTO.getZsoltAnswer(),questionDTO.getAnnaAnswer(),questionDTO.getColinAnswer(),questionDTO.getThibyaaAnswer());

        for (Long quizID : questionDTO.getQuizIds()){
            Optional<Quiz> optionalQuiz = quizRepository.findById(quizID);
            if (optionalQuiz.isPresent()){
                Quiz quiz = optionalQuiz.get();
                if (!quiz.getRunning()) {
                    question.addQuiz(quiz);
                    quiz.addQuestion(question);
                    //quizRepository.save(quiz);
                }
            }
        }
        questionRepository.save(question);
        return question;
    }

    public Long removeQuestionFromQuiz(Long id) {
        if (questionRepository.findById(id).isPresent()) {
            Question question = questionRepository.getById(id);
            for (Quiz quiz : question.getQuizzes()) {
                quiz.removeQuestion(question);
                List<String> toRemoveState = new ArrayList<>();
                for (List<String> questionStates: quiz.getCurrentState()){
                    if (questionStates.get(1).equals(id.toString())){
                        toRemoveState = questionStates;
                    }
                }
                if (!toRemoveState.isEmpty()) {
                    quiz.removeQuestionState(toRemoveState);
                    quiz.setNumberOfQuestions(quiz.getNumberOfQuestions()-1);
                }
            }
            questionRepository.deleteById(id);
            return id;
        } else {
            return null;
        }

    }

    public Optional<Question> findQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    public Question updateQuestionById(Long id, QuestionDTO questionDTO) {
        Question question = questionRepository.findById(id).get();
        if (questionDTO.getQuestion()!=null) {
            question.setQuestion(questionDTO.getQuestion());
        }
        if (questionDTO.getZsoltAnswer()!=null) {
            question.setZsoltAnswer(questionDTO.getZsoltAnswer());
        }
        if (questionDTO.getAnnaAnswer()!=null) {
            question.setAnnaAnswer(questionDTO.getAnnaAnswer());
        }
        if (questionDTO.getColinAnswer()!=null) {
            question.setColinAnswer(questionDTO.getColinAnswer());
        }
        if (questionDTO.getThibyaaAnswer()!=null) {
            question.setThibyaaAnswer(questionDTO.getThibyaaAnswer());
        }
        if (questionDTO.getOptionA()!=null) {
            question.setOptionA(questionDTO.getOptionA());
        }
        if (questionDTO.getOptionB()!=null) {
            question.setOptionB(questionDTO.getOptionB());
        }
        if (questionDTO.getOptionC()!=null) {
            question.setOptionC(questionDTO.getOptionC());
        }
        if (questionDTO.getOptionD()!=null) {
            question.setOptionD(questionDTO.getOptionD());
        }
        if (questionDTO.getQuizIds()!=null) {
            for (Long quizID : questionDTO.getQuizIds()) {
                if (quizRepository.findById(quizID).isPresent()
                        && (!question.getQuizzes().contains(quizRepository.findById(quizID).get()))) {
                    question.addQuiz(quizRepository.findById(quizID).get());
                }

            }

        }
        questionRepository.save(question);
        return question;
    }
}

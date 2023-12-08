package com.quiz.WhoAreYou.services;

import com.quiz.WhoAreYou.DTOs.*;
import com.quiz.WhoAreYou.models.Question;
import com.quiz.WhoAreYou.models.Quiz;
import com.quiz.WhoAreYou.repositories.QuestionRepository;
import com.quiz.WhoAreYou.repositories.QuizRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;

    public List<Quiz> findAllQuizzes() {
        return quizRepository.findAll();
    }

    public Optional<Quiz> findQuizById(Long id) {
        return quizRepository.findById(id);
    }

    public Quiz addNewQuiz(QuizDTO quizDTO) {
        Quiz quiz = new Quiz(quizDTO.getUserName(), quizDTO.getNumberOfQuestions());

        for (Long questionId : quizDTO.getQuestionIds()) {
            if (questionRepository.findById(questionId).isPresent()) {
                quiz.addQuestion(questionRepository.findById(questionId).get());
            }
        }

        quizRepository.save(quiz);
        return quiz;
    }

    public Long removeQuizById(Long id) {
        if (quizRepository.findById(id).isPresent()) {
            quizRepository.deleteById(id);
            return id;
        } else {
            return null;
        }

    }

    @Transactional
    public Quiz addQuestionsToQuiz(Long id, AddRemoveQuestionDTO addRemoveQuestionDTO) {

        if (quizRepository.findById(id).isEmpty()) {
            return null;
        } else {
            Quiz quiz = quizRepository.findById(id).get();
            //List<Question> questions = addRemoveQuestionDTO;

            if (!quiz.getRunning()) {


                for (Long questionId : addRemoveQuestionDTO.getQuestionIds()) { //loops through Ids in DTO
                    if (questionRepository.findById(questionId).isPresent()) {
                        quiz.addQuestion(questionRepository.findById(questionId).get()); //adds question to quiz
                        questionRepository.findById(questionId).get().addQuiz(quiz); //adds quiz to question
                    }
                }
                quizRepository.save(quiz);
            }
            return quiz;
        }

    }


    public Quiz removeQuestionFromQuiz(Long id, AddRemoveQuestionDTO removeQuestionDTO) {
        if (quizRepository.findById(id).isEmpty()) {
            return null;
        } else {
            Quiz quiz = quizRepository.findById(id).get();

            if (!quiz.getRunning()) {


                for (Long questionId : removeQuestionDTO.getQuestionIds()) {
                    if (questionRepository.findById(questionId).isPresent() && (quiz.getQuestions().contains(questionRepository.findById(questionId).get()))) {
                        quiz.removeQuestion(questionRepository.getById(questionId));
                    }
                }
                quizRepository.save(quiz);

            }
            return quiz;
        }
    }

    public Quiz startQuiz(Long id) {
        Optional<Quiz> optionalQuiz  = quizRepository.findById(id);
        if(optionalQuiz.isPresent()){
            Quiz quiz = optionalQuiz.get();
            if (!quiz.getRunning()) {
                quiz.setRunning(true);

                List<List<String>> newCurrentState = new ArrayList<>();
                for (Question question : quiz.getQuestions()) {
                    List<String> questionState = new ArrayList<>();
                    questionState.add("N");
                    questionState.add(question.getId().toString());
                    newCurrentState.add(questionState); // Add questionState to newCurrentState
                }
                quiz.setCurrentState(newCurrentState);
                quiz.setNumberOfQuestions(quiz.getQuestions().size());
                quizRepository.save(quiz);
            }
        }
      return null;
    }

    public Quiz createRandomQuiz(String userName, int numberOfQuestions) throws Exception {
        List<Question> allQuestions = questionRepository.findAll();

        if (allQuestions.size() < numberOfQuestions){
            throw new Exception("Not enough questions available");
        }

        Collections.shuffle(allQuestions);
        List<Question> selectedQuestions = allQuestions.subList(0, numberOfQuestions);
        Quiz quiz = new Quiz(userName, numberOfQuestions);
        for (Question question : selectedQuestions){
            quiz.addQuestion(question);
        }
        quizRepository.save(quiz);

        return quiz;
    }


    public Quiz answerQuestionFromQuiz(Long quizId, AnswerDTO answerDTO) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);
        if(optionalQuiz.isPresent()){
            Quiz quiz = optionalQuiz.get();
            int questionNumber = Math.toIntExact(answerDTO.getQuestionNumber());
            List<String> currentQuestion = quiz.getCurrentState().get(questionNumber);
            currentQuestion.set(0, answerDTO.getUserAnswer());
            quiz.setCurrentStatebyQuestionNumber(questionNumber, currentQuestion);
            quizRepository.save(quiz);
        }
       return null;


    }


    public Quiz finishQuiz(Long quizId){
        int pointIncrement = 1;
        Optional<Quiz> optionalQuiz= quizRepository.findById(quizId);

       if(optionalQuiz.isPresent()) {
           Quiz quiz = optionalQuiz.get();
           if (!quiz.getFinished()) {
               quiz.setFinished(true);
               for (List<String> questionState : quiz.getCurrentState()) {
                   Question question = questionRepository.findById(Long.valueOf(questionState.get(1))).get();
                   String userAnswer = questionState.get(0);
                   if (userAnswer.equals(question.getZsoltAnswer())) {
                       quiz.setZsoltScore(quiz.getZsoltScore() + pointIncrement);
                   }
                   if (userAnswer.equals(question.getColinAnswer())) {
                       quiz.setColinScore(quiz.getColinScore() + pointIncrement);
                   }
                   if (userAnswer.equals(question.getAnnaAnswer())) {
                       quiz.setAnnaScore(quiz.getAnnaScore() + pointIncrement);
                   }

                   if (userAnswer.equals(question.getThibyaaAnswer())) {
                       quiz.setThibyaaScore(quiz.getThibyaaScore() + pointIncrement);
                   }
               }
               quizRepository.save(quiz);
           }
       }
       return null;
    }

    public MessageDTO mapQuizToQuizResult(QuizResultDTO quizResultDTO) {
       Optional<Quiz> optionalQuiz = quizRepository.findById(quizResultDTO.getQuizId());
        MessageDTO messageDTO = new MessageDTO();
        String trainerName = quizResultDTO.getTrainerName();
       if(optionalQuiz.isPresent()){
           Quiz quiz = optionalQuiz.get();
           if(quiz.getFinished()) {
               switch (trainerName) {
                   case "Zsolt":
                       String bioZsolt = "You got " + quiz.getZsoltScore() + " points for Zsolt - " +
                               "The personality of a caffeine-overdosed golden retriever - Zsolt's wife \\n" +
                               "The absolute most handsome boy in the world - Zsolt's mum \\n" +
                               "I'm not saying he's annoying, but... " +
                               "- All of Zsolt's former/current colleagues, probably";
                       messageDTO.setMessage(bioZsolt);
                       break;
                   case "Anna":
                       String bioAnna = "You got " + quiz.getAnnaScore() + " points for Anna - " +
                               "Anna is a software developer trainer and mother-of-tweens-and-teens. " +
                               "Her specialities are Java, Javascript, Python, beetroot chocolate cake, " +
                               "chicken pie, and the family calendar.\n" +
                               "Her children have described her as, \"nice but scary\", \"unpredictable\" " +
                               "and \"a good listener\".\n" +
                               "She loves reading, learning French, watching films, " +
                               "talking to her family (and anyone else) and going for walks. " +
                               "She is fuelled by tea (medium brewed and just a *drop* of milk pls) " +
                               "and biscuits (hobnob or jaffa cake).";
                       messageDTO.setMessage(bioAnna);
                       break;
                   case "Colin":
                       String bioColin = "You got " + quiz.getColinScore() + " points for Colin " +
                               "- Fuelled by biscuits and coffee." + " Average guitarist." +
                               " Once got stuck in a lift with Gordon Brown (before he was Prime Minister)";
                        messageDTO.setMessage(bioColin);
                        break;
                   case "Thibyaa":
                       String bioThibyaa = "You got " + quiz.getThibyaaScore() + " points for Thibyaa -" + "I'm very "
                               + "organised and meticulous, quite calm and perceived as nice by others but I'm the " +
                               "\"looks like a cinnamon roll could kill you\" kinda person";
                        messageDTO.setMessage(bioThibyaa);
                        break;
                   default:
                       String defaultBio = "Unknown trainer";
                       messageDTO.setMessage(defaultBio);
                       break;
               }
               return messageDTO;
           }
       }
        return null;
       }

    public Question findQuestionByNumber(Long quizId, int questionNumber) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);
        if (optionalQuiz.isPresent()) {
            Quiz quiz = optionalQuiz.get();
            return quiz.getQuestions().get(questionNumber);
        }return null;
    }

    public ScoreDTO getTotalResultByTrainer(String trainerName) {
        List<Quiz> finishedQuizzes = quizRepository.findByIsFinishedIsTrue();
        int totalScore = 0;
        for (Quiz quiz : finishedQuizzes){

            switch (trainerName){
                case "Colin":
                    totalScore += quiz.getColinScore();
                    break;

                case "Anna":
                    totalScore += quiz.getAnnaScore();
                    break;

                case "Zsolt":
                    totalScore += quiz.getZsoltScore();
                    break;
                case "Thibyaa":
                    totalScore += quiz.getThibyaaScore();
                    break;
            }
        }
        return new ScoreDTO(totalScore);
    }

    public DisplayQuestionDTO findQuestionBodyByNumber(Long quizId, int questionNumber) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);
        if (optionalQuiz.isPresent()){
            Quiz quiz = optionalQuiz.get();
            Question question = quiz.getQuestions().get(questionNumber);
            return new DisplayQuestionDTO(question.getQuestion(),question.getOptionA(),question.getOptionB(),question.getOptionC(),question.getOptionD());
        }
        return null;
    }

    public List<Quiz> getQuizzesByUserName(String userName) {
        return quizRepository.findByUserNameEquals(userName);
    }
}

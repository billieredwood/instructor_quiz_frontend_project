package com.quiz.WhoAreYou.components;

import com.quiz.WhoAreYou.models.Question;
import com.quiz.WhoAreYou.models.Quiz;
import com.quiz.WhoAreYou.repositories.QuestionRepository;
import com.quiz.WhoAreYou.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuizRepository quizRepository;


    public DataLoader() {
    }


    public void run (ApplicationArguments args)throws Exception{

        Question question1 = new Question(
                "If you could be a biscuit, which one would you be and why?",
                "Hobnob (not chocolate, original only!)",
                "Ginger Nut - A good all-rounder",
                "Viennese w/Chocolate - I have no reason, I was just eating them the day before",
                "Wagon wheels - crunchy on the outside, soft on the inside",
                "D",
                "A",
                "B",
                "C");

        Question question2 = new Question(
                "If you ever got arrested, what would it most likely be for?",
                "Shouting at teenagers for littering (it’s a rite of passage for mums)",
                "Doing 22mph in a 20 zone",
                "I wouldn’t get arrested - I’d get away with whatever crime I commit :)",
                "Being obnoxiously loud or breaking spaghetti in half (the Italian police are ruthless",
                "D",
                "A",
                "B",
                "C");

        Question question3 = new Question(
                "What is your favourite chocolate?",
                "Kinder Bueno",
                "Wispa",
                "Ferrero Rocher",
                "Bounty, other answers, while respected, are factually wrong",
                "D",
                "A",
                "B",
                "C");

        Question question4 = new Question(
                "Imagine you can solve any problem with code. What is the first problem you’d like to solve?",
                "School websites",
                "Perfecting the milk:tea ratio",
                "Access to education for all children around the world",
                "Automating my cooking",
                "D",
                "A",
                "B",
                "C");

        Question question5 = new Question(
                "What is your dream job?",
                "This one!",
                "Luthier",
                "A cafe owning florist",
                "Teaching a bunch of rockstars",
                "D",
                "A",
                "B",
                "C");

        Question question6 = new Question(
                "What is your favourite genre of music?",
                "Folk/Country",
                "Blues",
                "I don’t have a favourite, it depends on my mood - hip hop?",
                "Metal",
                "D",
                "A",
                "B",
                "C");

        Question question7= new Question(
                "What is your spirit vegetable and why?",
                "Carrots, anything to improve my eyesight",
                "Cauliflower - affinity for cheese sauce",
                "Potatoes - I used to always wear really baggy clothes",
                "San Marzano tomato",
                "D",
                "A",
                "B",
                "C");

        Question question8 = new Question(
                "Where is your ideal holiday destination and why?",
                "France or Singapore - for the food",
                "A small town in Italy - peaceful, good weather and excellent food",
                "Jeju Island - peaceful and surrounded by water",
                "Perugia, Italy - amazing food, good coffee, lovely people, beautiful countryside",
                "D",
                "A",
                "B",
                "C");

        Question question9 = new Question(
                "What is the best bean?",
                "Chickpea",
                "Kidney",
                "@Repository",
                "A good arabica dark roast",
                "D",
                "A",
                "B",
                "C");

        Question question10 = new Question(
                "A zombie apocalypse takes the world by storm. What's your stance?",
                "No response",
                "Stock up on canned food and bunker down, I'm not a zombie snack",
                "I don't think there's much hope for me. My low athletic ability will lead to my imminent death. I guess I would just accept my fate?",
                "\"Ah frick, finally\"",
                "D",
                "A",
                "B",
                "C");


        questionRepository.save(question1);
        questionRepository.save(question2);
        questionRepository.save(question3);
        questionRepository.save(question4);
        questionRepository.save(question5);
        questionRepository.save(question6);
        questionRepository.save(question7);
        questionRepository.save(question8);
        questionRepository.save(question9);
        questionRepository.save(question10);

        Quiz quiz1 = new Quiz(
                "UserA",
                10);

        quizRepository.save(quiz1);

        quiz1.addQuestion(question1);
        quiz1.addQuestion(question2);
        quiz1.addQuestion(question3);
        quiz1.addQuestion(question4);
        quiz1.addQuestion(question5);
        quiz1.addQuestion(question6);
        quiz1.addQuestion(question7);
        quiz1.addQuestion(question8);
        quiz1.addQuestion(question9);
        quiz1.addQuestion(question10);

        quizRepository.save(quiz1);
    }

}

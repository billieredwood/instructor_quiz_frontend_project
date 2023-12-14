# Front-End 'Who Are You?' BNTA Instructor Quiz Documentation  

## About:

The following documentation provides the details of the front-end BNTA Instructor Quiz application, which has been developed to support its pre-made back-end WhoAreYou Quiz application.

Contributors to this project include:

- Faiz Fazaluddin
- Ryan Nekadio
- Saima Miah
- Sam Greenfield-Lee
- Billie Redwood


## Project aims:
The application is designed to track user responses to a set of questions within a single, short-form quiz. The tracked input ranges from selected response-types of A to D across each question and is accumulated to match the user to the BNTA Trainer whose personality aligns with their most commonly selected response-type. Following completion of the quiz, this value - being the most common of the scores - is then displayed to the user on the Results page along with their Trainer match's bio.

The application's UX was developed with consideration given to BNTA student, alumni & colleague persona types, with ages therefore spanning multi-generations.

The app is aimed to appeal to users of varied educational backgrounds, seeking a fun, engaging and simple to use short-form gaming experience that can be shared with their BNTA peers.


## The names and versions of any libraries used
- [Backend_Project_API](https://github.com/AanisN10/Backend_Project_API) (Using: Spring Boot, Spring Data JPA, Spring Web, Postgresql)
-  React (and React Router Dom)

## Step-by-step setup instructions
1. Clone the project Git Hub repository in your local directory from:
   ```
   https://github.com/billieredwood/instructor_quiz_frontend_project
   ```
2. Open the Back-End Project API and open the WhoAreYou folder in IntelliJ and navigate to the terminal; from there, create the local database with the following command:
   ```
   createdb WhoAreYou
   ```
   &
   in the WhoAreYouApplication class, run the project.
3. Open the front-end project client in VSCode and from the Terminal, insert the following commands to start the applciation in the browser:
4. ```
   npm i
   npm i react-router-dom
   npm start
   ```
5. Play your first quiz.

### Persona 
- BNTA students come from a diverse background, and are passionate and curious individuals which embarked on the journey of fullstack development through a challenging but rewarding bootcamp.
- Students are seeking balance and believes that a positive and supportive community is key to a successful learning experience.
- A fun and interactive app is crucial to help students to unwind and energize after long coding sessions. 
- They are seeking a platform that goes beyond coding – a priority is to connect with fellow peers that are like-minded, to share experiences of the ups and downs of their coding journey.
- A visually appealing app is essential with colorful and lively interfaces.
- Students values an app that is easy to navigate. After a day of intense coding, they wants a seamless experience without any unnecessary complexities.



## Details of the project's MVP and any extensions covered:
## MVP
- Enable the user to select the option to start a quiz from the homepage. ✅
- Enable the user to play a quiz comprising a set amount of questions. ✅
- Enable the user to select answers to the quiz's questions and navigate to its next question using a 'next' button. ✅
- Add functionality to save the above user input to each of the quiz's questions. ✅
- Add functionality to calculate the most frequently selected answers in the quiz. ✅ 
- Add routes to create a homepage, quiz game page and results page.✅
- Enable consistent styling across the application using CSS. ✅

## Extensions
- Add a ranking of the user's scores for each BNTA trainer to the Results page, underneath their trainer match. ✅
- Add a homepage navigation button. ✅
- Enable the user to input a username that is associated & select the option to start a quiz.
- Replace the button to 'learn more' with a drop down style display on the Results page's matched-trainer bio section.
- Add a back button to each question on the Quiz page to enable the user navigate to the previous question with the quiz.
- Add a menu of question number buttons from which the user can select specific questions to revisit & edit their answers to before quiz completion.
- Add a Review Answers page to enable the user to review all of their question and answer combinations, with the option to edit before clicking 'submit'.

## Relevant diagrams - wireframe & component diagram:

![image](https://github.com/billieredwood/instructor_quiz_frontend_project/assets/131786678/4b77897f-a08f-4ffd-a722-c0ebd6ba5afc)

---------

![image](https://github.com/billieredwood/instructor_quiz_frontend_project/assets/131786678/7b11c8be-f438-4546-b8b5-56e2cdd2cda8)

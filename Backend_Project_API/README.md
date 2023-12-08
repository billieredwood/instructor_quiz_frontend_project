# WhoAreYou Personality Quiz

## Project Description

The WhoAreYou Quiz App is a web application that allows users to create and take quizzes.

## Developers

- [Aanis](https://github.com/AanisN10)
- [Janet](https://github.com/JMen121)
- [Piraven](https://github.com/PiravenNan)
- [Suzi](https://github.com/sctowers)

## Libraries Used

- Spring Boot
- Spring Data JPA
- Spring Web
- Postgresql

## Setup Instructions

1. Clone the repository:

```bash
git  clone https://github.com/AanisN10/Backend_Project_API
```

2. Navigate to the project directory

```bash
cd WhoAreYou
```

3. Ensure you have Java, Maven and PostgreSQL installed and running. Create the database:

```bash
createdb WhoAreYou
```

4. Build the project

```bash
mvn clean install
```

5. Start the application

```bash
mvn spring-boot:run
```

6. Access the application at [https://localhost:8080](https://localhost:8080)

## Example demo run

1. Randomize Quiz

   - Method: `POST`
   - URL: `localhost:8080/quizzes/random?userName=UserA&numberOfQuestions=3`
   - Created a quiz randomized with 3 questions for user UserA.

2. Start quiz

   - Method: `GET`
   - URL: `localhost:8080/quizzes/startQuiz/2`
   - this will start the quiz with id 2.

3. Display Question

   - Method: `GET`
   - URL: `localhost:8080/quizzes/displayQuestion/quizId/0`
   - this will display the first question of the quiz

4. User answer to quiz

   - Method: `POST`
   - URL: `localhost:8080/quizzes/takeQuiz/2`
   - this allows the user to answer the first question of the quiz, continue until questions have been answered.
   - Example:
     ```json
     {
       "questionNumber": 0,
       "userAnswer": "B"
     }
     ```

5. Finish Quiz

   - Method: `GET`
   - URL: `localhost:8080/quizzes/finishQuiz/2`
   - this will end quiz 2

6. Check quiz

   - Method: `GET`
   - URL: `localhost:8080/quizzes/2`
   - this will show all the questions as well as the users response, the user would be allowed to change the answers to previous responses if the quiz is not finished.

7. Display result

   - Method: `GET`
   - URL: `localhost:8080/quizzes/quizId/result?trainerName=Thibyaa`
   - This will display the user's score for that given trainer and that trainer's bio.

8. Display total

   - Method: `GET`
   - URL: `localhost:8080/quizzes/allResults?trainerName=Anna`
   - This will display the accumulated scores for all finished quizzes for a specific trainer

9. Display all quizzes for user
   - Method: `GET`
   - URL: `localhost:8080/quizzes/allQuizzes?userName=UserA`
   - This will display the quizzes associated with UserA, can be used to allow the user to resume a quiz or look at completed ones

## Example Routes

### Questions

- **Get All Questions:**

  - Method: `GET`
  - URL: `/questions`

- **Get Question by ID:**

  - Method: `GET`
  - URL: `/questions/{id}`

- **Create New Question:**

  - Method: `POST`
  - URL: `/questions`
  - Request Body: JSON with question details
  - Example:
    ```json
    {
      "question": "If you could be a biscuit, which one would you be and why?",
      "zsoltAnswer": "D",
      "annaAnswer": "A",
      "colinAnswer": "B",
      "thibyaaAnswer": "C",
      "optionA": "Hobnob (not chocolate, original only!)",
      "optionB": "Ginger Nut - A good all-rounder",
      "optionC": "Viennese w/Chocolate - I have no reason, I was just eating them the day before",
      "optionD": "Wagon wheels - crunchy on the outside, soft on the inside",
      "quizIds": [1]
    }
    ```

- **Update Question by ID:**

  - Method: `PATCH`
  - URL: `questions/{id}`
  - Request Body: JSON with question details

- **Delete Question by ID:**
  - Method: `DELETE`
  - URL: `/questions/{id}`

### Quizzes

- **Get All Quizzes:**

  - Method: `GET`
  - URL: `/quizzes`

- **Get Quiz by ID:**

  - Method: `GET`
  - URL: `/quizzes/{id}`

- **Delete Quiz by ID:**

  - Method: `DELETE`
  - URL: `/quizzes/{id}`

- **Add Question To Quiz by ID:**

  - Method: `POST`
  - URL: `quizzes/{id}`
  - Request Body: JSON with question details
  - Example:

  ```json
  {
    "questionIds": [1, 10]
  }
  ```

- **Remove Question From Quiz by ID:**

  - Method: `POST`
  - URL: `quizzes/removeQuestion/{id}`
  - Request Body: JSON with question details
  - Example:

  ```json
  {
    "questionIds": [1, 10]
  }
  ```

- **Start Quiz by ID:**

  - Method: `GET`
  - URL: `quizzes/startQuiz/{id}`

- **Get Random Quiz:**

  - Method: `POST`
  - URL: `localhost:8080/quizzes/random?userName={}&numberOfQuestions={}`

- **Get All Quizzes for User:**

  - Method: `GET`
  - URL: `localhost:8080/quizzes/allQuizzes?userName=`

- **Answer a Question in a Quiz by Quiz ID:**

  - Method: `POST`
  - URL: `quizzes/takeQuiz/{quizID}`
  - Request Body: JSON with question ID and user answer details
  - Example:

  ```json
  {
    "questionNumber": 1,
    "userAnswer": "B"
  }
  ```

- **Finish Quiz by Quiz ID:**

  - Method: `GET`
  - URL: `quizzes/finishQuiz/{quizId}`

- **Get Trainer Result by Quiz ID:**

  - Method: `GET`
  - URL: `quizzes/{quizId}/result`

- **Get Question By Question Number**

  - Method: `GET`
  - URL: `quizzes/{quizID}/questionNumber`

- **Get Total Result by Trainer**
  - Method: `GET`
  - URL: `quizzes/allResults`

### MVP and Extensions

- The MVP includes basic CRUD operations for quizzes and questions.

  - Add quiz questions
  - Display all quiz questions
  - Return a list of quiz questions with a shared property (provide “a quiz”)
  - Implement this firstly as a hard-coded route and then in relation to a Quiz object
  - Update a quiz question
  - Delete a quiz question
  - Provide a result/score based on the answers

- Extensions include features like
  - Starting a quiz
  - Creating a random quiz
  - Submitting answers
  - Can change responses until submission

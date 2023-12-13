import Home from "../components/Home";
import Question from "../components/Question";
import ResultsPage from "../components/ResultsPage";
import { useState, useEffect } from "react";
import { createBrowserRouter, RouterProvider, Link} from "react-router-dom";


const QuizContainer = () => {
    const [stateUserName, setStateUserName] = useState("")
    const [currentQResponse, setCurrentQResponse] = useState({
        // questionNumber: null, 
        // userAnswer: ""
    }) //null as opposed to false bc a is fed int as a response
    const [currentQ, setCurrentQ] = useState({})
    // const [qAnswered, setQAnswered] = useState([])
    // const [listOfQs, setListOfQs] = useState([])
    const [quizFinished, setQuizFinished] = useState(null)
    const [currentQuiz, setCurrentQuiz] = useState(null)
    const [questionIndex, setQuestionIndex] = useState(0)
    const [trainersList, setTrainersList] = useState({})
    

    const loadQuizData = async () => {
        const response = await fetch (`http://localhost:8080/quizzes/random?userName=${stateUserName}&numberOfQuestions=3`, 
        {
            method: "POST",
            headers: {"Content-Type": "application/json"}
        })
        const quizData = await response.json()
        setCurrentQ(quizData.questions[0])
        setCurrentQuiz(quizData)
        // console.log(currentQuiz)
    }

    useEffect(() =>{
        loadQuizData();
    }, [])

    useEffect(() => {
        console.log(questionIndex);
        if(currentQuiz){
            setCurrentQ(currentQuiz.questions[questionIndex])
        }
        if(questionIndex > 2) {
            finishQuiz()
        } 
    }, [questionIndex])


    const startQuiz = async () => {
        if (currentQuiz) {
            const quiz = await fetch(`http://localhost:8080/quizzes/startQuiz/${currentQuiz.id}`);
            console.log(quiz)
            // const data = await quiz.json()
            localStorage.setItem("questionIndex", 0)
            setQuestionIndex(0);
        }
    }
    
    // const startQuiz = async () => {
    //     const quiz = await fetch (`http://localhost:8080/quizzes/startQuiz/${currentQuiz.id}`)
    //     localStorage.setItem("questionIndex", 0) 
    // }

    const postAnswer = async () => {
        console.log(currentQResponse)
        // console.log(currentQuiz)
        const response = await fetch(`http://localhost:8080/quizzes/takeQuiz/${currentQuiz.id}`, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(currentQResponse)
        })
        localStorage.setItem("questionIndex", parseInt(localStorage.getItem("questionIndex")) + 1) // updating counter by 1 
        setQuestionIndex(questionIndex+1)

    }

    const finishQuiz= async () => {
        const response = await fetch (`http://localhost:8080/quizzes/finishQuiz/${currentQuiz.id}`)
        // const quizData = await response.json()
        console.log(response);
        checkQuiz()
    }

    const checkQuiz= async () => {
        const response = await fetch (`http://localhost:8080/quizzes/${currentQuiz.id}`)
        const quizData = await response.json()
        console.log(quizData)
    }

    // const displayResult =async ()=>{
        
    // }


    const handleStartQuiz = () => {
        if (currentQuiz) {
            startQuiz();
        }
    }
    

    // const handleFormSubmit = ((event) => {
    //     console.log(event)
    //     event.preventDefault();
    //     postAnswer()
    //     localStorage.setItem("questionIndex", parseInt(localStorage.getItem("questionIndex")) + 1) // updating counter by 1 
    // })

    const handleQResponse = ((event) => {
        event.preventDefault();
        const buttonQResponseClick = event.target.value
        const updatedResponse = {...currentQResponse}
        updatedResponse.questionNumber = questionIndex
        updatedResponse.userAnswer = buttonQResponseClick
        setCurrentQResponse(updatedResponse)
        // console.log(currentQResponse)
        console.log(updatedResponse)
    })

    const quizRoutes = createBrowserRouter ([
        {
            path: "/",
            element: <Home handleStartQuiz={handleStartQuiz}/>,
            children: [
                {
                    path: "/question",
                    element: < Question 
                        question = {currentQ} 
                        onButtonClick = {handleQResponse}
                        postAnswer = {postAnswer}
                        questionIndex = {questionIndex}
                        finishQuiz = {finishQuiz}
                        checkQuiz = {checkQuiz}
                    />
                }, 
                {
                path: "/question/results",
                element: < ResultsPage />
                }
            ]
        }
    ])

    return (  
        <>
            {/* <h1><Link to = "/question">BNTA Trainers Quiz!</Link></h1> */}
           
            <RouterProvider router = {quizRoutes}/>
        </>
    );
}
 
export default QuizContainer;
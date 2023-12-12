import Question from "../components/Question";
import ResultsPage from "../components/ResultsPage";
import { useState, useEffect } from "react";


const QuizContainer = () => {
    const [stateUserName, setStateUserName] = useState("")
    const [currentQResponse, setCurrentQResponse] = useState(null) //null as opposed to false bc a is fed int as a response
    const [qAnswered, setQAnswered] = useState([])
    const [listOfQs, setListOfQs] = useState([])
    const [quizFinished, setQuizFinished] = useState(null)

    const loadQuizData = async () => {
        const response = await fetch (`http://localhost:8080/quizzes/random?userName=${stateUserName}&numberOfQuestions=3`, 
        {
            method: "POST",
            headers: {"Content-Type": "application/json"}
        })
        const quizData = await response.json()
        console.log(quizData)


    }
    useEffect(() => {
        loadQuizData()
    }, [])

    return (  
        <>
            <h1>BNTA Trainers Quiz!</h1>
            <Question/>
            <ResultsPage/>
        </>
    );
}
 
export default QuizContainer;
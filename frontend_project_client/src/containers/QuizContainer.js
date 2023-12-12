import Home from "../components/Home";
import Question from "../components/Question";
import ResultsPage from "../components/ResultsPage";
import { useState, useEffect } from "react";
import { createBrowserRouter, RouterProvider, Link} from "react-router-dom";


const QuizContainer = () => {
    const [stateUserName, setStateUserName] = useState("")
    const [currentQResponse, setCurrentQResponse] = useState({}) //null as opposed to false bc a is fed int as a response
    const [currentQ, setCurrentQ] = useState({})
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
        setCurrentQ(quizData.questions[0])
        console.log(quizData.questions[0])
    }


    useEffect(() => {
        loadQuizData()
    }, [])





    const handleQResponse = ((event) => {
        event.preventDefault();
        const buttonQResponseClick = event.target.value
        setCurrentQResponse(buttonQResponseClick)
    })

    const quizRoutes = createBrowserRouter ([
        {
            path: "/",
            element: <Home />,
            children: [
                {
                    path: "/question",
                    element: < Question 
                        question = {currentQ} 
                        onButtonClick = {handleQResponse}
                    />

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
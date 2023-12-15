import Home from "../components/Home";
import Question from "../components/Question";
import ResultsPage from "../components/ResultsPage";
import { useState, useEffect } from "react";
import { createBrowserRouter, RouterProvider, Link} from "react-router-dom";
import {annaImages, zsoltImages, thibyaaImages, colinImages} from "../imagePaths/images";


const QuizContainer = () => {
    const [stateUserName, setStateUserName] = useState("")
    const [currentQResponse, setCurrentQResponse] = useState({
        questionNumber: null, 
        userAnswer: ""
    }) //null as opposed to false bc a is fed int as a response
    const [currentQ, setCurrentQ] = useState({})
    // const [qAnswered, setQAnswered] = useState([])
    // const [listOfQs, setListOfQs] = useState([])
    const [quizFinished, setQuizFinished] = useState(null)
    const [currentQuiz, setCurrentQuiz] = useState(null)
    const [questionIndex, setQuestionIndex] = useState(0)
    const [trainersList, setTrainersList] = useState([])
    const [trainerScores, setTrainerScores] = useState({})
    
    console.log(annaImages)

    const loadQuizData = async () => {
        const response = await fetch (`http://localhost:8080/quizzes/random?userName=${stateUserName}&numberOfQuestions=5`, 
        {
            method: "POST",
            headers: {"Content-Type": "application/json"}
        })
        const quizData = await response.json()
        setCurrentQ(quizData.questions[0])
        setCurrentQuiz(quizData)
        localStorage.setItem("questionIndex", 0)
        setQuestionIndex(0);

        // console.log(currentQuiz)
    }

    useEffect(() =>{

        if(currentQuiz){
            startQuiz()
        }
    }, [currentQuiz])



    useEffect(() => {
        console.log(questionIndex);
        if(questionIndex > 4) {
            finishQuiz()
            setCurrentQ(null)
        }
        else if(currentQuiz){
            setCurrentQ(currentQuiz.questions[questionIndex])
        }

    }, [questionIndex])

    useEffect(()=>{

    },[currentQResponse])

    useEffect(() =>{
        if(trainerScores.length === 4){
            createTrainersList();
            console.log("scores");
        }
    }, [trainerScores])

    useEffect(() =>{
        console.log(trainersList);
        if (trainersList.length>0 && trainersList.length<4){
            displayResult(trainerScores[trainersList.length])
        }

    }, [trainersList])


    const startQuiz = async () => {
        if (currentQuiz) {
            const quiz = await fetch(`http://localhost:8080/quizzes/startQuiz/${currentQuiz.id}`);
            console.log(quiz)
            // const data = await quiz.json()
            // localStorage.setItem("questionIndex", 0)
            // setQuestionIndex(0);
        }
    }

    const postAnswer = async () => {
        console.log(currentQResponse)
        // console.log(currentQuiz)
        const response = await fetch(`http://localhost:8080/quizzes/takeQuiz/${currentQuiz.id}`, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(currentQResponse)
        })
        setCurrentQResponse(null)
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

        const scores = [
            ["Anna", quizData.annaScore, "You are fuelled by medium brewed tea with just a *drop* of milk, hobnobs & jaffa cakes.", annaImages],
            ["Colin", quizData.colinScore, "You are a reserved, tortoise-loving individual of few words.", colinImages],
            ["Thibyaa", quizData.thibyaaScore,"Some might say, you are a \“looks like a cinnamon roll could kill you\” kinda person.", thibyaaImages],
            ["Zsolt", quizData.zsoltScore,"…Or in other words, a \“caffeine-overdosed golden retriever.\”", zsoltImages ]
        ]

        const sortedScores = scores.sort((a,b)=>b[1]-a[1])
        console.log(sortedScores)
        setTrainerScores(sortedScores)
    }

    const displayResult = async (trainer) => {
        const trainerResponse = await fetch (`http://localhost:8080/quizzes/${currentQuiz.id}/result?trainerName=${trainer[0]}`)
        console.log(trainerResponse)
        const trainerData = await trainerResponse.json()
        console.log(trainerData)
        const trainerObject = {
            name: trainer[0],
            score: trainer[1],
            message: trainerData.message,
            personality: trainer[2],
            images: trainer[3]
        }
        console.log(trainerObject)
        setTrainersList([...trainersList, trainerObject])
    }

    const createTrainersList = () => {
        // trainerScores.forEach(trainer => {
            console.log(trainerScores);
            displayResult(trainerScores[0])
            // displayResult(trainerScores[1])
            // displayResult(trainerScores[2])
            // displayResult(trainerScores[3])
        // });
    }


    const handleStartQuiz = () => {
        // if (currentQuiz) {
        //     startQuiz();
        // }
        setCurrentQResponse(null)
        setCurrentQ(null)
        setTrainersList([])
        loadQuizData();
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
                        currentQResponse = {currentQResponse}
                    />
                }, 
                {
                path: "/question/results",
                element: < ResultsPage results={trainersList}/>
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
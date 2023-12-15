import { useState } from "react";
import Home from "./Home";
import { Link, useNavigate } from "react-router-dom";
import tortoise from "../ColinImages/ColinPet3.jpg"

const Question = ({question, onButtonClick, postAnswer, questionIndex, finishQuiz, checkQuiz}) => {
    
    const navigate = useNavigate();
    
    if (!question){
        return (
            <>
            <p>Loading...</p>
            <img className="loading" src={tortoise} />
            </>)
    }



    const handleFormSubmit = ((event) => {
        event.preventDefault();
        postAnswer()
        if(questionIndex >= 2) {
            navigate("/question/results")
        } 
    })

    // const submitButton = () => {
    //     if(questionIndex < 2) {
    //         return <button type="submit"> Next </button>
    //     } else {
    //         return <button type="submit"> Finish Quiz! </button>
    //     }
        
    // }

    return (  
        <>
            <h2 className="question">Question {questionIndex+1}: {question.question}</h2>
            <form  onSubmit={handleFormSubmit}> 
                <div id = "options">
                    <button className="optionButtons" onClick={onButtonClick} value="A"> {question.optionA} </button>
                    <button className="optionButtons" onClick={onButtonClick} value="B"> {question.optionB} </button>
                    <button className="optionButtons" onClick={onButtonClick} value="C"> {question.optionC} </button>
                    <button className="optionButtons" onClick={onButtonClick} value="D"> {question.optionD} </button>
                </div>
                <button type="submit"> {questionIndex < 2 ? "Next" : "Finish Quiz!" } </button>
            </form>
            
            
        </>
    );
}
 
export default Question;

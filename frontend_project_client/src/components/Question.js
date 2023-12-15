import { useState } from "react";
import Home from "./Home";
import { Link, useNavigate } from "react-router-dom";
import tortoise from "../ColinImages/ColinPet3.jpg"

const Question = ({question, onButtonClick, postAnswer, questionIndex,currentQResponse}) => {
    
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
        if(questionIndex >= 4) {
            navigate("/question/results")
        } 
    })

    const clicked = (value)=>{
        if (currentQResponse){
            return currentQResponse.userAnswer ===value?"clicked":"unclicked"
        }

    }

    return (  
        <>
            <h2 className="question">Question {questionIndex+1}: {question.question}</h2>
            <form  onSubmit={handleFormSubmit}> 
                <div id = "options">
                    <button id={clicked("A")} className="optionButtons" onClick={onButtonClick} value="A"> {question.optionA} </button>
                    <button id={clicked("B")} className="optionButtons" onClick={onButtonClick} value="B"> {question.optionB} </button>
                    <button id={clicked("C")} className="optionButtons" onClick={onButtonClick} value="C"> {question.optionC} </button>
                    <button id={clicked("D")} className="optionButtons" onClick={onButtonClick} value="D"> {question.optionD} </button>
                </div>
                <button type="submit"> {questionIndex < 4 ? "Next" : "Finish Quiz!" } </button>
            </form>
            
            
        </>
    );
}
 
export default Question;

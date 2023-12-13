import { useState } from "react";
import Home from "./Home";

const Question = ({question, onButtonClick, onFormSubmit}) => {

  

    return (  
        <>
            <h2>{question.question}</h2>
            <form  onSubmit={onFormSubmit}> 
                <div id = "options">
                    <button onClick={onButtonClick} value="A"> {question.optionA} </button>
                    <button onClick={onButtonClick} value="B"> {question.optionB} </button>
                    <button onClick={onButtonClick} value="C"> {question.optionC} </button>
                    <button onClick={onButtonClick} value="D"> {question.optionD} </button>
                </div>
                <button type="submit"> Next </button>
            </form>
            
            
        </>
    );
}
 
export default Question;

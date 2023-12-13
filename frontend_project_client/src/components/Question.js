import { useState } from "react";

const Question = ({question, onButtonClick, postAnswer, questionIndex}) => {
    
    const handleFormSubmit = ((event) => {
        event.preventDefault();
        postAnswer()
    })

    return (  
        <>
            <h2>Question {questionIndex+1}: {question.question}</h2>
            <form  onSubmit={handleFormSubmit}> 
                <div>
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

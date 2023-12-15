import { Link } from "react-router-dom";
import React, { useState } from 'react';
import sonic from "../ZsoltImages/ZsoltHedgehog1.jpg"


const ResultsPage = ({ results }) => {
    const [showMore, setShowMore] = useState(false);
    const [showTrainers, setShowTrainers] = useState(false);

    console.log(results)

    if (!results.length){
        return (
        <>
        <p>Loading...</p>
        <img className="loading" src={sonic} />
        </>)
    }

    console.log(results.length);

    let otherTrainers=null

    if (results.length==4){
        const splicedResults = [...results].splice(1,3)
        console.log(splicedResults);
        otherTrainers = splicedResults.map((trainer, index)=>{
            return(
            <li key={index}>
                <p>{trainer.name}</p>
                <p>Score: {trainer.score}</p>
                <p>{trainer.personality}</p>

            </li>)
        })
        console.log(otherTrainers);
    }
    
    const trainerImages = [...results[0].images].splice(1).map(path => {
        return <img id = "extraImages" src = {path}/>
    })

        // }else{
        //     return <p>Loading trainers...</p>
        // }


   
    
    return ( 
        <div className="results-container">
            <h2>You are {results[0].name}!</h2>
            <img id = "mainImage" src = {results[0].images[0]}/>
            <p>{results[0].personality}</p>
            {showMore &&  (
            <div>
                <p>{results[0].message}</p>
                {trainerImages}
            </div>
            )}

            
            <button className="see-more-btn" onClick={() => setShowMore(!showMore)}>
            {showMore ? 'See less' : 'See more'}
            </button>

            <button className="see-more-btn" onClick={() => setShowTrainers(!showTrainers)}>
            {showTrainers ? 'See less' : 'See how you compare to other trainers!'}
            </button>
            
            {showTrainers && (
            <>
                <h2>How you compare to other trainers!</h2>
                <ul>
                    {results.length>=4? otherTrainers:<li>Loading trainers...</li>}
                </ul>
            </>
            )}
            {/* {otherTrainers} */}
            {/* {} */}
            
        </div> 
    );
}
 
export default ResultsPage;
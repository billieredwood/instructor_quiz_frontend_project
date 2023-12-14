import { Link } from "react-router-dom";
import React, { useState } from 'react';


const ResultsPage = ({ results }) => {
    const [showMore, setShowMore] = useState(false);

    console.log(results)

    if (!results.length){
        return <p>Loading...</p>
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
                    <p>{trainer.score}</p>
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
        <>
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
            <ul>
                {results.length>=4? otherTrainers:<li>Loading trainers...</li>}
            </ul>
            {/* {otherTrainers} */}
            {/* {} */}
            
        </>
    );
}
 
export default ResultsPage;
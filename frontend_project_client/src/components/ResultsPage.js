import { Link } from "react-router-dom";

const ResultsPage = ({ results }) => {

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
        


        // }else{
        //     return <p>Loading trainers...</p>
        // }


   
    
    return (  
        <>
            <h2>You are {results[0].name}!</h2>
            <img alt = "Trainer image"></img>
            <p>{results[0].message}</p>
            <ul>
                {results.length>=4? otherTrainers:<li>Loading trainers...</li>}
            </ul>
            {/* {otherTrainers} */}
            {/* {} */}
            
        </>
    );
}
 
export default ResultsPage;
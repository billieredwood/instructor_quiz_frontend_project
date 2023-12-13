import { Link } from "react-router-dom";

const ResultsPage = ({ results }) => {

    console.log(results)

    return (  
        <>
            <h2>You are {results[0].name}!</h2>
            <img alt = "Trainer image"></img>
        </>
    );
}
 
export default ResultsPage;
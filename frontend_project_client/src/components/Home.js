import { Link, Outlet } from "react-router-dom";
import { useLocation, useNavigate } from "react-router-dom";
import trainers from "../trainers.jpg"


const Home = ({handleStartQuiz}) => {
    const location = useLocation();
    const navigate = useNavigate()
    console.log(trainers)

    const handleHome = ()=>{
        navigate("/")
    }

    const renderHomePage = location.pathname === '/';
    return (
    <>
        <header><button onClick={handleHome} className="homepage">Which Trainer Are You?</button></header>
        {renderHomePage && (
        <>
            <hr/>
            <section className="main-homepage">
                <h1 className="home">BNTA TRAINER QUIZ</h1>
                <img className="trainers" src={trainers} alt="Image of trainers" />
                <p className="about-text"> Discover your BNTA trainer match with our interactive quiz!</p>
                <p className="about-text"> Enter your username and click start:</p>
                <form id="homepage-form">
                    <input id="username"
                        placeholder="Enter a username" 
                        type="text"
                    />
                    <Link to="/question" onClick={handleStartQuiz}><button >Start quiz</button></Link>
                </form>
            </section>
        </>
        )}
        <Outlet />
    </>
    );
};
 
export default Home;
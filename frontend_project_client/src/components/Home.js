import { Link, Outlet } from "react-router-dom";
import { useLocation } from "react-router-dom";
import logo from "../logo.svg";

const Home = ({handleStartQuiz}) => {
    const location = useLocation();
 
    const renderHomePage = location.pathname === '/';
    return (
    <>
        {renderHomePage && (
        <>
            <header className="homepage">Which Trainer Are You?</header>
            <hr/>
            <section className="main-homepage">
                <h1 className="home">BNTA TRAINER QUIZ</h1>
                <img className="App-logo" src={logo} alt="Image of trainers"/>
                <p>Discover your BNTA trainer match with our interactive quiz!</p>
                <p>Enter your username and click start:</p>
                <form id="homepage-form">
                    <input 
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
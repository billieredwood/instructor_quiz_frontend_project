import { Link, Outlet } from "react-router-dom";
import { useLocation } from "react-router-dom";

const Home = () => {
    const location = useLocation();

    const renderHomePage = location.pathname === '/';
    return (
    <>
        {renderHomePage && (
        <>
            <h1>Home Page</h1>
            <p><Link to="/question">Start Quiz</Link></p>
         </>
        )}
        <Outlet />
    </>
    );
};
 
export default Home;
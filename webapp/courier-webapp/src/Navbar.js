import { Link } from "react-router-dom";

const Navbar = () => {
    return (    
         <nav className="navbar">
    <h1>Dispatch</h1>
            <div className="links" >
                <Link to="/">How it works</Link>
                <Link to="/">Who we are</Link>
                <Link to="/">Download the mobile app</Link>
                <Link to="/">Contact us</Link>
                <Link to="/">Login</Link>
            </div>
    
            </nav>
            
         ); 
}
 
export default Navbar;
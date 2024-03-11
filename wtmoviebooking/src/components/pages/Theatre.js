import React from 'react';
import Footer from '../Footer';
import Moviemain from '../Moviemain';
import Loginmain from '../Loginmain'
import Theatremain from '../Theatremain'
import Logincontroller from '../Logincontroller';
import { useLocation } from "react-router-dom";
import { ReactSession } from 'react-client-session';
import Navbar from '../Navbar';
import { Apicalls } from '../Apicalls'
import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Theatre() {
//     const TheatreComponent = () => {
  const location = useLocation();
  const passedProps = location.state;
  const[movies,setMovies]=useState(false);
  const navigate = useNavigate();

console.log("passedProps", passedProps);
ReactSession.set("movieName", passedProps.movieName);
ReactSession.set("popularity",passedProps.popularity);
ReactSession.set("originalLanguage",passedProps.originalLanguage);
ReactSession.set("isAdult",passedProps.isAdult);
//   // Access the passed props here and use them as needed
// };
// if (passedProps.adult === "false") {
//   console.log(passedProps.adult);
// navigate("/theatre");
// } else {
//   navigate("/verify");
// }
// const handleRedirect = () => {
//   if (passedProps.adult === "false") {
//     navigate("/theatre");
//   } else {
//     navigate("/verify");
//   }
// }
  return (
    <div> 
      <Navbar />
   <Theatremain />
   {/* <button onClick={handleRedirect}>Redirect</button> */}

   <Footer/>
    </div>
  )
}

export default Theatre

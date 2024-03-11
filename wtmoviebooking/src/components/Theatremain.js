// Material UI imports
import Chip from "@mui/material/Chip";
import FaceIcon from "@mui/icons-material/Face";
import Paper from "@mui/material/Paper";
import LockIcon from "@mui/icons-material/Lock";
import { useEffect } from "react";
import { Apicalls } from './Apicalls'
import Switch from "@mui/material/Switch";
import { useState } from "react";
import Loginmain from "./Loginmain";
import Signup from "./Signup";
import { WidthFull } from "@mui/icons-material";
import { ReactSession } from 'react-client-session';
import { useNavigate } from "react-router-dom";

function Theatremain() {
//   const ot = ReactSession.get("original_title")
//   const pop = ReactSession.get("popularity");
//   const lan = ReactSession.get("original_language");
  const[movies,setMovies]=useState(false);
  const [theaters, setTheaters] = useState([]);
  const [selectedTheater, setSelectedTheater] = useState("");
  var [resp, setResp] = useState(false);
  var [movName, setMovName] = useState("");
  const navigate = useNavigate();


var movsta;
  useEffect(() => {
  
   
    const mdetail = {
        movieName: ReactSession.get("movieName"),
        cbfcrating: ReactSession.get("popularity"),
        moviegenre: ReactSession.get("originalLanguage")
      };
      const mdetail1 = {
        movieName: ReactSession.get("movieName"),
        
      };
   movsta = ReactSession.get("movieName")
//   Apicalls.Movies(mdetail).then(response=> {
//     setMovies(true)
//   }).catch(error => {
//     const error1=error;
//   });
Apicalls.CheckMovie(mdetail1)
      .then(response => {
        console.log(response);
        const movieExists = response.data.movieName;
        const moviefromWeb = response.data.id;
        setMovName(mdetail.movieName);
        console.log(movieExists); // Assuming the response has a boolean field indicating if the movie exists
        console.log(moviefromWeb);
        ReactSession.set("moviefromWeb",moviefromWeb);
        // Movie details are already present in the database
        setMovies(true);
        getTheatres(moviefromWeb);
      })
      .catch(error => {
        console.error(error);
      });
  
}, [ReactSession.get("movieName"), ReactSession.get("popularity"), ReactSession.get("originalLanguage")]);

const handleTheaterSelect = (event) => {
  setSelectedTheater(event.target.value);
};

const handleSubmit = (event) => {
  const ol = ReactSession.get("isAdult");
  const theatreObject = theaters.filter(theater => theater.theatreName === selectedTheater);
  console.log("theatre object id = " + theatreObject[0].id);
  ReactSession.set("tid", theatreObject[0].id);
  ReactSession.set("theatrename", theatreObject[0].theaterName);
  //const ve= ReactSession.get("verify");
  if(ol === "false"){ //&& ve === true){
   ReactSession.set("movieName",movName);
    event.preventDefault();
    // Perform any desired action with the theaterName value
    // Reset the input value
    navigate("/tpage")
  }
  else{
    ReactSession.set("movieName",movName);
    event.preventDefault();
    // Perform any desired action with the theaterName value
    // Reset the input value
    navigate("/verify")
    // navigate("/verify");
  }

  };
  const getTheatres = (movieId) => {
    fetch('http://localhost:8083/api/theatres/m/'+ movieId).then(
      response => response.json()
    ).then(data => {
      setTheaters(data);
      // console.log(data)
      // var tid = data.id;
      // ReactSession.set("tid",tid);
      // ReactSession.set("theatrename",theaterName);
      // console.log(verifyEmail);
      // console.log(emailInput);
      // console.log(username);
    // navigate("/tpage")
    }, (e) =>{
      console.log(e);
    })}
   
  return (
    <div style={styles.container}>
      <form onSubmit={handleSubmit} style={styles.form}>
        <select
          value={selectedTheater}
          onChange={handleTheaterSelect}
          style={styles.select}
        >
          <option value="">Select a theater</option>
          {theaters.map(theater => (
            <option key={theater.id} value={theater.theatreName}>{theater.theatreName}</option>
          ))}
        </select>
        <button type="submit" style={styles.button}>
          Submit
        </button>
      </form>
    </div>
);
}
const styles = {
  container: {
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    height: "100vh",
  },
  form: {
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    backgroundColor: "#f2f2f2",
    padding: "20px",
    borderRadius: "10px",
  },
  select: {
    width: "300px",
    padding: "10px",
    marginBottom: "10px",
    borderRadius: "5px",
    border: "1px solid #ccc",
  },
  button: {
    padding: "10px 20px",
    backgroundColor: "#007bff",
    color: "#fff",
    border: "none",
    borderRadius: "5px",
    cursor: "pointer",
  },
  };
  
  

export default Theatremain;
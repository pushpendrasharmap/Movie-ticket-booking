// Material UI imports

import { useEffect } from "react";
import { useState } from "react";
import { ReactSession } from 'react-client-session';
import { useNavigate } from "react-router-dom";

function Theatremain() {
  const [theaters, setTheaters] = useState([]);
  const [selectedTheater, setSelectedTheater] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    getTheatres(ReactSession.get("movieId"));
  }, []);

  const handleTheaterSelect = (event) => {
    setSelectedTheater(event.target.value);
  };

  const handleSubmit = (event) => {
    const ol = ReactSession.get("isAdult");
    const theatreObject = theaters.filter(theater => theater.theatreName === selectedTheater);
    console.log("theatre object id = " + theatreObject[0].id);
    ReactSession.set("tid", theatreObject[0].id);
    ReactSession.set("theatrename", theatreObject[0].theatreName);
    //const ve= ReactSession.get("verify");
    if (ol === "false") { 
      event.preventDefault();
      // Perform any desired action with the theaterName value
      // Reset the input value
      navigate("/tpage")
    }
    else {
      event.preventDefault();
      // Perform any desired action with the theaterName value
      // Reset the input value
      navigate("/verify")
    }

  };
  const getTheatres = (movieId) => {
    fetch('http://localhost:8081/api/theatres/m/' + movieId).then(
      response => response.json()
    ).then(data => {
      setTheaters(data);
    }, (e) => {
      console.log(e);
    })
  }

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
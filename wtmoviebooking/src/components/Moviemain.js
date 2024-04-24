import React, { useEffect, useState } from "react";
import axios from "axios";
import { Container, Grid, Toolbar, FormControl, InputLabel, Select, MenuItem, Button, colors } from "@mui/material";
import MovieCards from "./MovieCards";
import { Apicalls } from "./Apicalls";
import { Link } from "react-router-dom";
import { ReactSession } from 'react-client-session';
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { event } from "jquery";

function Moviemain() {
  const navigate = useNavigate();
  var usern = ReactSession.get("username");
  const [language, setLanguage] = useState("");
  const [languages, setLanguages] = useState([]);
  const [data, setData] = useState([]);
  const [movies, setMovies] = useState([]);

  const getData = async () => {
    var k = [];
    Apicalls.AllMovies()
      .then((res) => {
        k = res.data.filter((i) => i);
        setData(k);
        setMovies(k);
        setLanguages(k.map((movie, i) => movie.originalLanguage))
        console.log(k);
      });
  };

  useEffect(() => {
    getData();
  }, []);

  const handleChange = (event) => {
    event.preventDefault();
    if (event.target.value == "All") {
      setMovies(data)
    } else{
      setMovies(data.filter(data => data.originalLanguage == event.target.value))
    }
    setLanguage(event.target.value);
  }

  return (
    <Container>
      {usern !== null ? (
        <div>
          <br></br>
          <ToastContainer />

          <Button variant="contained" color="primary" onClick={() => navigate("/reservation")}>
            Check your reservations</Button>

        </div>
      ) : null}

      <Toolbar sx={{ justifyContent: 'end', marginTop: '05px' }}>
        <FormControl sx={{ width: '150px' }}>
          <InputLabel >Language</InputLabel>
          <Select
            value={language}
            label={`Language`}
            onChange={handleChange}
          >
            <MenuItem value="All">All</MenuItem>
            {languages.map((language, key) => (
                <MenuItem value={language}>{language}</MenuItem>
              ))}

          </Select>
        </FormControl>
      </Toolbar>

      <Grid
        container
        spacing={{ xs: 2, md: 3 }}
        columns={{ xs: 2, sm: 8, md: 12, lg: 12 }}
        alignItems="center"
        justifyContent="center"
      >
        {movies &&
          movies.map((i, key) => (

            <Grid item xs={2} sm={4} md={3.4} key={key}>

              <MovieCards passed={i} />

            </Grid>
          ))}
      </Grid>

    </Container>

  );
};

export default Moviemain;
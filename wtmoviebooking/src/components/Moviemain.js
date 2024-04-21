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

function Moviemain() {
  const navigate = useNavigate();
  var usern = ReactSession.get("username");
  const [data, setData] = useState([]);

  const getData = async () => {
    var k = [];
    Apicalls.AllMovies()
      .then((res) => {
        k = res.data.filter((i) => i); setData(k);
        console.log(k);
      });
  };

  useEffect(() => {
    getData();
  }, []);

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

      <Grid
        container
        spacing={{ xs: 2, md: 3 }}
        columns={{ xs: 2, sm: 8, md: 12, lg: 12 }}
        alignItems="center"
        justifyContent="center"
      >
        {data &&
          data.map((i, key) => (

            <Grid item xs={2} sm={4} md={3.4} key={key}>

              <MovieCards passed={i} />

            </Grid>
          ))}
      </Grid>

    </Container>

  );
};

export default Moviemain;
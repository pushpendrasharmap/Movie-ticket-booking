import React, { useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import YouTube from "react-youtube";
import { ReactSession } from 'react-client-session';

import {
  Button,
  CardActions,
  Typography,
  Card,
  CardContent,
  Rating,
  Modal,
  Paper,
} from "@mui/material";

// import not from "../Img/notfound.png";

function MovieCards(props) {
  const [open, setOpen] = useState(false);

  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const handleBookTicket = (props) => {
    ReactSession.set('movieName', props.movieName);
    ReactSession.set('movieId', props.id);
  }
  const usern = ReactSession.get("username");

  return (
    <div>
      <Card sx={{ maxWidth: 500, marginTop: "20px" }}>
        <img
          style={{ width: 290, height: 400 }}
          src={
            props.passed.posterPath == null
              ? "hello"
              : `https://image.tmdb.org/t/p/original/${props.passed.posterPath}`
          }
          alt="poster.exe"
        ></img>
        <CardContent>
          <Rating name="read-only" value={props.passed.popularity} readOnly />
          {props.passed.originalTitle === props.passed.movieName ? (
            <Typography gutterBottom variant="h6" component="div">
              {" "}
              {props.passed.title}{" "}
            </Typography>
          ) : (
            <div>
              <Typography gutterBottom variant="h6" component="div">
                {props.passed.originalTitle}
              </Typography>
              <Typography gutterBottom variant="h6" component="div">
                {props.passed.movieName}
              </Typography>
            </div>
          )}
          <Typography variant="body2" noWrap color="text.secondary">
            {props.passed.overview}
          </Typography>
        </CardContent>

        <CardActions>
          <div
            style={{
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
            }}
          >
            <Link
              style={{ textDecoration: "none", marginRight: "10px" }}
              to={usern ? "/theatre" : "/login"}
              state={props.passed}
            >
              <Button size="small" variant="contained" color="primary" onClick={() => {
                handleBookTicket(props.passed);
              }}>
                Book Ticket
              </Button>
            </Link>
            <Button
              size="small"
              variant="contained"
              color="secondary"
              onClick={() => {
                handleOpen(props.passed);
              }}
            >
              More Details
            </Button>
          </div>
        </CardActions>
      </Card>

      <Modal open={open} onClose={handleClose}>
        <Paper
          sx={{
            position: "absolute",
            top: "50%",
            left: "50%",
            transform: "translate(-50%, -50%)",
            width: 1100,
            bgcolor: "background.paper",
            boxShadow: 24,
            p: 4,
          }}
        >
          <Card
            raised
            sx={{
              maxWidth: 1100,
              display: "flex",
              padding: "5px",
              alignItems: "center",
            }}
          >
            {props.passed.backdropPath == null ? (
              <img
                style={{ width: 700, height: 500 }}
                src={`https://image.tmdb.org/t/p/original/${props.passed.posterPath}`}
                alt="poster.exe"
              ></img>
            ) : (
              <img
                style={{ width: 690, height: 500 }}
                src={`https://image.tmdb.org/t/p/original/${props.passed.backdropPath}`}
                alt="poster.exe"
              ></img>
            )}

            <CardContent>
              <Typography variant="h6">
                IMBD Rating:{" "}
                <span style={{ color: "purple" }}>
                  {" "}
                  {props.passed.popularity}
                </span>
              </Typography>
              <Rating
                name="read-only"
                value={props.passed.popularity}
                readOnly
              />
              <Typography gutterBottom variant="h6" component="div">
                {props.passed.originalTitle}
              </Typography>
              <Typography gutterBottom>
                Release Date : {props.passed.releaseDate.slice(8, 11)}-
                {props.passed.releaseDate.slice(5, 7)}-
                {props.passed.releaseDate.slice(0, 4)}
              </Typography>
              <Typography variant="body2" color="text.secondary">
                {props.passed.overview}
              </Typography>
            </CardContent>
          </Card>
        </Paper>
      </Modal>
    </div>
  );
};

export default MovieCards;
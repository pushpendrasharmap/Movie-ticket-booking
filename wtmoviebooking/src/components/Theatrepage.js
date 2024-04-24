import Chip from "@mui/material/Chip";
import FaceIcon from "@mui/icons-material/Face";
import Paper from "@mui/material/Paper";
import LockIcon from "@mui/icons-material/Lock";
import { useEffect } from "react";
import { Apicalls } from './Apicalls'
import Switch from "@mui/material/Switch";
import { MenuItem, Select } from '@mui/material';
import { toast, ToastContainer } from 'react-toastify';
// import SeatPicker from "react-seat-picker";
import React, { useState } from "react";
import "./SeatPicker.css";
import {
  TextField,
  InputAdornment,
  FormControl,
  InputLabel,
  IconButton,
  Button,
  Input,
  Checkbox,
  Alert,
  Stack,
} from "@mui/material";
import Loginmain from "./Loginmain";
import Signup from "./Signup";
import { WidthFull } from "@mui/icons-material";
import { ReactSession } from 'react-client-session';
import { useNavigate } from "react-router-dom";

function Theatrepage() {
  //   const ot = ReactSession.get("original_title")
  //   const pop = ReactSession.get("popularity");
  //   const lan = ReactSession.get("original_language");
  const navigate = useNavigate();

  var tname = ReactSession.get("theatrename");
  var user = ReactSession.get("username");
  var ids = ReactSession.get("id");
  var password = ReactSession.get("password");
  var tid = ReactSession.get("tid");
  var email = ReactSession.get("email");
  var movienam = ReactSession.get("movieName")
  var movId = ReactSession.get("movieId")
  var [seat, setSeat] = useState("");
  var [unavailableSeats, setUnavailableSeats] = useState([]);
  const [showDates, setShowDates] = useState([]);
  const [showTimes, setShowTimes] = useState([]);
  const [showDateTimes, setShowDateTimes] = useState([]);
  //   var mid=ReactSession.get("mid");

  useEffect(() => {
    const udetail = {
      id: ReactSession.get("id"),
      movieId: ReactSession.get("movieId"),
      theatreId: ReactSession.get("tid"),
    }

    Apicalls.GetSeat(udetail).then(
      response => {
        const responseData = response.data; // Store response data in a variable
        const seatArray = responseData.map((item) => item.body.seat.replace(/[\[\]]+/g, '')); // Extract seat information from each user object and remove square brackets
        const combinedSeats = "[" + seatArray.join(',') + "]"; // Combine seat information into a single string separated by commas and enclosed in square brackets
        console.log(combinedSeats); // Display the combined seats string
        setSeat(combinedSeats);
        console.log(udetail.movieId);
      })

    getShows(ReactSession.get("movieId"), tid);

  }, []);

  const getShows = (movieId, theatreId) => {
    fetch('http://localhost:8081/api/shows/' + movieId + '/' + theatreId).then(
      response => response.json()
    ).then(data => {
      console.log(data);
      setShowDateTimes(data.map(data => ({ date: data.date, time: data.time, price: data.ticketPrice })));
      setShowDates(data.map(data => data.date));
      setShowTimes(data.map(data => data.time));
    }, (e) => {
      console.log(e);
    })
  }

  console.log(ReactSession.get("movieId"));
  console.log(user);
  console.log(ids);
  console.log(tid);
  console.log(password);
  console.log(email);
  console.log(movienam);
  console.log(movId);
  console.log(seat);


  var [selectedSeats, setSelectedSeats] = useState([]);
  var [showDate, setShowDate] = useState("");
  var [showTime, setShowTime] = useState("");
  var [price, setPrice] = useState("");
  const [seatPrice, setSeatPrice] = useState(0);
  const todayDate = new Date().toISOString().split("T")[0];

  const handleSeatClick = (seat) => {
    if (selectedSeats.includes(seat)) {
      // Deselect the seat
      setSelectedSeats((prevSeats) =>
        prevSeats.filter((selectedSeat) => selectedSeat !== seat)
      );
    } else {
      // Select the seat
      setSelectedSeats((prevSeats) => [...prevSeats, seat]);
    }
  };

  const handleDateChange = (event) => {
    const newDate = event.target.value;
    setShowDate(newDate);

    setShowTimes(showDateTimes
      .filter(showDateTime => showDateTime.date === newDate)
      .map(showDateTime => showDateTime.time));

  };

  const handleTimeChange = (event) => {
    const newTime = event.target.value;
    setShowTime(newTime);
    setSeatPrice(showDateTimes.filter(showDateTime => showDateTime.date === showDate && showDateTime.time === newTime)[0].price)
  };

  const getUnavailableSeats = (date, time) => {
    const udetail = {
      id: ReactSession.get("id"),
      movieId: ReactSession.get("movieId"),
      theatreId: ReactSession.get("tid"),
      datetime: date + " T" + time
    };

    Apicalls.GetSeat(udetail)
      .then(response => {
        const responseData = response.data;
        const seatArray = responseData.map((item) => item.body.seat.replace(/[\[\]]+/g, ''));
        const combinedSeats = "[" + seatArray.join(',') + "]";
        setUnavailableSeats(combinedSeats);
        console.log(combinedSeats);
      })
      .catch(error => {
        console.error(error);
      });
  };
  useEffect(() => {
    if (showDate !== "" && showTime !== "") {
      getUnavailableSeats(showDate, showTime);
    }
  }, [showDate, showTime]);

  const numRows = 10; // Number of rows
  const numColumns = 10; // Number of columns
   // Price per seat
  // unavailableSeats = seat; // Example of unavailable seats
  const [updateSuccess, setUpdateSuccess] = useState(false); // State variable for update success
  const renderSeats = () => {
    const seats = [];

    for (let row = 0; row < numRows; row++) {
      const rowSeats = [];

      for (let column = 0; column < numColumns; column++) {
        const seat = `${String.fromCharCode(65 + row)}${column + 1}`;
        const isAvailable = !unavailableSeats.includes(seat);

        rowSeats.push(
          <button
            key={seat}
            className={`seat ${selectedSeats.includes(seat) ? "selected" : ""} ${isAvailable ? "available" : "unavailable"}`}
            onClick={() => handleSeatClick(seat)}
            disabled={!isAvailable}
          >
            {seat}
          </button>
        );
      }

      seats.push(
        <div key={row} className="row">
          <span className="row-name">{String.fromCharCode(65 + row)}</span>
          {rowSeats}
        </div>
      );
    }

    return seats;
  };

  const calculateSeatPrice = () => {
    return selectedSeats.length * seatPrice;
  };
  const calculateTotalPrice = () => {
    var dummy = calculateSeatPrice();
    if (dummy < 0) {
      return 0;
    }
    else {
      return dummy;
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    if (selectedSeats.length == 0) {
      toast.error("Please select seats");
      return;
    }
    // Perform actions on form submission
    console.log("Date:", showDate);
    console.log("Time:", showTime);
    console.log("Selected Seats:", selectedSeats);
    var p = calculateTotalPrice();
    setPrice(p);
    console.log(price);
    console.log("Seat Price:", calculateSeatPrice());
    console.log("Total Price", calculateTotalPrice());
    const seatt = `[${selectedSeats.join(", ")}]`; // Convert selectedSeats array to a string enclosed in square brackets
    console.log("Seats:", seatt);
    bookTicket(seatt, p);
  };
  function bookTicket(seatt, p) {
    //var seatt= "E3";
    const bDetail = {
      userId: ids,
      userName: user,
      email: email,
      seat: seatt,
      datetime: showDate + " T" + showTime,
      price: p,
      theatreId: tid,
      theatreName: tname,
      movieName: movienam,
      movieId: movId
    }
    //console.log(udetail);
    Apicalls.SaveBooking(bDetail)
      .then(() => {
        console.log("updated successfully")
        setUpdateSuccess(true); // Set the update success state to true

      })
      .catch(error => {
        console.error(error);
      })
  }
  useEffect(() => {

    if (updateSuccess) {
      const ldetail2 = {
        id: ids,
      }
      const timer = setTimeout(() => {
        navigate("/movies"); // Redirect to '/movies' after 2 seconds
      }, 2000);

      return () => clearTimeout(timer); // Clear the timer on component unmount
    }
  }, [updateSuccess, navigate]);

  return (
    <div className="seat-picker">
      <div className="screen">SCREEN</div>
      <div className="seating-plan">{renderSeats()}</div>
      <div className="selected-seats">
        <h4>Selected Seats:</h4>
        <ul>
          {selectedSeats.map((seat) => (
            <li key={seat}>{seat}</li>
          ))}
        </ul>
        <h4>Seat Price(+): Rs.{seatPrice}</h4>
        <h4>Total Price: Rs.{calculateTotalPrice()}</h4>
      </div>
      <form onSubmit={handleSubmit}>
        <div className="form-row">
          <FormControl fullWidth>
            <InputLabel>Select Date</InputLabel>
            <Select
              value={showDate}
              onChange={handleDateChange}
              required
            >
              <MenuItem value="">Select Date</MenuItem>
              {showDates.map((date, i) => (
                <MenuItem key={i} value={date}>{date}</MenuItem>
              ))}
            </Select>
          </FormControl>
        </div>
        <div className="form-row">
          <FormControl fullWidth>
            <InputLabel>Select Time</InputLabel>
            <Select
              value={showTime}
              onChange={handleTimeChange}
              required
            >
              <MenuItem value="">Select Time</MenuItem>
              {showTimes.map(time => (
                <MenuItem key={time} value={time}>{time}</MenuItem>
              ))}
            </Select>
          </FormControl>
        </div>
        <button type="submit">Submit</button>
        <ToastContainer />
        {updateSuccess &&
          <Alert severity="success" size="small">
            Booking success! Redirecting to movies ..
          </Alert>
        }
      </form>
    </div>

  );
}


export default Theatrepage;
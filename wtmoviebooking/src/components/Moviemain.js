import React, { useEffect, useState } from "react";
import axios from "axios";
import { Container, Grid, Toolbar,  FormControl, InputLabel, Select, MenuItem,Button, colors} from "@mui/material";
import MovieCards from "./MovieCards";
import { Apicalls } from "./Apicalls";
import { Link } from "react-router-dom";
import { ReactSession } from 'react-client-session';
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

function Moviemain(){
  const [restatus, setRestatus] = useState(null);
  var re1;
  const navigate = useNavigate();
var usern = ReactSession.get("username");
var tid = ReactSession.get("tid");
var mid = ReactSession.get("movId");
console.log(tid);
console.log(mid);
console.log(usern);
  const [data, setData] = useState([]);
  const [resp, setResp] = useState("");
  const [samp, setSamp] = useState("");
  const [region, setRegion] = React.useState('India');
  
  
 //ReactSession.set("restatus","");
  const handleChange = (event) => {
    setRegion(event.target.value);
  };

  const getData = async () => {
    var k = [];
      Apicalls.AllMovies()
      .then((res) => { k = res.data.filter( (i) => i );setData(k);
        console.log(k);
      });
  };

  const getGData = async () => {
    
    var k = [];
    await axios
      .get("dataGL.json")
      .then((res) => { k = res.data.results.filter( (i) => i );setData(k);
        console.log(k);
      });
  };
  useEffect(() => {
    (region === 'India' ? getData() : getGData())
    var ids=ReactSession.get("id");
    console.log(ids);
    const udetail = {
      id: ReactSession.get("id"),
    }
    console.log(udetail);
    
  }, []);

// useEffect(() => {
//     (region =='India' ? getData() : getGData())
    
//     const udetail = {
//       id: ReactSession.get("id"),
//     }
//     console.log(udetail);
//     Apicalls.GetUser(udetail).then(response =>{
//      console.log(response.data.user.restatus);
//      setResp(response.data.user.restatus);
//      ReactSession.set("restatus",resp);
//      console.log(resp);
//     }
//       ).catch(error => {
//         console.error(error);
//       });
//  re = ReactSession.get("restatus");
    
//   }, []);

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
  
      <Toolbar sx={{justifyContent:'end',marginTop:'05px'}}>
      <FormControl sx={{width:'150px'}}>
        <InputLabel >Region</InputLabel>
        <Select 
          value={region}
          label={`Region`}
          onChange={handleChange}
        >
          <MenuItem value='India' onClick={getData}>India</MenuItem>
          <MenuItem value='Global' onClick={getGData} >Global</MenuItem>
        </Select>
      </FormControl>
      </Toolbar>
      
      <Grid
        container
        spacing={{ xs: 2, md: 3 }}
        columns={{ xs: 2, sm: 8, md: 12 , lg:12}}
        alignItems="center"
        justifyContent="center"
      >
         {data &&
            data.map((i, key) => (

          <Grid item xs={2} sm={4} md={3.4} key={key}>
          
             <MovieCards passed={i}  />
             
          </Grid>
        ))}
      </Grid>

    </Container>

  );
};

export default Moviemain;
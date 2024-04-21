import React from 'react';
import { useEffect } from 'react';
import { Apicalls } from './Apicalls';
import { useState } from 'react';
import { ReactSession } from 'react-client-session';
import { PDFDownloadLink, Document, Page, Text, View, Image, StyleSheet } from '@react-pdf/renderer';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
//npm startimport './ticketConfirmationPage.css';

const styles = StyleSheet.create({
  container: {
    marginTop: 0,
    marginBottom: 50,
    textAlign: 'center',
    backgroundColor: '#1c1b1b',
    paddingTop: 10,
  },
  logo: {
    width: 200,
    height: 80,
    marginBottom: 10,
  },
  heading: {
    fontSize: 24,
    fontWeight: 'bold',
    color: 'white',
  },
  pdfContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    marginTop: '-50vh',
  },
  detailsContainer: {
    border: '1px solid black',
    padding: 10,
    width: '80%',
  },
  detailText: {
    marginBottom: 5,
  },
  downloadButton: {
    backgroundColor: '#f6b745',
    color: 'black',
    padding: '10px 20px',
    borderRadius: 4,
    fontWeight: 'bold',
    textDecoration: 'none',
    textTransform: 'uppercase',
    cursor: 'pointer',
    display: 'inline-block',
    marginTop: 20,
    marginBottom: 0,
  },
  toastContainer: {
    //position: 
    // top: '100%',
    // transform: 'translateY(-50%)',
  },
});

function Reservationmain() {
  const [data, setData] = useState([]);

  const [renderper, setRenderper] = useState();

  const [transferEmail, setTransferEmail] = useState('');
  const [transferBookingId, setTransferBookingId] = useState(null);

  useEffect(() => {
    const udetail = {
      id: ReactSession.get('id'),
    };
    setRenderper(ReactSession.get('username'));
    fetchBookings(udetail);

  }, []);

  const fetchBookings = (udetail) => {
    Apicalls.GetUserBookings(udetail.id)
      .then((response) => {
        console.log(response.data);
        setData(response.data);
        // console.log(data.movie.movieName);
      })
      .catch((error) => {
        console.error(error);
      });
  }

  const handleDownload = () => {
    toast.success('PDF downloaded successfully!', {
      position: toast.POSITION.BOTTOM_RIGHT,
      autoClose: 8000,
    });
  };

  const handleTransfer = (bookingId) => {
    // Set booking ID for transfer
    setTransferBookingId(bookingId);
  };



  const confirmTransfer = () => {
    // Logic to transfer booking using transferEmail
    // Call API to perform transfer
    fetch('http://localhost:8081/api/users/' + transferEmail).then(
      response => response.json()
    ).then(data => {
      console.log("data id" + data.id);
      Apicalls.TransferBooking(transferBookingId, data)
        .then(() => {
          toast.success('Booking transferred successfully!');
          // Clear transfer-related states
          setTransferBookingId(null);
          setTransferEmail('');
          // Refresh bookings after transfer
          const udetail = {
            id: ReactSession.get('id'),
          };
          fetchBookings(udetail);
        })
        .catch((error) => {
          console.error(error);
          toast.error('Failed to transfer booking.');
        });
    }, (e) => {
      toast.error("No user with provided email fount");
    })

  };

  const handleCancel = (bookingId) => {
    // Logic to cancel booking
    // Call API to cancel booking
    Apicalls.CancelBooking(bookingId)
      .then(() => {
        toast.success('Booking canceled successfully!');
        // Refresh bookings after cancellation
        const udetail = {
          id: ReactSession.get('id'),
        };
        fetchBookings(udetail);
      })
      .catch((error) => {
        console.error(error);
        toast.error('Failed to cancel booking.');
      });
  };

  const MyDocument = ({ booking }) => (
    <Document>
      <Page>
        <View style={styles.container}>
          <Image style={styles.logo} src="/images/logos.png" />
          <Text style={styles.heading}>Reservation Details</Text>
        </View>
        <View style={styles.pdfContainer}>
          <View style={styles.detailsContainer}>
            <Text style={styles.detailText}>Datetime: {booking.body.datetime}</Text>
            <Text style={styles.detailText}>Email: {booking.body.email}</Text>
            <Text style={styles.detailText}>ID: {booking.body.userId}</Text>
            <Text style={styles.detailText}>Movie ID: {booking.body.movieId}</Text>
            <Text style={styles.detailText}>Movie Name: {booking.body.movieName}</Text>
            <Text style={styles.detailText}>Name: {booking.body.userName}</Text>

            <Text style={styles.detailText}>Price: {booking.body.price}</Text>

            <Text style={styles.detailText}>Seat: {booking.body.seat}</Text>
            <Text style={styles.detailText}>Theatre ID: {booking.body.theatreId}</Text>
            <Text style={styles.detailText}>Theatre Name: {booking.body.theatreName}</Text>
          </View>
        </View>

      </Page>
    </Document>
  );

  return (
    <div>
      <div>
        <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center',  marginBottom: "20px"}}>
          <h1>Reservation Details:</h1>
        </div>
        <div style={{ maxHeight: '300px', overflowY: 'auto', display: 'flex', flexDirection: 'column', alignItems: 'center' }}>

          {data && renderper !== null ?
            data.map((booking, i) => (
              <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', marginLeft: "10px", marginBottom: "50px" }} key={i}>
                
                <div>
                <p>Datetime: {booking.body.datetime}</p>
                <p>Email: {booking.body.email}</p>
                <p>ID: {booking.body.userId}</p>
                <p>Movie ID: {booking.body.movieId}</p>
                <p>Movie Name: {booking.body.movieName}</p>
                <p>Name: {booking.body.userName}</p>

                <p>Price: {booking.body.price}</p>

                <p>Seat: {booking.body.seat}</p>
                <p>Theatre ID: {booking.body.theatreId}</p>
                <p>Theatre Name: {booking.body.theatreName}</p>
                </div>
                <div>
                <PDFDownloadLink document={<MyDocument booking={booking} />} fileName="reservation.pdf">
                  {({ blob, url, loading, error }) => (
                    <>
                      <button onClick={handleDownload} style={styles.downloadButton}>
                        {loading ? 'Loading document...' : 'Download Ticket'}
                      </button>
                      <ToastContainer />
                    </>
                  )}
                </PDFDownloadLink>
                <button
                  type="button"
                  style={{
                    backgroundColor: '#5F9EA0', border: 'none', color: 'black',
                    padding: '10px 15px', textAlign: 'center',
                    fontSize: '16px', margin: '10px', marginLeft: '0'
                  }}
                  onClick={() => handleTransfer(booking.body.id)}
                >
                  Transfer
                </button>
                <button
                  type="button"
                  style={{
                    backgroundColor: '#DC143C', border: 'none', color: 'black',
                    padding: '10px 15px', textAlign: 'center',
                    fontSize: '16px', margin: '10px'
                  }}
                  onClick={() => handleCancel(booking.body.id)}
                >
                  Cancel
                </button>
                {transferBookingId === booking.body.id && (
                  <div>
                    <input
                      type="text"
                      placeholder="Enter email address"
                      value={transferEmail}
                      onChange={(e) => setTransferEmail(e.target.value)}
                    />
                    <br/>
                    <button
                      type="button"
                      style={{
                        marginTop: '5px'
                      }}
                      onClick={confirmTransfer}
                    >
                      Confirm Transfer
                    </button>
                  </div>
                )}
                </div>
              </div>
            )) : (
              <p>Loading...Please hold on..Check if you're logged in if it takes more than a few seconds to load.</p>
            )}
        </div>
      </div>
      <br></br>
    </div>
  );
}

export default Reservationmain;
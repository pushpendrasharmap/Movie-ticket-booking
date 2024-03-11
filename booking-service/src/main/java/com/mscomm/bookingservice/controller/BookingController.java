package com.mscomm.bookingservice.controller;

import com.mscomm.bookingservice.dto.RequestBodyDto;
import com.mscomm.bookingservice.entity.Booking;
import com.mscomm.bookingservice.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/bookings")
@AllArgsConstructor
@CrossOrigin(origins="*")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> saveBooking(@RequestBody Booking booking){
        Booking savedBooking = bookingService.saveBooking(booking);
        return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public List<ResponseEntity<Booking>> getBookingsByUserId(@PathVariable("userId") Long userId) {
        List<Booking> bookings = bookingService.getBookingByUserId(userId);

        List<ResponseEntity<Booking>> responseEntities = new ArrayList<>();

        for (Booking user : bookings) {
            responseEntities.add(ResponseEntity.ok(user));
        }
        return responseEntities;
    }
    @GetMapping("/{tid}/{mid}")
    public List<ResponseEntity<Booking>> getBookings(@PathVariable("tid") String theatreId, @PathVariable("mid") String movieId) {
        List<Booking> bookings = bookingService.getByTheatreIdAndMovieId(theatreId, movieId);

        List<ResponseEntity<Booking>> responseEntities = new ArrayList<>();

        for (Booking user : bookings) {
            responseEntities.add(ResponseEntity.ok(user));
        }
        return responseEntities;
    }
    @GetMapping("/{tid}/{mid}/{date}")
    public List<ResponseEntity<Booking>> getBookingsByShowDate(@PathVariable("tid") String theatreId, @PathVariable("mid") String movieId,  @PathVariable("date") String datetime) {
        List<Booking> bookings = bookingService.getByTheatreIdAndMovieIdAndDatetime(theatreId, movieId, datetime);

        List<ResponseEntity<Booking>> responseEntities = new ArrayList<>();

        for (Booking booking : bookings) {
            responseEntities.add(ResponseEntity.ok(booking));
        }

        return responseEntities;
    }

    @PatchMapping("/{bookingId}")
    public ResponseEntity<?> transferBooking(@PathVariable("bookingId") Long bookingId,
                                             @RequestBody RequestBodyDto dto) {
        if (dto.getUserId() != null && dto.getUserName() != null && dto.getEmail() != null) {
            bookingService.updateUserIdInBooking(bookingId, dto);
            return ResponseEntity.ok("Updated User Id");
        }
        throw new IllegalArgumentException("User Id, Username and email should exist in request body");

    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<?> deleteMapping(@PathVariable("bookingId") Long bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.ok("Deleted booking");
    }
}

package com.mscomm.bookingservice.service;

import com.mscomm.bookingservice.dto.RequestBodyDto;
import com.mscomm.bookingservice.entity.Booking;

import java.util.List;

public interface BookingService {
    Booking saveBooking(Booking booking);
    List<Booking> getByTheatreIdAndMovieId(String theatreId, String movieId);
    List<Booking> getByTheatreIdAndMovieIdAndDatetime(String theatreId, String movieId, String datetime);
    List<Booking> getBookingByUserId(Long userId);
    void updateUserIdInBooking(Long bookingId, RequestBodyDto dto);
    void deleteBooking(Long bookingId);
}

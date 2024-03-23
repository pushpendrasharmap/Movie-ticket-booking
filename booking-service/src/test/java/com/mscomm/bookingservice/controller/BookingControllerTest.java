package com.mscomm.bookingservice.controller;

import com.mscomm.bookingservice.dto.RequestBodyDto;
import com.mscomm.bookingservice.entity.Booking;
import com.mscomm.bookingservice.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveBooking_ValidBooking_ShouldReturnCreated() {
        // Arrange
        Booking booking = new Booking();
        when(bookingService.saveBooking(booking)).thenReturn(booking);

        // Act
        ResponseEntity<Booking> response = bookingController.saveBooking(booking);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(booking, response.getBody());
        verify(bookingService, times(1)).saveBooking(booking);
    }

    @Test
    void getBookingsByUserId_ValidUserId_ShouldReturnListOfBookings() {
        // Arrange
        long userId = 1;
        List<Booking> bookings = new ArrayList<>();
        when(bookingService.getBookingByUserId(userId)).thenReturn(bookings);

        // Act
        List<ResponseEntity<Booking>> response = bookingController.getBookingsByUserId(userId);

        // Assert
        assertEquals(bookings.size(), response.size());
        for (ResponseEntity<Booking> entity : response) {
            assertEquals(HttpStatus.OK, entity.getStatusCode());
        }
        verify(bookingService, times(1)).getBookingByUserId(userId);
    }

    // Similarly, write tests for getBookings, getBookingsByShowDate, transferBooking, and deleteMapping methods
}

package com.mscomm.bookingservice.service;

import com.mscomm.bookingservice.dto.RequestBodyDto;
import com.mscomm.bookingservice.entity.Booking;
import com.mscomm.bookingservice.repository.BookingRepository;
import com.mscomm.bookingservice.service.impl.BookingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveBooking_ValidBooking_ShouldReturnSavedBooking() {
        // Arrange
        Booking booking = new Booking();
        when(bookingRepository.save(booking)).thenReturn(booking);

        // Act
        Booking savedBooking = bookingService.saveBooking(booking);

        // Assert
        assertEquals(booking, savedBooking);
        verify(bookingRepository, times(1)).save(booking);
    }

    @Test
    void getByTheatreIdAndMovieId_ValidIds_ShouldReturnListOfBookings() {
        // Arrange
        String theatreId = "1";
        String movieId = "1";
        List<Booking> bookings = new ArrayList<>();
        when(bookingRepository.findByTheatreIdAndMovieId(theatreId, movieId)).thenReturn(bookings);

        // Act
        List<Booking> result = bookingService.getByTheatreIdAndMovieId(theatreId, movieId);

        // Assert
        assertEquals(bookings, result);
        verify(bookingRepository, times(1)).findByTheatreIdAndMovieId(theatreId, movieId);
    }

    @Test
    void getByTheatreIdAndMovieIdAndDatetime_ValidParams_ShouldReturnListOfBookings() {
        // Arrange
        String theatreId = "1";
        String movieId = "1";
        String datetime = "2024-03-21";
        List<Booking> bookings = new ArrayList<>();
        when(bookingRepository.findByTheatreIdAndMovieIdAndDatetime(theatreId, movieId, datetime)).thenReturn(bookings);

        // Act
        List<Booking> result = bookingService.getByTheatreIdAndMovieIdAndDatetime(theatreId, movieId, datetime);

        // Assert
        assertEquals(bookings, result);
        verify(bookingRepository, times(1)).findByTheatreIdAndMovieIdAndDatetime(theatreId, movieId, datetime);
    }

    @Test
    void getBookingByUserId_ValidUserId_ShouldReturnListOfBookings() {
        // Arrange
        long userId = 1;
        List<Booking> bookings = new ArrayList<>();
        when(bookingRepository.findByUserId(userId)).thenReturn(bookings);

        // Act
        List<Booking> result = bookingService.getBookingByUserId(userId);

        // Assert
        assertEquals(bookings, result);
        verify(bookingRepository, times(1)).findByUserId(userId);
    }

    @Test
    void updateUserIdInBooking_ExistingBookingId_ShouldUpdateUserId() {
        // Arrange
        long bookingId = 1;
        RequestBodyDto dto = new RequestBodyDto();
        dto.setUserId(1L);
        dto.setUserName("John");
        dto.setEmail("john@example.com");

        Booking booking = new Booking();
        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));
        when(bookingRepository.save(booking)).thenReturn(booking);

        // Act
        assertDoesNotThrow(() -> bookingService.updateUserIdInBooking(bookingId, dto));

        // Assert
        assertEquals(dto.getUserId(), booking.getUserId());
        assertEquals(dto.getUserName(), booking.getUserName());
        assertEquals(dto.getEmail(), booking.getEmail());
        verify(bookingRepository, times(1)).findById(bookingId);
        verify(bookingRepository, times(1)).save(booking);
    }

    @Test
    void updateUserIdInBooking_NonExistingBookingId_ShouldThrowNoSuchElementException() {
        // Arrange
        long bookingId = 1;
        RequestBodyDto dto = new RequestBodyDto();
        dto.setUserId(1L);
        dto.setUserName("John");
        dto.setEmail("john@example.com");

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> bookingService.updateUserIdInBooking(bookingId, dto));
        verify(bookingRepository, times(1)).findById(bookingId);
        verify(bookingRepository, never()).save(any(Booking.class));
    }

    @Test
    void deleteBooking_ExistingBookingId_ShouldDeleteBooking() {
        // Arrange
        long bookingId = 1;

        // Act
        assertDoesNotThrow(() -> bookingService.deleteBooking(bookingId));

        // Assert
        verify(bookingRepository, times(1)).deleteById(bookingId);
    }
}

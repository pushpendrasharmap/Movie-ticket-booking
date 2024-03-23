package com.mscomm.movieservice.controller;

import com.mscomm.movieservice.entity.Show;
import com.mscomm.movieservice.service.ShowService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ShowControllerTest {

    @Mock
    private ShowService showService;

    @InjectMocks
    private ShowController showController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getShowsByMovieIdAndTheatreId_ValidIds_ShouldReturnShows() {
        // Arrange
        long movieId = 1;
        long theatreId = 1;
        List<Show> shows = Arrays.asList(new Show(), new Show());
        when(showService.getShowsByMovieIdAndTheatreId(movieId, theatreId)).thenReturn(shows);

        // Act
        ResponseEntity<List<Show>> response = showController.getShowsByMovieIdAndTheatreId(movieId, theatreId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shows, response.getBody());
        verify(showService, times(1)).getShowsByMovieIdAndTheatreId(movieId, theatreId);
    }
}

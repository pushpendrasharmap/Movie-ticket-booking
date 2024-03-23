package com.mscomm.movieservice.controller;

import com.mscomm.movieservice.entity.Theatre;
import com.mscomm.movieservice.service.TheatreService;
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

class TheatreControllerTest {

    @Mock
    private TheatreService theatreService;

    @InjectMocks
    private TheatreController theatreController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveTheatre_ValidInput_ShouldReturnCreated() {
        // Arrange
        Theatre theatre = new Theatre();
        when(theatreService.saveTheatre(theatre)).thenReturn(theatre);

        // Act
        ResponseEntity<Theatre> response = theatreController.saveTheatre(theatre);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(theatre, response.getBody());
        verify(theatreService, times(1)).saveTheatre(theatre);
    }

    @Test
    void getTheatreById_ValidId_ShouldReturnTheatre() {
        // Arrange
        long id = 1;
        Theatre theatre = new Theatre();
        when(theatreService.getTheatreById(id)).thenReturn(theatre);

        // Act
        ResponseEntity<Theatre> response = theatreController.getTheatreById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(theatre, response.getBody());
        verify(theatreService, times(1)).getTheatreById(id);
    }

    @Test
    void getTheatreByTheatreName_ValidName_ShouldReturnTheatre() {
        // Arrange
        String theatreName = "TheatreName";
        Theatre theatre = new Theatre();
        when(theatreService.getTheatreByTheatreName(theatreName)).thenReturn(theatre);

        // Act
        ResponseEntity<Theatre> response = theatreController.getTheatreByTheatreName(theatreName);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(theatre, response.getBody());
        verify(theatreService, times(1)).getTheatreByTheatreName(theatreName);
    }

    @Test
    void getAllTheatres_ShouldReturnListOfTheatres() {
        // Arrange
        List<Theatre> theatres = Arrays.asList(new Theatre(), new Theatre());
        when(theatreService.getAllTheatres()).thenReturn(theatres);

        // Act
        ResponseEntity<List<Theatre>> response = theatreController.getAllTheatres();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(theatres, response.getBody());
        verify(theatreService, times(1)).getAllTheatres();
    }

    @Test
    void getTheatresForMovie_ValidId_ShouldReturnListOfTheatres() {
        // Arrange
        long movieId = 1;
        List<Theatre> theatres = Arrays.asList(new Theatre(), new Theatre());
        when(theatreService.getTheatresForMovieId(movieId)).thenReturn(theatres);

        // Act
        ResponseEntity<List<Theatre>> response = theatreController.getTheatresForMovie(movieId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(theatres, response.getBody());
        verify(theatreService, times(1)).getTheatresForMovieId(movieId);
    }
}

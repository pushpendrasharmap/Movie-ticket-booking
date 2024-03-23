package com.mscomm.movieservice.service;

import com.mscomm.movieservice.entity.Theatre;
import com.mscomm.movieservice.repository.TheatreRepository;
import com.mscomm.movieservice.service.impl.TheatreServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TheatreServiceImplTest {

    @Mock
    private TheatreRepository theatreRepository;

    @InjectMocks
    private TheatreServiceImpl theatreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveTheatre_ValidTheatre_ShouldReturnSavedTheatre() {
        // Arrange
        Theatre theatre = new Theatre();
        when(theatreRepository.save(theatre)).thenReturn(theatre);

        // Act
        Theatre savedTheatre = theatreService.saveTheatre(theatre);

        // Assert
        assertEquals(theatre, savedTheatre);
        verify(theatreRepository, times(1)).save(theatre);
    }

    @Test
    void getTheatreById_ExistingId_ShouldReturnTheatre() {
        // Arrange
        long theatreId = 1;
        Theatre theatre = new Theatre();
        when(theatreRepository.findById(theatreId)).thenReturn(Optional.of(theatre));

        // Act
        Theatre result = theatreService.getTheatreById(theatreId);

        // Assert
        assertEquals(theatre, result);
        verify(theatreRepository, times(1)).findById(theatreId);
    }

    @Test
    void getAllTheatres_ShouldReturnListOfTheatres() {
        // Arrange
        List<Theatre> theatres = Arrays.asList(new Theatre(), new Theatre());
        when(theatreRepository.findAll()).thenReturn(theatres);

        // Act
        List<Theatre> result = theatreService.getAllTheatres();

        // Assert
        assertEquals(theatres.size(), result.size());
        assertEquals(theatres, result);
        verify(theatreRepository, times(1)).findAll();
    }

    @Test
    void getTheatresForMovieId_ValidId_ShouldReturnListOfTheatres() {
        // Arrange
        long movieId = 1;
        List<Theatre> theatres = Arrays.asList(new Theatre(), new Theatre());
        when(theatreRepository.getTheatresForMovieId(movieId)).thenReturn(theatres);

        // Act
        List<Theatre> result = theatreService.getTheatresForMovieId(movieId);

        // Assert
        assertEquals(theatres.size(), result.size());
        assertEquals(theatres, result);
        verify(theatreRepository, times(1)).getTheatresForMovieId(movieId);
    }

    @Test
    void getTheatreByTheatreName_ValidName_ShouldReturnTheatre() {
        // Arrange
        String theatreName = "TheatreName";
        Theatre theatre = new Theatre();
        when(theatreRepository.findByTheatreName(theatreName)).thenReturn(theatre);

        // Act
        Theatre result = theatreService.getTheatreByTheatreName(theatreName);

        // Assert
        assertEquals(theatre, result);
        verify(theatreRepository, times(1)).findByTheatreName(theatreName);
    }
}

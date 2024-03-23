package com.mscomm.movieservice.service;

import com.mscomm.movieservice.entity.Show;
import com.mscomm.movieservice.repository.ShowRepository;
import com.mscomm.movieservice.service.impl.ShowServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ShowServiceImplTest {

    @Mock
    private ShowRepository showRepository;

    @InjectMocks
    private ShowServiceImpl showService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getShowsByMovieIdAndTheatreId_ShouldReturnShows() {
        // Arrange
        long movieId = 1;
        long theatreId = 1;
        List<Show> shows = Arrays.asList(new Show(), new Show());
        when(showRepository.findByMovieIdAndTheatreId(movieId, theatreId)).thenReturn(shows);

        // Act
        List<Show> result = showService.getShowsByMovieIdAndTheatreId(movieId, theatreId);

        // Assert
        assertEquals(shows.size(), result.size());
        assertEquals(shows, result);
        verify(showRepository, times(1)).findByMovieIdAndTheatreId(movieId, theatreId);
    }
}

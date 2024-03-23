package com.mscomm.movieservice.controller;

import com.mscomm.movieservice.entity.Movie;
import com.mscomm.movieservice.service.MovieService;
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

class MovieControllerTest {

   @Mock
   private MovieService movieService;

   @InjectMocks
   private MovieController movieController;

   @BeforeEach
   void setUp() {
      MockitoAnnotations.initMocks(this);
   }

   @Test
   void saveMovie_ValidInput_ShouldReturnCreated() {
      // Arrange
      Movie movie = Movie.builder()
              .movieName("testName")
              .build();
      when(movieService.saveMovie(movie)).thenReturn(movie);

      // Act
      ResponseEntity<Movie> response = movieController.saveMovie(movie);

      // Assert
      assertEquals(HttpStatus.CREATED, response.getStatusCode());
      assertEquals(movie, response.getBody());
      verify(movieService, times(1)).saveMovie(movie);
   }

   @Test
   void getMovieById_ValidId_ShouldReturnMovie() {
      // Arrange
      long id = 1;
      Movie movie = Movie.builder()
              .movieName("testName")
              .id(123L)
              .build();
      when(movieService.getMovieById(id)).thenReturn(movie);

      // Act
      ResponseEntity<Movie> response = movieController.getMovieById(id);

      // Assert
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(movie, response.getBody());
      verify(movieService, times(1)).getMovieById(id);
   }

   @Test
   void getMovieByMovieName_ValidName_ShouldReturnMovie() {
      // Arrange
      String movieName = "MovieName";
      Movie movie = Movie.builder()
              .movieName(movieName)
              .id(123L)
              .build();
      when(movieService.getMovieByMovieName(movieName)).thenReturn(movie);

      // Act
      ResponseEntity<Movie> response = movieController.getMovieByMovieName(movieName);

      // Assert
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(movie, response.getBody());
      verify(movieService, times(1)).getMovieByMovieName(movieName);
   }

   @Test
   void getAllMovies_ShouldReturnListOfMovies() {
      // Arrange
      List<Movie> movies = Arrays.asList(new Movie(), new Movie());
      when(movieService.getAllMovies()).thenReturn(movies);

      // Act
      ResponseEntity<List<Movie>> response = movieController.getAllMovies();

      // Assert
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(movies, response.getBody());
      verify(movieService, times(1)).getAllMovies();
   }
}

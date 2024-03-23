package com.mscomm.movieservice.service;

import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import com.mscomm.movieservice.entity.*;
import com.mscomm.movieservice.repository.*;
import com.mscomm.movieservice.service.impl.MovieServiceImpl;

@ExtendWith(MockitoExtension.class)
 class MovieServiceImplTest {
	@InjectMocks
    private MovieServiceImpl movieService;
    
    @Mock
    private MovieRepository movieRepository;
    @Test
     void testSaveDepartment() {
        // Arrange
        Movie department = new Movie();
        when(movieRepository.save(any(Movie.class))).thenReturn(department);
        
        // Act
        Movie result = movieService.saveMovie(department);
        
        // Assert
        Assertions.assertNotNull(result);
        // Add more assertions as needed
    }

    @Test
     void testGetDepartmentById() {
        // Arrange
        Long departmentId = 1L;
        Movie department = new Movie();
        when(movieRepository.findById(departmentId)).thenReturn(Optional.of(department));
        
        // Act
        Movie result = movieService.getMovieById(departmentId);
        
        // Assert
       Assertions.assertNotNull(result);
        // Add more assertions as needed
    }

    @Test
     void testGetDepartmentBymovieName() {
        // Arrange
        String movieName = "Example";
        Movie department = new Movie();
        when(movieRepository.findBymovieName(movieName)).thenReturn(department);
        
        // Act
        Movie result = movieService.getMovieByMovieName(movieName);
        
        // Assert
        Assertions.assertNotNull(result);
        // Add more assertions as needed
    }

	
}

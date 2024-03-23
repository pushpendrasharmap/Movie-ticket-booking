package com.mscomm.movieservice.service.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mscomm.movieservice.entity.Movie;
import com.mscomm.movieservice.repository.MovieRepository;
import com.mscomm.movieservice.service.MovieService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class MovieServiceImpl implements MovieService {
	
	 private MovieRepository movieRepository;
	@Override
	public Movie saveMovie(Movie movie) {
	return movieRepository.save(movie);
	}


	@Override
	public Movie getMovieById(Long movieId) {
	    Optional<Movie> optionalMovie = movieRepository.findById(movieId);
	    if (optionalMovie.isPresent()) {
	        return optionalMovie.get();
	    }
	    return null; // or throw an exception, depending on your requirement
	}

	@Override
	public Movie getMovieByMovieName(String movieName) {
		 return movieRepository.findBymovieName(movieName);
	}

	@Override
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

}

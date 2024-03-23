package com.mscomm.movieservice.service;

import com.mscomm.movieservice.entity.*;

import java.util.List;

public interface MovieService {
    Movie saveMovie(Movie department);

    Movie getMovieById(Long departmentId);
    Movie getMovieByMovieName(String movieName);

    List<Movie> getAllMovies();
}
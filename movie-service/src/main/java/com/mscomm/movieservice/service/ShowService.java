package com.mscomm.movieservice.service;

import com.mscomm.movieservice.entity.Show;

import java.util.List;

public interface ShowService {
    List<Show> getShowsByMovieIdAndTheatreId(Long movieId, Long theatreId);
}

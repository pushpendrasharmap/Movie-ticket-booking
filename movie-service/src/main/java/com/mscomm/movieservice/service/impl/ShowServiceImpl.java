package com.mscomm.movieservice.service.impl;

import com.mscomm.movieservice.entity.Show;
import com.mscomm.movieservice.repository.ShowRepository;
import com.mscomm.movieservice.service.ShowService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ShowServiceImpl implements ShowService {
    private ShowRepository showRepository;
    @Override
    public List<Show> getShowsByMovieIdAndTheatreId(Long movieId, Long theatreId) {
        return showRepository.findByMovieIdAndTheatreId(movieId, theatreId);
    }
}

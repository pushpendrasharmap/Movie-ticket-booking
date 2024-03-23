package com.mscomm.movieservice.service.impl;

import com.mscomm.movieservice.entity.Theatre;
import com.mscomm.movieservice.repository.TheatreRepository;
import com.mscomm.movieservice.service.TheatreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TheatreServiceImpl implements TheatreService{
	
	 private TheatreRepository theatreRepository;
	@Override
	public Theatre saveTheatre(Theatre theatre) {
	return theatreRepository.save(theatre);
	}

	@Override
	public Theatre getTheatreById(Long theatreId) {
		 return theatreRepository.findById(theatreId).get();
	}

	@Override
	public List<Theatre> getAllTheatres() {
		return theatreRepository.findAll();
	}

	@Override
	public List<Theatre> getTheatresForMovieId(Long movieId) {
		return theatreRepository.getTheatresForMovieId(movieId);
	}

	@Override
	public Theatre getTheatreByTheatreName(String theatreName) {
		 return theatreRepository.findByTheatreName(theatreName);
	}

}

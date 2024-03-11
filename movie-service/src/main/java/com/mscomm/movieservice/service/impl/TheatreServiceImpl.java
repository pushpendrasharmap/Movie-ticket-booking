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
	
	 private TheatreRepository departmentRepository;
	@Override
	public Theatre saveDepartment(Theatre department) {
	return departmentRepository.save(department);
	}

	@Override
	public Theatre getDepartmentById(Long departmentId) {
		 return departmentRepository.findById(departmentId).get();
	}

	@Override
	public List<Theatre> getAllTheatres() {
		return departmentRepository.findAll();
	}

	@Override
	public List<Theatre> getTheatresForMovieId(Long movieId) {
		return departmentRepository.getTheatresForMovieId(movieId);
	}

	@Override
	public Theatre getDepartmentBytheatreName(String theatreName) {
		 return departmentRepository.findBytheatreName(theatreName);
	}

}

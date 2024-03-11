package com.mscomm.movieservice.service;

import com.mscomm.movieservice.entity.*;

import java.util.List;

public interface TheatreService {
    Theatre saveDepartment(Theatre department);
    Theatre getDepartmentBytheatreName(String theatreName);
    Theatre getDepartmentById(Long departmentId);
    List<Theatre> getAllTheatres();
    List<Theatre> getTheatresForMovieId(Long movieId);
}
package com.mscomm.movieservice.service;

import com.mscomm.movieservice.entity.*;

import java.util.List;

public interface DepartmentService {
    Movie saveDepartment(Movie department);

    Movie getDepartmentById(Long departmentId);
    Movie getDepartmentBymovieName(String movieName);

    List<Movie> getAllMovies();
}
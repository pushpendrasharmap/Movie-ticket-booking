package com.mscomm.movieservice.repository;

import com.mscomm.movieservice.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TheatreRepository extends JpaRepository<Theatre,Long> {
Theatre findByTheatreName(String theatreName);

@Query("Select t from Theatre t, Movie m, Show s " +
        "where m.id = :movieId " +
        "and s.movieId = m.id " +
        "and s.theatreId = t.id ")
 List<Theatre> getTheatresForMovieId(Long movieId);
}

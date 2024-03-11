package com.mscomm.movieservice.repository;

import com.mscomm.movieservice.entity.Show;
import com.mscomm.movieservice.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface ShowRepository extends JpaRepository<Show,Long>  {
    List<Show> findByMovieIdAndTheatreId(Long movieId, Long theatreId);
}

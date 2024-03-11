package com.mscomm.movieservice.controller;

import com.mscomm.movieservice.entity.Show;
import com.mscomm.movieservice.entity.Theatre;
import com.mscomm.movieservice.service.ShowService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("api/shows")
@AllArgsConstructor
@RestController
@CrossOrigin(origins="*")
public class ShowController {

    private final ShowService showService;
    @GetMapping("/{movieId}/{theatreId}")
    public ResponseEntity<List<Show>> getShowsByMovieIdAndTheatreId(@PathVariable("movieId") Long movieId,
                                                                 @PathVariable("theatreId") Long theatreId){
        List<Show> shows = showService.getShowsByMovieIdAndTheatreId(movieId, theatreId);
        return ResponseEntity.ok(shows);
    }
}

package com.mscomm.movieservice.controller;

import com.mscomm.movieservice.entity.Theatre;
import com.mscomm.movieservice.service.TheatreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/theatres")
@AllArgsConstructor
@CrossOrigin(origins="*")
public class TheatreController {

	private TheatreService theatreService;

    @PostMapping
    public ResponseEntity<Theatre> saveTheatre(@RequestBody Theatre theatre){
        Theatre savedDepartment = theatreService.saveTheatre(theatre);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Theatre> getTheatreById(@PathVariable("id") Long theatreId){
        Theatre department = theatreService.getTheatreById(theatreId);
        return ResponseEntity.ok(department);
    }
    @GetMapping("/t/{theatreName}")
    public ResponseEntity<Theatre> getTheatreByTheatreName(@PathVariable("theatreName") String theatreName){
        Theatre department = theatreService.getTheatreByTheatreName(theatreName);
        return ResponseEntity.ok(department);
    }
    @GetMapping
    public ResponseEntity<List<Theatre>> getAllTheatres() {
        List<Theatre> department = theatreService.getAllTheatres();
        return ResponseEntity.ok(department);
    }

    @GetMapping("/m/{movieId}")
    public ResponseEntity<List<Theatre>> getTheatresForMovie(@PathVariable("movieId") Long movieId) {

        return ResponseEntity.ok(theatreService.getTheatresForMovieId(movieId));

    }
}

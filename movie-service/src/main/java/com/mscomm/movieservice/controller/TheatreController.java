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

	private TheatreService departmentService;

    @PostMapping
    public ResponseEntity<Theatre> saveDepartment(@RequestBody Theatre department){
        Theatre savedDepartment = departmentService.saveDepartment(department);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Theatre> getDepartmentById(@PathVariable("id") Long departmentId){
        Theatre department = departmentService.getDepartmentById(departmentId);
        return ResponseEntity.ok(department);
    }
    @GetMapping("/t/{theatreName}")
    public ResponseEntity<Theatre> getDepartmentByTheatreName(@PathVariable("theatreName") String theatreName){
        Theatre department = departmentService.getDepartmentBytheatreName(theatreName);
        return ResponseEntity.ok(department);
    }
    @GetMapping
    public ResponseEntity<List<Theatre>> getAllTheatres() {
        List<Theatre> department = departmentService.getAllTheatres();
        return ResponseEntity.ok(department);
    }

    @GetMapping("/m/{movieId}")
    public ResponseEntity<List<Theatre>> getTheatresForMovie(@PathVariable("movieId") Long movieId) {

        return ResponseEntity.ok(departmentService.getTheatresForMovieId(movieId));

    }
}

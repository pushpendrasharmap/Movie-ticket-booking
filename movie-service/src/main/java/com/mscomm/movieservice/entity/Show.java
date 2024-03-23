package com.mscomm.movieservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "shows")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long movieId;
    private Long theatreId;
    private LocalDate date;
    private LocalTime time;
    private Long ticketPrice;
}

package com.mscomm.movieservice.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Table(name = "movies")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Movie {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String movieName;
	    private String movieGenre;
		private String originalLanguage;
		private String originalTitle;
		private String posterPath;
		private String isAdult;
		private String overview;
		private String releaseDate;
		private Float popularity;
	    
}

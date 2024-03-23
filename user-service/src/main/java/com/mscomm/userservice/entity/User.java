package com.mscomm.userservice.entity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
@Entity
@Table(name = "users")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String name;
	    private String password;
	    private String email;

}

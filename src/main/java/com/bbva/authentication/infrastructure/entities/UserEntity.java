package com.bbva.authentication.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "users")
@RequiredArgsConstructor
@Getter
@Setter
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username", nullable = false, length = 50)
	private String username;
	
	@Column(name = "password", nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false)
	private String salt;
	
	public UserEntity(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
}

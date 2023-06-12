package com.bbva.authentication.infrastructure.repositories;

import com.bbva.authentication.infrastructure.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl {

	private final UserSpringRepository userSpringRepository;
	
	private BCryptPasswordEncoder passwordEncoder;
	public void save(UserEntity user) {
		// Genera una sal aleatoria para el usuario
		String salt = BCrypt.gensalt();
		// Crea el hash de la contraseña con la sal generada
		String hashedPassword = BCrypt.hashpw(user.getPassword(), salt);
		// Almacena la sal y el hash en la base de datos
		user.setPassword(hashedPassword);
		user.setSalt(salt);
		userSpringRepository.save(user);
	}
	
	
	
	
	public boolean authenticateUser(String username, String password) {
		UserEntity user = userSpringRepository.findByUsername(username);
		if (user == null) {
			return false;
		}
		String hashedPassword = BCrypt.hashpw(password, user.getSalt());
		return hashedPassword.equals(user.getPassword());
	}
	
	public UserEntity findByUsername(String username) {
		return userSpringRepository.findByUsername(username);
	}
}

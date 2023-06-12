package com.bbva.authentication.infrastructure.repositories;

import com.bbva.authentication.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSpringRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByUsername(String username);
}

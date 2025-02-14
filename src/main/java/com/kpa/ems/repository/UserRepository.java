package com.kpa.ems.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kpa.ems.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
	@Query(value = "SELECT * FROM users WHERE user_email = ?1", nativeQuery = true)
    Optional<UserEntity> getUserByEmailId(String email);
}

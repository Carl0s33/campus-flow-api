package com.campusflow.api.domain.repository;

import com.campusflow.api.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByMatricula(String matricula);
    Optional<User> findByEmail(String email);
    boolean existsByMatricula(String matricula);
    boolean existsByEmail(String email);
}

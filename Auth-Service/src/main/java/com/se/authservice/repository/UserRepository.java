package com.se.authservice.repository;

import com.se.authservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccount_Username(String username);

    Optional<User> findByAccount_Id(Long id);
}

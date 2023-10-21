package com.chalenges.userservice.repository;

import com.chalenges.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author lady Cuizara
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUsersByEmail(String email);

    Optional<User> findUsersByName(String name);

}

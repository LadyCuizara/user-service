package com.chalenges.userservice.repository;

import com.chalenges.userservice.models.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lady Cuizara
 */
public interface UserSessionRepository extends JpaRepository<UserSession, Long> {

}

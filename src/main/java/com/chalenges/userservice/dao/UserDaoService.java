package com.chalenges.userservice.dao;
import com.chalenges.userservice.models.User;
import java.util.Optional;

/**
 * @author lady Cuizara
 */
public interface UserDaoService {
    User save(User user);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByName(String name);
}

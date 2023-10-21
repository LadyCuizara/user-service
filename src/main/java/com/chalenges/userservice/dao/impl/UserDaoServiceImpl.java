package com.chalenges.userservice.dao.impl;

import com.chalenges.userservice.dao.UserDaoService;
import com.chalenges.userservice.models.User;
import com.chalenges.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author lady Cuizara
 */
@Service
public class UserDaoServiceImpl implements UserDaoService {

    private UserRepository userRepository;

    public UserDaoServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return this.userRepository.findUsersByEmail(email);
    }

    @Override
    public Optional<User> findUserByName(String name) {
        return this.userRepository.findUsersByName(name);
    }
}

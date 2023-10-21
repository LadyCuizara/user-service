package com.chalenges.userservice.services.impl;

import com.chalenges.userservice.dao.UserDaoService;
import com.chalenges.userservice.dao.UserSessionDaoService;
import com.chalenges.userservice.exceptions.EmailFormatException;
import com.chalenges.userservice.exceptions.UserAlreadyExistsException;
import com.chalenges.userservice.models.Phone;
import com.chalenges.userservice.models.User;
import com.chalenges.userservice.models.UserSession;
import com.chalenges.userservice.request.PhoneRequest;
import com.chalenges.userservice.request.UserRequest;
import com.chalenges.userservice.responses.RegisterUserResponse;
import com.chalenges.userservice.services.JwtService;
import com.chalenges.userservice.services.RegisterUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lady Cuizara
 */
@Service
public class RegisterUserServiceImpl implements RegisterUserService {
    private UserDaoService userDaoService;

    private UserSessionDaoService userSessionDaoService;
    private PasswordEncoder passwordEncoder;

    private JwtService jwtService;

    @Value("${email.validation.regex}")
    private String emailValidationRegex;

    public RegisterUserServiceImpl(UserDaoService userDaoService,
                                   UserSessionDaoService userSessionDaoService,
                                   PasswordEncoder passwordEncoder,
                                   JwtService jwtService) {
        this.userDaoService = userDaoService;
        this.userSessionDaoService = userSessionDaoService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public RegisterUserResponse registerUser(UserRequest userRequest) {
        validateUserRequest(userRequest);

        User user = createUser(userRequest);

        User userDB = userDaoService.save(user);

        UserSession session = createUserSession(userDB);

        UserSession sessionDB = userSessionDaoService.save(session);

        return new RegisterUserResponse(userDB, sessionDB);
    }

    private void validateUserRequest(UserRequest userRequest) {
        verifyEmailFormat(userRequest.getEmail());
        verifyIfEmailExists(userRequest.getEmail());
    }

    private User createUser(UserRequest userRequest) {
        User user = new User.UserBuilder()
                .setId(UUID.randomUUID())
                .setName(userRequest.getName())
                .setEmail(userRequest.getEmail())
                .setPassword(encryptPassword(userRequest.getPassword()))
                .setCreated(new Date())
                .setModified(new Date())
                .build();

        List<Phone> phones = createPhones(userRequest.getPhones(), user);

        user.setPhones(phones);

        return user;
    }

    private List<Phone> createPhones(List<PhoneRequest> phoneRequests, User user) {
        return phoneRequests.stream()
                .map(p -> new Phone.PhoneBuilder()
                        .setNumber(p.getNumber())
                        .setCityCode(p.getCityCode())
                        .setCountryCode(p.getCountryCode())
                        .setUser(user)
                        .build())
                .toList();
    }

    private String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }


    private void verifyIfEmailExists(String email) {
        Optional<User> user = userDaoService.findUserByEmail(email);
        if (user.isPresent()) {
            throw new UserAlreadyExistsException();
        }
    }

    private void verifyEmailFormat(String email) {
        String regex = emailValidationRegex;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new EmailFormatException();
        }
    }

    private UserSession createUserSession(User user) {
        return new UserSession.UserSessionBuilder()
                .setUserId(user.getId())
                .setLastLogin(new Date())
                .setToken(jwtService.generateToken(user.getName()))
                .setIsActive(true)
                .build();
    }
}

package com.chalenges.userservice.services.impl;

import com.chalenges.userservice.dao.UserDaoService;
import com.chalenges.userservice.dao.UserSessionDaoService;
import com.chalenges.userservice.exceptions.EmailFormatException;
import com.chalenges.userservice.exceptions.UserAlreadyExistsException;
import com.chalenges.userservice.models.User;
import com.chalenges.userservice.models.UserSession;
import com.chalenges.userservice.request.PhoneRequest;
import com.chalenges.userservice.request.UserRequest;
import com.chalenges.userservice.responses.RegisterUserResponse;
import com.chalenges.userservice.services.JwtService;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author lady Cuizara
 */
public class RegisterUserServiceImplTest {

    private RegisterUserServiceImpl target;

    private UserDaoService userDaoService;

    private UserSessionDaoService userSessionDaoService;

    private PasswordEncoder passwordEncoder;

    private JwtService jwtService;

    private static final String emailValidationRegex = "^[A-Za-z0-9+_.-]+@(.+)$";

    @BeforeEach
    public void beforeTest() {
        userDaoService = Mockito.mock(UserDaoService.class);
        userSessionDaoService = Mockito.mock(UserSessionDaoService.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        jwtService = Mockito.mock(JwtService.class);

        target = new RegisterUserServiceImpl(
                userDaoService,
                userSessionDaoService,
                passwordEncoder,
                jwtService);

        ReflectionTestUtils.setField(target, "emailValidationRegex", emailValidationRegex);
    }

    @Test
    void shouldSaveUser() {
        // Arrange
        PhoneRequest phoneRequest = new PhoneRequest();
        phoneRequest.setNumber(123456789);
        phoneRequest.setCountryCode(591);
        phoneRequest.setCityCode(4);

        UserRequest userRequest = new UserRequest();
        userRequest.setName("Juan");
        userRequest.setPassword("Password1!");
        userRequest.setEmail("juan@rodriguez.org");
        userRequest.setPhones(Collections.singletonList(phoneRequest));

        User user = mock(User.class);
        UserSession userSession = mock(UserSession.class);

        when(userDaoService.findUserByEmail(userRequest.getEmail())).thenReturn(Optional.empty());
        when(userDaoService.save(any())).thenReturn(user);
        when(userSessionDaoService.save(any())).thenReturn(userSession);
        when(passwordEncoder.encode(userRequest.getPassword())).thenReturn("encrypted");
        when(jwtService.generateToken(any())).thenReturn("token");

        // Act
        RegisterUserResponse response = target.registerUser(userRequest);

        // Assert
        Assert.notNull(response);
        verify(userDaoService, times(1)).findUserByEmail(userRequest.getEmail());
        verify(userDaoService, times(1)).save(any());
        verify(userSessionDaoService, times(1)).save(any());
        verify(passwordEncoder, times(1)).encode(userRequest.getPassword());
        verify(jwtService, times(1)).generateToken(any());
    }

    @Test
    void shouldFailIfEmailIsInvalid() {
        // Arrange
        PhoneRequest phoneRequest = new PhoneRequest();
        phoneRequest.setNumber(123456789);
        phoneRequest.setCountryCode(591);
        phoneRequest.setCityCode(4);

        UserRequest userRequest = new UserRequest();
        userRequest.setName("Juan");
        userRequest.setPassword("Password1!");
        userRequest.setEmail("invalid");
        userRequest.setPhones(Collections.singletonList(phoneRequest));

        // Act and Assert
        assertThrows(EmailFormatException.class, () -> target.registerUser(userRequest));
    }

    @Test
    void shouldFailIfEmailAlreadyExists() {
        // Arrange
        PhoneRequest phoneRequest = new PhoneRequest();
        phoneRequest.setNumber(123456789);
        phoneRequest.setCountryCode(591);
        phoneRequest.setCityCode(4);

        UserRequest userRequest = new UserRequest();
        userRequest.setName("Juan");
        userRequest.setPassword("Password1!");
        userRequest.setEmail("juan@rodriguez.org");
        userRequest.setPhones(Collections.singletonList(phoneRequest));

        User user = mock(User.class);

        when(userDaoService.findUserByEmail(userRequest.getEmail())).thenReturn(Optional.of(user));

        // Act and Assert
        assertThrows(UserAlreadyExistsException.class, () -> target.registerUser(userRequest));
    }
}

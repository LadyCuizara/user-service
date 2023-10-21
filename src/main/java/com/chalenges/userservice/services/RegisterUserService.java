package com.chalenges.userservice.services;

import com.chalenges.userservice.request.UserRequest;
import com.chalenges.userservice.responses.RegisterUserResponse;

/**
 * @author lady Cuizara
 */
public interface RegisterUserService {

    RegisterUserResponse registerUser(UserRequest request);
}

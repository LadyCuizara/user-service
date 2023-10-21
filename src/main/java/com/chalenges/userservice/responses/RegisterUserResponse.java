package com.chalenges.userservice.responses;

import com.chalenges.userservice.models.User;
import com.chalenges.userservice.models.UserSession;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author lady Cuizara
 */
public class RegisterUserResponse {
    private User user;

    @JsonProperty("session")
    private UserSession userSession;

    public RegisterUserResponse(User user, UserSession userSession) {
        this.user = user;
        this.userSession = userSession;
    }

    public User getUser() {
        return user;
    }

    public UserSession getUserSession() {
        return userSession;
    }
}

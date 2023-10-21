package com.chalenges.userservice.responses;

import java.util.Date;

/**
 * @author lady Cuizara
 */
public class UserSessionResponse {

    private Date lastLogin;

    private String token;

    private boolean isActive;

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

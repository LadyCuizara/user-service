package com.chalenges.userservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

/**
 * @author lady Cuizara
 */
@Entity
@Table(name = "userSession")
public class UserSession {

    @Id
    private UUID userId;

    @Column(name = "lastLogin")
    private Date lastLogin;

    @Column(name = "token")
    private String token;

    @Column(name = "isActive")
    private boolean isActive;

    public UserSession() {

    }

    public UserSession(UserSessionBuilder builder) {
        this.userId = builder.userId;
        this.lastLogin = builder.lastLogin;
        this.token = builder.token;
        this.isActive = builder.isActive;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

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

    public static class UserSessionBuilder {

        private UUID userId;

        private Date lastLogin;

        private String token;

        private boolean isActive;

        public UserSessionBuilder setUserId(UUID userId) {
            this.userId = userId;
            return this;
        }

        public UserSessionBuilder setLastLogin(Date lastLogin) {
            this.lastLogin = lastLogin;
            return this;
        }

        public UserSessionBuilder setToken(String token) {
            this.token = token;
            return this;
        }

        public UserSessionBuilder setIsActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public UserSession build() {
            return new UserSession(this);
        }
    }
}

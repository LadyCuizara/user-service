package com.chalenges.userservice.exceptions;

/**
 * @author lady Cuizara
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("Usuario no encontrado");
    }
}

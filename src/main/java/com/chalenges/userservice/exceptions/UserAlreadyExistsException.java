package com.chalenges.userservice.exceptions;

/**
 * @author lady Cuizara
 */
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("El correo ya esta registrado");
    }
}

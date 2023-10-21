package com.chalenges.userservice.exceptions;

/**
 * @author lady Cuizara
 */
public class EmailFormatException extends RuntimeException {
    public EmailFormatException() {
        super("El formato del correo es incorrecto");
    }
}

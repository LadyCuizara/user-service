package com.chalenges.userservice.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author lady Cuizara
 */
public class ExceptionResponse {
    @JsonProperty("mensaje")
    private String message;

    public ExceptionResponse(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

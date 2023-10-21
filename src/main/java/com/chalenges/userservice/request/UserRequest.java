package com.chalenges.userservice.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author lady Cuizara
 */
public class UserRequest {
    @NotBlank(message = "El nombre no debe estar en blanco")
    @NotNull(message = "El nombre no debe ser nulo")
    private String name;

    @NotBlank(message = "El email no debe estar en blanco")
    @NotNull(message = "El email no debe ser nulo")
    private String email;

    @NotBlank(message = "El password no debe estar en blanco")
    @NotNull(message = "El password no debe ser nulo")
    private String password;

    private List<PhoneRequest> phones;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PhoneRequest> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneRequest> phones) {
        this.phones = phones;
    }
}

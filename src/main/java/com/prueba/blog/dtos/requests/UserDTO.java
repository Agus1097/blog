package com.prueba.blog.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    @JsonProperty("first_name")
    @NotEmpty(message = "El nombre es obligatorio")
    private String firstName;

    @JsonProperty("last_name")
    @NotEmpty(message = "El apellido es obligatorio")
    private String lastName;

    @Pattern(regexp = "", message = "El correo electrónico es inválido")
    @NotEmpty(message = "El correo electrónico es obligatorio")
    private String email;

    @NotEmpty(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 30, message = "La contraseña debe tenes entre 8 y 30 caracteres")
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
}

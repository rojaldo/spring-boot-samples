package com.example.demo.customer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CustomerForm {

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Only alphanumeric characters are allowed")
    private String name;

    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Only alphanumeric characters are allowed")
    private String lastName;

    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z]+.[a-zA-Z]+", message = "Only alphanumeric characters are allowed")
    private String email;



    // getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String toString() {
        return "CustomerForm{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}

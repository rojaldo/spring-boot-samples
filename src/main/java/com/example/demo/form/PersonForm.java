package com.example.demo.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PersonForm {
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Only alphanumeric characters are allowed")
    private String name;
    @NotNull
    @Min(18)
    private Integer age;

    // getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String toString() {
        return "Person(Name: " + this.name + ", Age: " + this.age + ")";
    }
}

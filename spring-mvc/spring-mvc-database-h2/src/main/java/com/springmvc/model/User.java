package com.springmvc.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class User {

    @NotBlank(message = "User name can't be empty")
    public String name;

    @Range(min = 0, message = "User age can't be smaller than 0")
    private String age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }
}

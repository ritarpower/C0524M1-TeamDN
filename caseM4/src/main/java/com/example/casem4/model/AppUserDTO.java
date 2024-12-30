package com.example.casem4.model;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AppUserDTO implements Validator {

    private int id;
// tự code valid đi!
    private String username;

    private String password;

    private String name;

    private String address;

    private String email;

    private Integer phone;

    private AppRole role;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
//        xử lý validate thì code vào đây
    }
}

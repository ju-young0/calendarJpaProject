package org.example.calendarjapproject.dto;

import lombok.Getter;

@Getter
public class UserSignUpRequestDto {

    private final String username;

    private final String email;

    private final String password;

    public UserSignUpRequestDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

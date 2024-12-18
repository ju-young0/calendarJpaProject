package org.example.calendarjapproject.dto;

import lombok.Getter;
import org.example.calendarjapproject.entity.User;

import java.time.LocalDateTime;

@Getter
public class UserSignUpResponseDto {

    private final Long id;

    private final String username;

    private final String email;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public UserSignUpResponseDto(Long id, String username, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // toDto를 활용하여 Entity를 User에서 UserSignUpResponseDto로 변환하는 메서드
    public static UserSignUpResponseDto toDto(User user) {

        return new UserSignUpResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt(), user.getUpdatedAt());
    }
}

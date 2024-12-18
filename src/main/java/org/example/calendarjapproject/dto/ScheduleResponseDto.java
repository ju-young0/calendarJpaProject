package org.example.calendarjapproject.dto;

import lombok.Getter;
import org.example.calendarjapproject.entity.Schedule;
import org.example.calendarjapproject.entity.User;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {

    private final Long id;
    private final Long userId;
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ScheduleResponseDto(Long id, Long userId, String title, String contents, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ScheduleResponseDto toDto(Schedule schedule) {

        return new ScheduleResponseDto(schedule.getId(), schedule.getUser().getId(), schedule.getTitle(), schedule.getContents(), schedule.getCreatedAt(), schedule.getUpdatedAt());
    }
}

package org.example.calendarjapproject.dto;

import lombok.Getter;

@Getter
public class ScheduleCreateRequestDto {

    private final Long userId;

    private final String title;

    private final String contents;

    public ScheduleCreateRequestDto(Long userId, String title, String contents) {
        this.userId = userId;
        this.title = title;
        this.contents = contents;
    }

}

package org.example.calendarjapproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleCreateRequestDto {

    @NotBlank
    @Size(max = 10)
    private final String title;

    @NotBlank
    private final String contents;

    public ScheduleCreateRequestDto( String title, String contents) {

        this.title = title;
        this.contents = contents;
    }

}

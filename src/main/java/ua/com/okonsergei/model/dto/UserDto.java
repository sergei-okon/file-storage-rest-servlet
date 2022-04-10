package ua.com.okonsergei.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private Long id;
    private String name;
    private List<EventDto> eventsDto;
}

package ua.com.okonsergei.converter;

import ua.com.okonsergei.model.dto.UserDto;
import ua.com.okonsergei.repository.entity.User;

import java.util.stream.Collectors;

public class UserConverter {

    public static UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        if (user == null) {
            userDto = null;
        } else {
            userDto.setId(user.getId());
            userDto.setName(user.getName());

            if (user.getEvents() == null) {
                userDto.setEventsDto(null);
            } else {
                userDto.setEventsDto(user.getEvents().stream()
                        .map(EventConverter::convertToDto)
                        .collect(Collectors.toList()));
            }
        }
        return userDto;
    }

    public static User convertToEntity(UserDto userDto) {
        User user = new User();
        if (userDto == null) {
            user = null;
        } else {
            user.setId(userDto.getId());
            user.setName(userDto.getName());
            if (userDto.getEventsDto() == null) {
                user.setEvents(null);
            } else {
                user.setEvents(userDto.getEventsDto().stream()
                        .map(EventConverter::convertToEntity)
                        .collect(Collectors.toList()));
            }
        }
        return user;
    }
}
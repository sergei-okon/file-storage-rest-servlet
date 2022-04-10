package ua.com.okonsergei.converter;

import ua.com.okonsergei.model.dto.EventDto;
import ua.com.okonsergei.repository.db.hibernate.FileEntityRepositoryImpl;
import ua.com.okonsergei.repository.db.hibernate.UserEntityRepositoryImpl;
import ua.com.okonsergei.repository.entity.Event;
import ua.com.okonsergei.service.FileService;
import ua.com.okonsergei.service.UserService;
import ua.com.okonsergei.utils.ConverterLocalDateTime;

public class EventConverter {

    private static final UserService userService = new UserService(new UserEntityRepositoryImpl());
    private static final FileService fileService = new FileService(new FileEntityRepositoryImpl());

    public static EventDto convertToDto(Event event) {
        EventDto eventDto = new EventDto();
        if (event == null) {
            eventDto = null;
        } else {
            if (event.getId() == null) {
                eventDto.setId(null);
            } else {
                eventDto.setId(event.getId());
            }
            eventDto.setUpdated(ConverterLocalDateTime.convertLocalDateTimeToLong(event.getUpdated()));
            eventDto.setCreated(ConverterLocalDateTime.convertLocalDateTimeToLong(event.getCreated()));
            if (event.getUser() == null) {
                eventDto.setUserId(null);
            } else {
                eventDto.setUserId(event.getUser().getId());
            }
            if (event.getFile() == null) {
                eventDto.setFileId(null);
            } else {
                eventDto.setFileId(event.getFile().getId());
            }
        }
        return eventDto;
    }

    public static Event convertToEntity(EventDto eventDto) {
        Event event = new Event();
        if (eventDto == null) {
            event = null;
        } else {
            if (eventDto.getId() == null) {
                event.setId(null);
            } else {
                event.setId(eventDto.getId());
                event.setCreated(ConverterLocalDateTime.convertLongToLocalDateTime(eventDto.getCreated()));
                event.setUpdated(ConverterLocalDateTime.convertLongToLocalDateTime(eventDto.getUpdated()));
            }

            if (eventDto.getUserId() == null) {
                event.setUser(null);
            } else {
                event.setUser(UserConverter.convertToEntity(userService.findById(eventDto.getUserId())));
            }
            if (eventDto.getFileId() == null) {
                event.setFile(null);
            } else {
                event.setFile(FileConverter.convertToEntity(fileService.findById(eventDto.getFileId())));
            }
        }
        return event;
    }
}

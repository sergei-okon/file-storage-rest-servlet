package ua.com.okonsergei.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.com.okonsergei.converter.EventConverter;
import ua.com.okonsergei.model.dto.EventDto;
import ua.com.okonsergei.repository.EventRepository;
import ua.com.okonsergei.repository.db.hibernate.EventEntityRepositoryImpl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class EventServiceTest {

    EventRepository eventRepositoryMock;
    EventService eventService;

    @BeforeEach
    void setUp() {
        eventRepositoryMock = mock(EventEntityRepositoryImpl.class);
        eventService = new EventService(eventRepositoryMock);
    }

    @Test
    void findAllSuccess() {
        eventService.findAll();
        verify(eventRepositoryMock).findAll();
    }

    @Test
    void findById_Success() {
        Long id = 1L;
        eventService.findById(id);
        verify(eventRepositoryMock).findById(id);
    }

    @Test
    void save_Success() {
        EventDto eventDto = new EventDto();
        eventDto.setId(1L);
        eventDto.setUpdated(1646734746000L);
        eventDto.setCreated(1646734743000L);
        eventDto.setFileId(2L);
        eventDto.setUserId(2L);

        eventService.save(eventDto);
        verify(eventRepositoryMock).save(EventConverter.convertToEntity(eventDto));
    }

    @Test
    void deleteById_Success() {
        Long id = 1L;
        eventService.deleteById(id);
        verify(eventRepositoryMock).deleteById(id);
    }

    @Test
    void update_Success() {
        EventDto eventDto = new EventDto();
        eventDto.setId(1L);
        eventDto.setUpdated(1646734746000L);
        eventDto.setCreated(1646734743000L);
        eventDto.setFileId(2L);
        eventDto.setUserId(2L);

        eventService.update(eventDto);
        verify(eventRepositoryMock).update(EventConverter.convertToEntity(eventDto));
    }
}
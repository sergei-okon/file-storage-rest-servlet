package ua.com.okonsergei.service;

import ua.com.okonsergei.converter.EventConverter;
import ua.com.okonsergei.model.dto.EventDto;
import ua.com.okonsergei.repository.EventRepository;
import ua.com.okonsergei.repository.entity.Event;

import java.util.List;
import java.util.stream.Collectors;

public class EventService {

    EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventDto> findAll() {
        return eventRepository.findAll().stream().map(EventConverter::convertToDto).collect(Collectors.toList());

    }

    public EventDto findById(Long id) {
        return EventConverter.convertToDto(eventRepository.findById(id));
    }

    public Event save(EventDto eventDto) {
        return eventRepository.save(EventConverter.convertToEntity(eventDto));
    }

    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    public Long update(EventDto eventDto) {
        return eventRepository.update(EventConverter.convertToEntity(eventDto));
    }
}

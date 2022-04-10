package ua.com.okonsergei.servlet.event;

import ua.com.okonsergei.converter.EventConverter;
import ua.com.okonsergei.model.dto.EventDto;
import ua.com.okonsergei.repository.db.hibernate.EventEntityRepositoryImpl;
import ua.com.okonsergei.repository.entity.Event;
import ua.com.okonsergei.service.EventService;
import ua.com.okonsergei.utils.ConverterLocalDateTime;
import ua.com.okonsergei.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "AddEventsServlet", value = "/events/new")
public class AddEventServlet extends HttpServlet {

    private final EventService eventService = new EventService(new EventEntityRepositoryImpl());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (ServletUtils.eventRequestIsValid(request)) {
            Long userId = Long.parseLong(request.getParameter("user_id"));
            Long fileId = Long.parseLong(request.getParameter("file_id"));

            EventDto eventDto = new EventDto();
            eventDto.setCreated(ConverterLocalDateTime.convertLocalDateTimeToLong(LocalDateTime.now()));
            eventDto.setUpdated(ConverterLocalDateTime.convertLocalDateTimeToLong(LocalDateTime.now()));
            eventDto.setUserId(userId);
            eventDto.setFileId(fileId);

            Event savedEvent = eventService.save(eventDto);
            ServletUtils.createResponseJson(response, EventConverter.convertToDto(savedEvent));
        } else {
            response.sendRedirect(request.getContextPath() + "/errorHandler");
        }
    }
}

package ua.com.okonsergei.servlet.event;

import ua.com.okonsergei.model.dto.EventDto;
import ua.com.okonsergei.repository.db.hibernate.EventEntityRepositoryImpl;
import ua.com.okonsergei.service.EventService;
import ua.com.okonsergei.utils.ConverterLocalDateTime;
import ua.com.okonsergei.utils.ServletUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "UpdateEventServlet", value = "/events/update")
public class UpdateEventServlet extends HttpServlet {

    private final EventService eventService = new EventService(new EventEntityRepositoryImpl());

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        EventDto eventDto = eventService.findById(Long.parseLong(request.getParameter("id")));

        if (ServletUtils.idIsNumber(request) && eventDto.getId() != null) {
            Long userId = Long.parseLong(request.getParameter("user_id"));
            Long fileId = Long.parseLong(request.getParameter("file_id"));
            eventDto.setUserId(userId);
            eventDto.setFileId(fileId);
            eventDto.setUpdated(ConverterLocalDateTime.convertLocalDateTimeToLong(LocalDateTime.now()));
            eventService.update(eventDto);

            ServletUtils.createResponseJson(response, eventDto);
        } else {
            response.sendRedirect(request.getContextPath() + "/errorHandler");
        }
    }
}

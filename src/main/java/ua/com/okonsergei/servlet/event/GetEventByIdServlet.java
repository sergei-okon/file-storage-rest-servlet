package ua.com.okonsergei.servlet.event;

import ua.com.okonsergei.model.dto.EventDto;
import ua.com.okonsergei.repository.db.hibernate.EventEntityRepositoryImpl;
import ua.com.okonsergei.service.EventService;
import ua.com.okonsergei.utils.ServletUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetEventsByIdServlet", value = "/events/")
public class GetEventByIdServlet extends HttpServlet {

    private final EventService eventService = new EventService(new EventEntityRepositoryImpl());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (ServletUtils.idIsNumber(request)) {
            EventDto eventDto = eventService.findById(Long.parseLong(request.getParameter("id")));
            ServletUtils.createResponseJson(response, eventDto);
        } else {
            response.sendRedirect(request.getContextPath() + "/errorHandler");
        }
    }
}

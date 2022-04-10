package ua.com.okonsergei.servlet.event;

import ua.com.okonsergei.repository.db.hibernate.EventEntityRepositoryImpl;
import ua.com.okonsergei.service.EventService;
import ua.com.okonsergei.utils.ServletUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteEventServlet", value = "/events/delete")
public class DeleteEventServlet extends HttpServlet {

    private final EventService eventService = new EventService(new EventEntityRepositoryImpl());

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("id");

        if (ServletUtils.idIsNumber(request) && idStr != null &&
                eventService.findById(Long.parseLong(idStr)) != null) {

            eventService.deleteById(Long.parseLong(idStr));
            ServletUtils.createResponseJson(response, "Delete operation is successful");
        } else {
            ServletUtils.createResponseJson(response, "Unable to delete Event from database");
        }
    }
}
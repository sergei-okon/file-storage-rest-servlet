package ua.com.okonsergei.servlet.user;

import ua.com.okonsergei.model.dto.UserDto;
import ua.com.okonsergei.repository.db.hibernate.UserEntityRepositoryImpl;
import ua.com.okonsergei.service.UserService;
import ua.com.okonsergei.utils.ServletUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetUserByIdServlet", value = "/users/")
public class GetUserByIdServlet extends HttpServlet {

    private final UserService userService = new UserService(new UserEntityRepositoryImpl());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (ServletUtils.idIsNumber(request)) {
            UserDto userDto = userService.findById(Long.parseLong(request.getParameter("id")));
            ServletUtils.createResponseJson(response, userDto);
        } else {
            response.sendRedirect(request.getContextPath() + "/errorHandler");
        }
    }
}

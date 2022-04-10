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

@WebServlet(name = "UpdateUserServlet", value = "/users/update")
public class UpdateUserServlet extends HttpServlet {

    private final UserService userService = new UserService(new UserEntityRepositoryImpl());

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDto userDto = userService.findById(Long.parseLong(request.getParameter("id")));

        if (ServletUtils.idIsNumber(request) && userDto != null) {
            String name = request.getParameter("name");
            userDto.setName(name);
            userService.update(userDto);

            ServletUtils.createResponseJson(response, userDto);
        } else {
            response.sendRedirect(request.getContextPath() + "/errorHandler");
        }
    }
}

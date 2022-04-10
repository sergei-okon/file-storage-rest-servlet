package ua.com.okonsergei.servlet.user;

import ua.com.okonsergei.model.dto.UserDto;
import ua.com.okonsergei.repository.db.hibernate.UserEntityRepositoryImpl;
import ua.com.okonsergei.repository.entity.User;
import ua.com.okonsergei.service.UserService;
import ua.com.okonsergei.utils.ServletUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddNewUserServlet", value = "/users/new")
public class AddUserServlet extends HttpServlet {

    UserService userService = new UserService(new UserEntityRepositoryImpl());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");

        if (name != null && !name.trim().isEmpty()) {
            UserDto userDto = new UserDto();
            userDto.setName(name);
            User user = userService.save(userDto);
            userDto.setId(user.getId());

            ServletUtils.createResponseJson(response, userDto);
        } else {
            response.sendRedirect(request.getContextPath() + "/errorHandler");
        }
    }
}

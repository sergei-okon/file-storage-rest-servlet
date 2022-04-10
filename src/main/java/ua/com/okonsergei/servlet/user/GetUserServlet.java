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
import java.util.List;

@WebServlet(name = "GetUserServlet", value = "/users")
public class GetUserServlet extends HttpServlet {
    private final UserService userService = new UserService(new UserEntityRepositoryImpl());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<UserDto> usersDto = userService.findAll();
        ServletUtils.createResponseJson(response, usersDto);
    }
}

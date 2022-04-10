package ua.com.okonsergei.servlet.user;

import ua.com.okonsergei.repository.db.hibernate.UserEntityRepositoryImpl;
import ua.com.okonsergei.service.UserService;
import ua.com.okonsergei.utils.ServletUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteUserServlet", value = "/users/delete")
public class DeleteUserServlet extends HttpServlet {

    private final UserService userService = new UserService(new UserEntityRepositoryImpl());

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("id");

        if (ServletUtils.idIsNumber(request) && userService.findById(Long.parseLong(idStr)) != null) {
            userService.deleteById(Long.parseLong(idStr));
            ServletUtils.createResponseJson(response, "Delete operation is successful");
        } else {
            ServletUtils.createResponseJson(response, "Unable to delete User from database");
        }
    }
}

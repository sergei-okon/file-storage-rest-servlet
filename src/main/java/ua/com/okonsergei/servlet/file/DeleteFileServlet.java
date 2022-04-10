package ua.com.okonsergei.servlet.file;

import ua.com.okonsergei.repository.db.hibernate.FileEntityRepositoryImpl;
import ua.com.okonsergei.service.FileService;
import ua.com.okonsergei.utils.ServletUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteFileServlet", value = "/files/delete")
public class DeleteFileServlet extends HttpServlet {

    private final FileService fileService = new FileService(new FileEntityRepositoryImpl());

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("id");

        if (ServletUtils.idIsNumber(request) && fileService.findById(Long.parseLong(idStr)) != null) {
            fileService.deleteById(Long.parseLong(idStr));
            ServletUtils.createResponseJson(response, "Delete operation is successful");
        } else {
            ServletUtils.createResponseJson(response, "Unable to delete File from database");
        }
    }
}

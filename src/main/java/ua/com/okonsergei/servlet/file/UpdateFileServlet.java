package ua.com.okonsergei.servlet.file;

import ua.com.okonsergei.model.dto.FileDto;
import ua.com.okonsergei.repository.db.hibernate.FileEntityRepositoryImpl;
import ua.com.okonsergei.service.FileService;
import ua.com.okonsergei.utils.ServletUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateFileServlet", value = "/files/update")
public class UpdateFileServlet extends HttpServlet {

    private final FileService fileService = new FileService(new FileEntityRepositoryImpl());

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FileDto fileDto = fileService.findById(Long.parseLong(request.getParameter("id")));

        if (ServletUtils.idIsNumber(request) && fileDto != null) {
            String fileName = request.getParameter("fileName");
            String path = request.getParameter("path");
            fileDto.setFileName(fileName);
            fileDto.setPath(path);
            fileService.update(fileDto);

            ServletUtils.createResponseJson(response, fileDto);
        } else {
            response.sendRedirect(request.getContextPath() + "/errorHandler");
        }
    }
}
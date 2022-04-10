package ua.com.okonsergei.servlet.file;

import ua.com.okonsergei.model.dto.FileDto;
import ua.com.okonsergei.repository.db.hibernate.FileEntityRepositoryImpl;
import ua.com.okonsergei.repository.entity.File;
import ua.com.okonsergei.service.FileService;
import ua.com.okonsergei.utils.ServletUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddFileServlet", value = "/files/new")
public class AddFileServlet extends HttpServlet {

    private final FileService fileService = new FileService(new FileEntityRepositoryImpl());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (ServletUtils.fileRequestIsValid(request)) {
            String fileName = request.getParameter("fileName");
            String path = request.getParameter("path");

            FileDto fileDto = new FileDto();
            fileDto.setFileName(fileName);
            fileDto.setPath(path);
            File savedFile = fileService.save(fileDto);

            ServletUtils.createResponseJson(response, savedFile);
        } else {
            response.sendRedirect(request.getContextPath() + "/errorHandler");
        }
    }
}


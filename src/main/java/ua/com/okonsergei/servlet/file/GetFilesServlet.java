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
import java.util.List;

@WebServlet(name = "GetFilesServlet", value = "/files")
public class GetFilesServlet extends HttpServlet {

    private final FileService fileService = new FileService(new FileEntityRepositoryImpl());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<FileDto> filesDto = fileService.findAll();
        ServletUtils.createResponseJson(response, filesDto);
    }
}

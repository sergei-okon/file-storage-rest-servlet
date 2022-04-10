package ua.com.okonsergei.utils;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletUtils {

    private static final String ONLY_DIGITS = "[0-9]+";

    public static boolean idIsNumber(HttpServletRequest request) {
        final String id = request.getParameter("id");
        return id != null && (id.length() > 0) && id.matches(ONLY_DIGITS);
    }

    public static boolean fileRequestIsValid(HttpServletRequest request) {
        final String name = request.getParameter("fileName");
        final String path = request.getParameter("path");

        return name != null && !name.trim().isEmpty() &&
                path != null && !path.trim().isEmpty();
    }

    public static boolean eventRequestIsValid(HttpServletRequest request) {
        final String user_idStr = request.getParameter("user_id");
        final String file_idStr = request.getParameter("file_id");

        return user_idStr != null && (user_idStr.length() > 0) && user_idStr.matches(ONLY_DIGITS) &&
                file_idStr != null && (file_idStr.length() > 0) && file_idStr.matches(ONLY_DIGITS);

    }

    public static <T> void createResponseJson(HttpServletResponse response, T t) throws IOException {
        String json = new Gson().toJson(t);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();
    }
}

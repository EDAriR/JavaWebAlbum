package utility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;


@WebServlet("/utility.delete")
public class delete extends HttpServlet {
    private static final long serialVersionUID = 1L;
    String saveDirectory = "/images_uploaded";


    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("Big5"); // 處理中文檔名
        res.setContentType("text/html; charset=Big5");

        PrintWriter out = res.getWriter();
        Enumeration en =  req.getParameterNames();

        String realPath = getServletContext().getRealPath(saveDirectory);
        System.out.println("realPath=" + realPath); // 測試用
        out.println("saveDirectory=" + saveDirectory); // 測試用

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}

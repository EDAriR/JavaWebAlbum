package utility;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

@WebServlet("/utility.upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
// ��ƾڶq�j��fileSizeThreshold�ȮɡA���e�N�Q�g�J�Ϻ�
// �W�ǹL�{���L�׬O��Ӥ��W�LmaxFileSize�ȡA�Ϊ̤W�Ǫ��`�q�j��maxRequestSize �ȳ��|�ߥXIllegalStateException ���`
public class upload extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // �W���ɮת��ئa�ؿ�;
    // �N�ѩ��U����26~30��� java.io.File �� ContextPath ���U, �۰ʫإߥئa�ؿ�

    String[] names;
    ArrayList<String> filenames = new ArrayList<String>();


    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        PrintWriter out = res.getWriter();

        String[] ch = req.getParameterValues("ch");
        String saveDirectory = "/images_uploaded";
        String realPath = getServletContext().getRealPath(saveDirectory);
        File fsaveDirectory = new File(realPath);

        out.println("<!DOCTYPE html>\n" + "<html lang=\"en\">" + "<body>" + "<head>" + "<link rel=\"stylesheet\" href=\"css.css\">");
        out.println("</head>\n" + "<body>");
        out.println("<pre>");

        for (int i = 0; i < ch.length; i++) {

            File ff = new File(fsaveDirectory, ch[i]);
            out.println("ch: " + ch[i] + "<br>");

            ff.delete();
            out.println("ch: " + ch[i] + "delete" + "<br>");

        }


        for (String s : filenames) {
            out.println("filenames: " + s + "<br>");

        }

        out.println("</pre>");
        out.println("</body>" + "</html>");
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("Big5"); // �B�z�����ɦW
        res.setContentType("text/html; charset=Big5");
        int i = 0;

        PrintWriter out = res.getWriter();

        String saveDirectory = "/images_uploaded";
        String realPath = getServletContext().getRealPath(saveDirectory);

        System.out.println("ContentType=" + req.getContentType()); // ���ե�
        System.out.println("realPath=" + realPath); // ���ե�

        File fsaveDirectory = new File(realPath);
        if (!fsaveDirectory.exists())
            fsaveDirectory.mkdirs(); // �� ContextPath ���U,�۰ʫإߥئa�ؿ�

        Collection<Part> parts = req.getParts(); // Servlet3.0�s�W�FPart�����A���ڭ̤�K���i���ɮפW�ǳB�z
        ArrayList<File> files = new ArrayList<File>();

        out.write("<h2> Total parts : " + parts.size() + "</h2>");
        out.write("<FORM action=\"upload\" method=get enctype=\"multipart/form-data\">");

        for (Part part : parts) {

            if (getFileNameFromPart(part) != null && part.getContentType() != null) {

//out.println("<form action=\"upload\" method=\"get\" enctype=\"multipart/form-data\">");
                out.println("<PRE>");
                i++;
                String name = part.getName();
                //names[i]= name;

                String filename = getFileNameFromPart(part);

                String ContentType = part.getContentType();
                long size = part.getSize();

                File f = new File(fsaveDirectory, filename);
                filenames.add(f.toString());

                out.println("name: " + name);
                //out.println("names: "  +names[i]);
                out.println("filenames: " + filename);
                out.println("ContentType: " + ContentType);
                out.println("size: " + size);
                out.println("File: " + f);

                // �Q��File����,�g�J�ئa�ؿ�,�W�Ǧ��\
                part.write(f.toString());

                // �B�~���� InputStream �P byte[] (���N��model��VO�w�@�ǳ�)
                InputStream in = part.getInputStream();
                byte[] buf = new byte[in.available()];
                in.read(buf);
                in.close();
                out.println("buffer length: " + buf.length);

                // �B�~���ըq��
                out.println("<br><img src=\"" + req.getContextPath() + saveDirectory + "/" + filename + "\">");

                out.println("<input type=\"checkbox\" name=\"ch\" value = \"" + filename + "\">");
                // out.println(" <input type=\"submit\" name=\"" + i + "\" value=\"delete\">");
                out.println("</PRE>");
                //out.println("</form >");

            }
        }
        out.println("<input type=\"submit\">");
        out.println("</FORM>");
    }

    // ���X�W�Ǫ��ɮצW�� (�]��API������method,�ҥH�����ۦ漶�g)
    public String getFileNameFromPart(Part part) {
        String header = part.getHeader("content-disposition");
        System.out.println("header=" + header); // ���ե�
        String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
        System.out.println("filename=" + filename); // ���ե�
        if (filename.length() == 0) {
            return null;
        }
        return filename;
    }
}



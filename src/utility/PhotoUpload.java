package utility;
import com.diary.model.PhotoService;
import com.diary.model.PhotoVO;

import javax.servlet.RequestDispatcher;
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
import java.util.ArrayList;
import java.util.Collection;

@WebServlet("/PhotoUpload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 500 * 1024 * 1024, maxRequestSize = 5 * 500 * 1024 * 1024)
// ��ƾڶq�j��fileSizeThreshold�ȮɡA���e�N�Q�g�J�Ϻ�
// �W�ǹL�{���L�׬O��Ӥ��W�LmaxFileSize�ȡA�Ϊ̤W�Ǫ��`�q�j��maxRequestSize �ȳ��|�ߥXIllegalStateException ���`
public class PhotoUpload extends HttpServlet {

    ArrayList<String> filenames = new ArrayList<String>();

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8"); // �B�z�����ɦW

        Collection<Part> parts = req.getParts(); // Servlet3.0�s�W�FPart�����A���ڭ̤�K���i���ɮפW�ǳB�z

        System.out.println("Total parts : " + parts.size());

        for (Part part : parts) {

            if (getFileNameFromPart(part) != null && part.getContentType() != null) {

                String name = part.getName();
//              �A
//              String filename =  new String(getFileNameFromPart(part).getBytes("ISO-8859-1"),"UTF-8");
                String filename =  getFileNameFromPart(part);
                String ContentType = part.getContentType();
                
             // �B�~���� InputStream �P byte[] (���N��model��VO�w�@�ǳ�)
                InputStream in = part.getInputStream();
                byte[] buf = new byte[in.available()];
                in.read(buf);
                in.close();
 
                PhotoService photosvc = new PhotoService();
                photosvc.addPhoto("BABY0007",filename, filename,buf,"0");
                System.out.println("insert OKxxxxxxxxxxxxxxxxxxxxxXX!!!");
                
            }
        }        
    }

    // ���X�W�Ǫ��ɮצW�� (�]��API������method,�ҥH�����ۦ漶�g)
    public String getFileNameFromPart(Part part) {
    	
        String header = part.getHeader("content-disposition");
        String filename = new File(header.substring(header.lastIndexOf("=") + 2
                , header.length() - 1)).getName();

        if (filename.length() == 0) { return null; }
        return filename;
    }
}



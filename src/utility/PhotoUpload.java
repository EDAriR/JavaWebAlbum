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
// 當數據量大於fileSizeThreshold值時，內容將被寫入磁碟
// 上傳過程中無論是單個文件超過maxFileSize值，或者上傳的總量大於maxRequestSize 值都會拋出IllegalStateException 異常
public class PhotoUpload extends HttpServlet {

    ArrayList<String> filenames = new ArrayList<String>();

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8"); // 處理中文檔名

        Collection<Part> parts = req.getParts(); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理

        System.out.println("Total parts : " + parts.size());

        for (Part part : parts) {

            if (getFileNameFromPart(part) != null && part.getContentType() != null) {

                String name = part.getName();
//              再
//              String filename =  new String(getFileNameFromPart(part).getBytes("ISO-8859-1"),"UTF-8");
                String filename =  getFileNameFromPart(part);
                String ContentType = part.getContentType();
                
             // 額外測試 InputStream 與 byte[] (幫將來model的VO預作準備)
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

    // 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
    public String getFileNameFromPart(Part part) {
    	
        String header = part.getHeader("content-disposition");
        String filename = new File(header.substring(header.lastIndexOf("=") + 2
                , header.length() - 1)).getName();

        if (filename.length() == 0) { return null; }
        return filename;
    }
}



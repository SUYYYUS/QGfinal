package com.suyyyus.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;
import java.util.List;

@WebServlet("/uploadvideo/*")
public class UploadvideoServlet extends BaseServlet{

    /**
     * 教师上传视频
     * @param req
     * @param resp
     * @throws IOException
     * @throws ServletException
     */
    public void uploadvideo(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (ServletFileUpload.isMultipartContent(req)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                List<FileItem> items = upload.parseRequest(req);
                for (FileItem item : items) {
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        File uploadedFile = new File("/path/to/upload/directory", fileName);
                        // 如果目录不存在，先创建目录
                        uploadedFile.getParentFile().mkdirs();
                        item.write(uploadedFile);
                    }
                }
                resp.getWriter().println("File uploaded successfully.");
            } catch (Exception e) {
                resp.getWriter().println("File upload failed: " + e.getMessage());
            }
        } else {
            resp.getWriter().println("No file uploaded.");
        }
    }

    /**
     * 学生接收视频并观看
     * @param req
     * @param resp
     * @throws IOException
     */
    public void getvideo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String parameter = req.getParameter("videofilename");
        String videourl = "uploaded_videos/" + parameter;
        resp.getWriter().write(videourl);
    }


}

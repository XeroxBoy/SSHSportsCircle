package com.cdut.sx.controller;


import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
public class UploadController {
    private File upload;
    private String uploadFileName;

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    /*
     * 上传头像函数
     * */
    @RequestMapping("/upload")
    public String upload1(HttpSession session, HttpServletRequest request) {
        if (uploadFileName != null) {
            String username = (String) session.getAttribute("name");//把头像文件命名为username
            if (username == null || username == "")
                return "fail";
            String path = request.getServletContext().getRealPath("/WEB-INF/upload");
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            File destFile = new File(path, username + ".jpg");
            try {
                FileUtils.copyFile(upload, destFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "views/fitcircle";
        }
        return "views/error";
    }
}


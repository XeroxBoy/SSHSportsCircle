package com.cdut.sx.controller;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
@Controller

public class uploadController {
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

    public String upload1() {
        if (uploadFileName != null) {
            Map<String,Object> session=ActionContext.getContext().getSession();//从session中取出用户名 用来命名头像
            String username=(String) session.get("name");//把头像文件命名为username
            System.out.print(username);
            if(username==null || username=="")
                return "fail";
            String path = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload");
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            System.out.println(path);
            File destFile = new File(path, username + ".jpg");
            try {
                FileUtils.copyFile(upload, destFile);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return "success";
        }
        return "fail";
    }
}


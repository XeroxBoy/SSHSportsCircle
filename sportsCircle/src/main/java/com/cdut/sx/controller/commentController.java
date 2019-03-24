package com.cdut.sx.controller;

import com.cdut.sx.dao.Commentsdao;
import com.cdut.sx.dao.impl.messagedaoImp;
import com.cdut.sx.pojo.comments;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;
import com.cdut.sx.dao.messagedao;
import com.cdut.sx.dao.impl.commentsdaoImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class commentController {
    @Autowired
    private comments comment;
    private int currPage;
    @Autowired
    private Commentsdao commentsdaoImp;
    @Autowired
    private messagedao messagedao;


    @Override
    public comments getModel() {
        // TODO 自动生成的方法存根
        if (comment == null) {
            comment = new comments();
        }
        return comment;
    }
    public int getCurrPage() {
        return currPage;
    }
    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }
    public String save()
    {
        comment.setActive("active");
        comment.setOutTime(""+new Date().getDate());
        String content="";
        String userId="";


        try {
            content=new String(comment.getContents().getBytes("iso-8859-1"),"utf-8");
            userId=new String(comment.getUserId().getBytes("iso-8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//转码
        comment.setContents(content);
        comment.setUserId(userId);
        if(comment!=null)
            commentsdaoImp.save(comment);
     /*	Userdao userdao=new UserdaoImp();
        message messagebelongTo=messagedao.queryById(comment.getMessagebelongTo()).get(0);//获取所属的状态
        String userEmail=userdao.queryByName(messagebelongTo.getUserId()).get(0).getEmail();//获取楼主的email
        try {
			Sendmail.pushMessage(userEmail,"您的帖子有回复了 内容如下: \n"+comment.getContents());
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        return "success";
    }

    public String delete() {
        HttpServletRequest request = ServletActionContext.getRequest();
        int commentid = Integer.parseInt(request.getParameter("commentId"));
        List<comments> comment = commentsdaoImp.queryById(commentid);
        if (comment != null && !comment.isEmpty()) {
            comment.get(0).setActive("dead");
        }
        commentsdaoImp.save(comment.get(0));
        return "success";
    }
}


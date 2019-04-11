package com.cdut.sx.controller;

import com.cdut.sx.pojo.Comments;
import com.cdut.sx.service.CommentsService;
import com.cdut.sx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 *
 */
@Controller
public class CommentController {

    private int currPage;
    private static final String ERR = "views/error";
    private static final String ZHUYE = "redirect:/findArea";
    @Autowired
    private CommentsService commentsdaoImp;
    @Autowired
    private UserService userdao;

    public int getCurrPage() {
        return currPage;
    }
    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    @RequestMapping("/comment")
    public ModelAndView save(@ModelAttribute Comments comment) {
        if (comment == null) return new ModelAndView(ERR);
        comment.setActive("active");
        comment.setOutTime("" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        String userId = "";
        try {
            userId = new String(comment.getUserId().getBytes("iso-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }//转码
        comment.setUserId(userId);
        comment.setActive("active");
        commentsdaoImp.save(comment);
        return new ModelAndView(ZHUYE);
    }

    @RequestMapping("/commentDelete")
    public ModelAndView delete(HttpServletRequest request) {
        int commentid = Integer.parseInt(request.getParameter("commentId"));
        List<Comments> comment = commentsdaoImp.queryById(commentid);
        if (comment == null || comment.isEmpty()) return new ModelAndView("/error");
        comment.get(0).setActive("dead");
        commentsdaoImp.save(comment.get(0));
        return new ModelAndView(ZHUYE);
    }
}


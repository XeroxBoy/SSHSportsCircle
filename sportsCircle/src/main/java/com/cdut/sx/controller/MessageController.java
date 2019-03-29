package com.cdut.sx.controller;

import com.cdut.sx.pojo.Message;
import com.cdut.sx.pojo.PageBean;
import com.cdut.sx.service.MessageService;
import com.cdut.sx.service.RemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 *
 */
@Controller
public class MessageController {
    private static final String PAGE = "PageBean";
    private static final String ZHUYE = "views/fitcircle";
    @Autowired
    RemindService reminddao;
    private Integer currPage = 1;
    @Autowired
    private MessageService dao;
    private Message message;

    @RequestMapping("/message")
    public ModelAndView message(HttpSession session, @ModelAttribute Message message) {
        this.message = message; //验证状态是否合理
        if (message == null || session.getAttribute("name") == null)//未登录
            return new ModelAndView("views/error");
        message.setActive("active");

        message.setUserId((String) session.getAttribute("name"));//给message所属人属性赋值
        dao.save(message);
        return new ModelAndView(ZHUYE);
    }

    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    @RequestMapping("/findAll")
    public ModelAndView findAll() {
        PageBean<Message> pageBean = dao.findByPage(currPage);
        ModelAndView mav = new ModelAndView(ZHUYE);
        mav.addObject(PAGE, pageBean);
        return mav;
    }

    @RequestMapping("/solve")
    public String solve(HttpServletRequest request) { //将message状态置为解决
        String messageId = request.getParameter("messageId");
        message = dao.queryById(Integer.valueOf(messageId));
        message.setActive("dead");
        return ZHUYE;
    }

    /**
     * 查找不同领域（打球圈,健身圈，跑步圈，……）
     *
     * @return
     */
    @RequestMapping("/findArea")
    public ModelAndView findArea(HttpServletRequest request, HttpSession session) {
        ModelAndView mav = new ModelAndView(ZHUYE);
        String area = request.getParameter("area");//如果是点链接跳过来的话
        if (area == null)//如果是通过登录等方式跳过来的话
            area = (String) session.getAttribute("area");
        else {//如果是点链接跳过来的话
            try {
                area = new String(URLDecoder.decode(area, "utf-8"));//转码
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (currPage == 0)
            currPage = 1;
        session.setAttribute("area", area);//重新赋值 分页查询才会正确显示其他圈子的状态
        PageBean<Message> pageBean = dao.findByArea(currPage, area);
        mav.addObject(PAGE, pageBean);
        return mav;
    }

    /**
     * 查找我的发出的messages
     *
     * @return
     */
    @RequestMapping("/findMine")
    public ModelAndView findMine(HttpSession session) {
        ModelAndView mav = new ModelAndView(ZHUYE);
        String userId;
        userId = String.valueOf(session.getAttribute("id")); // Servlet 中获取 Session 对象
        PageBean<Message> pageBean = dao.findMineByPage(currPage, userId);
        mav.addObject(PAGE, pageBean);
        return mav;
    }

    @RequestMapping("/deleteMessage")
    public ModelAndView delete(HttpServletRequest request) {
        int messageid = Integer.parseInt(request.getParameter("messageid"));
        Message messages = dao.queryById(messageid);
        if (messages != null) {
            messages.setActive("dead");
        }
        dao.save(messages);
        return new ModelAndView(ZHUYE);
    }
}


package com.cdut.sx.controller;

import com.cdut.sx.dao.Circledao;
import com.cdut.sx.dao.Userdao;
import com.cdut.sx.pojo.Circle;
import com.cdut.sx.pojo.Message;
import com.cdut.sx.pojo.PageBean;
import com.cdut.sx.pojo.User;
import com.cdut.sx.service.MessageService;
import com.cdut.sx.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 *
 */
@Controller
public class MessageController {
    private static final String PAGE = "PageBean";
    private static final String ZHUYE = "views/fitcircle";
    private static boolean isChanged = false;
    @Autowired
    private RedisService redisService;

    private Integer currPage = 1;
    @Autowired
    private MessageService dao;
    @Autowired
    private Userdao userdao;
    @Autowired
    private Circledao circledao;

    //改变ischanged状态
    public void change() {
        isChanged = true;
    }

    /*
     * 保存帖子
     * */
    @RequestMapping("/message")
    public ModelAndView message(HttpSession session, @ModelAttribute Message message) {
        if (message == null || session.getAttribute("name") == null)//未登录
            return new ModelAndView("views/error");
        message.setActive("active");
        Circle messageBelongCircle = circledao.findByCircleName(message.getBelongTo().getCircleName()).get(0);
        messageBelongCircle.setMessageCount(messageBelongCircle.getMessageCount() + 1);
        circledao.save(messageBelongCircle);
        String name = (String) session.getAttribute("name");
        message.setUserId(userdao.queryByName(name).get(0));//给message所属人属性赋值
        dao.save(message);
        this.change();
        return new ModelAndView("forward:/findArea");
    }

    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    /*
     * 找到所有信息
     * */
    @RequestMapping("/findAll")
    public ModelAndView findAll() {
        PageBean<Message> pageBean = dao.findByPage(currPage);
        ModelAndView mav = new ModelAndView(ZHUYE);
        mav.addObject(PAGE, pageBean);
        return mav;
    }

    /*
     * 去发状态的页面
     * */
    @RequestMapping("/date")
    public ModelAndView date(HttpSession session) {
        ModelAndView mav = new ModelAndView("views/date");
        String username = (String) session.getAttribute("name");
        List<User> users = userdao.queryByName(username);
        User user = users.get(0);
        Set<Circle> userCircles = user.getCircles();
        mav.addObject("userCircles", userCircles);
        return mav;
    }

    @RequestMapping("/solve")
    public String solve(HttpServletRequest request) { //将message状态置为解决
        String messageId = request.getParameter("messageId");
        Message message = dao.queryById(Integer.parseInt(messageId));
        message.setActive("dead");
        return ZHUYE;
    }

    /**
     * 查找不同领域（打球圈,健身圈，跑步圈，……）
     */
    @RequestMapping("/findArea")
    public ModelAndView findArea(HttpServletRequest request, HttpSession session, @RequestParam(name = "currPage", required = false) Integer Currpage) {
        ModelAndView mav = new ModelAndView(ZHUYE);
        String area = request.getParameter("area");//如果是点链接跳过来的话

        if (area == null) {//如果是通过登录等方式跳过来的话
            area = (String) session.getAttribute("area");
        } else {//如果是点链接跳过来的话
            try {
                area = URLDecoder.decode(area, "utf-8");//转码
            } catch (UnsupportedEncodingException e) {
                Logger.getLogger("abc").info(e.getMessage());
            }
        }
        if (Currpage != null) currPage = Currpage;
        if (currPage == 0)
            currPage = 1;
        session.setAttribute("area", area);//重新赋值 分页查询才会正确显示其他圈子的状态
        String currpage = String.valueOf(currPage);
        PageBean<Message> pageBean = dao.findByArea(currPage, area);
        if (pageBean != null){
            mav.addObject(PAGE, pageBean); }
        return mav;
    }

    /**
     * 查找我的发出的messages
     */
    @RequestMapping("/findMine")
    public ModelAndView findMine(HttpSession session) {
        ModelAndView mav = new ModelAndView(ZHUYE);
        String userId;
        int pagesize = 5;

        userId = String.valueOf(session.getAttribute("id")); // Servlet 中获取 Session 对象
        PageBean<Message> pageBean = dao.findByPage(currPage, userId, String.valueOf(session.getAttribute("area")));
        mav.addObject(PAGE, pageBean);
        return mav;
    }

    @RequestMapping("/deleteMessage")
    public ModelAndView delete(HttpServletRequest request, HttpSession session) {
        int messageid = Integer.parseInt(request.getParameter("messageid"));
        Message messages = dao.queryById(messageid);
        if (messages != null) {
            messages.setActive("dead");
        }
        dao.save(messages);
        return new ModelAndView("forward:/findArea");
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /*
     * TODO
     * 推荐算法实现
     * */
    public void Recommend() {

    }


}


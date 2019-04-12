package com.cdut.sx.controller;

import com.cdut.sx.pojo.Circle;
import com.cdut.sx.pojo.Message;
import com.cdut.sx.pojo.User;
import com.cdut.sx.service.CircleService;
import com.cdut.sx.service.UserService;
import com.cdut.sx.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ZhuceController {
    @Autowired
    UserService userdao;

    @Autowired
    private User User;

    @Autowired
    private CircleService circledao;
    @RequestMapping("/zhuce")
    public ModelAndView zhuce(@Validated @ModelAttribute("user") User User) {
        ModelAndView mav = new ModelAndView("views/Login");
        ModelAndView errormav = new ModelAndView("views/error");
        String userArea = User.getAreabelongto();

        if (userdao.queryByName(User.getUsername()).isEmpty()) {
            if (circledao.findCircle(userArea).isEmpty() || circledao.findCircle(userArea) == null)
                return new ModelAndView("forward:/toCircle");
            Circle userCircle = circledao.findCircle(userArea).get(0);
            userCircle.setUserCount(userCircle.getUserCount() + 1);
            User.setLastProday(new Date("2000/01/01"));//防止出现不符合现实的打卡结果
            User.setPassword(MD5.encodeMd5(User.getPassword()));
            userdao.save(User);
            return mav;
        }
        return errormav;
    }


    /**
     * 找到当前用户的信息
     *
     *
     */
    @RequestMapping("/findMyInfo")
    public ModelAndView findMyInfo(HttpSession session) {
        ModelAndView mav = new ModelAndView("views/MyInfo");
        ModelAndView errormav = new ModelAndView("views/error");
        String userName = (String) session.getAttribute("name");
        if (userdao.queryByName(userName).size() == 1) {
            User user = userdao.queryByName(userName).get(0);
            this.User = user;
            mav.addObject("user", User);
            return mav;
        } else {
            return errormav;// 暂不处理
        }

    }

    /**
     * 用于修改用户信息
     */
    @RequestMapping("/modify")
    public ModelAndView modify(HttpSession session) {
        ModelAndView mav = new ModelAndView("views/MyInfo");
        int userId = (Integer) session.getAttribute("id");
        List<Message> message = (ArrayList<Message>) session.getAttribute("messages");
        User.setUserId(userId);
        User.setMessages(message);//不然user的message会被清空
        userdao.save(User);
        mav.addObject("user", User);
        return mav;
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    @RequestMapping("/toZhuce")
    public ModelAndView toZhuce(){
        return new ModelAndView("views/zhuce");
    }
}

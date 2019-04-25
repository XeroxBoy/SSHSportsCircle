package com.cdut.sx.controller;

import com.cdut.sx.pojo.Circle;
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

import java.text.SimpleDateFormat;
import java.util.Date;

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
                return new ModelAndView("forward:/createCircle");
            Circle userCircle = circledao.findCircle(userArea).get(0);
            userCircle.setUserCount(userCircle.getUserCount() + 1);
            User.setLastProday(new Date("2000/01/01"));//防止出现不符合现实的打卡结果
            User.setPassword(MD5.encodeMd5(User.getPassword()));
            userdao.save(User);
            return mav;
        }
        return errormav;
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

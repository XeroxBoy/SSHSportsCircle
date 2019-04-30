package com.cdut.sx.controller;

import com.cdut.sx.pojo.Circle;
import com.cdut.sx.pojo.User;
import com.cdut.sx.service.CircleService;
import com.cdut.sx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/*
 * 圈子类的实现
 * */
@Controller
public class CircleController {
    @Autowired
    CircleService circledao;
    @Autowired
    UserService userdao;

    public final String CIRCLE_PAGE = "views/circle";
    public final String TO_CIRCLE = "forward:/toCircle";

    //去创建圈子的页面
    @RequestMapping("/toCircle")
    public ModelAndView toCircle() {
        ModelAndView mav = new ModelAndView(CIRCLE_PAGE);
        mav.addObject("circles", circledao.findAllCircles());
        return mav;
    }

    @RequestMapping("/createCircle")
    public ModelAndView createCircle() {
        return new ModelAndView("views/createCircle");
    }

    //去创建圈子的页面
    @RequestMapping("/circle")
    public ModelAndView circle(@ModelAttribute Circle circle, HttpSession session) {
        circledao.save(circle);
        if (session.getAttribute("name") == null) {
            return new ModelAndView("views/zhuce");
        } else {
            return new ModelAndView(TO_CIRCLE);
        }
    }

    @RequestMapping("/searchCircle")
    public ModelAndView search(@RequestParam("key") String key) {
        ModelAndView mav = new ModelAndView(CIRCLE_PAGE);
        mav.addObject("circles", circledao.search(key));
        return mav;
    }

    //删除圈子
    @RequestMapping("/deleteCircle")
    public ModelAndView deleteCircle(@RequestParam("circle_name") String name) {
        circledao.delete(circledao.findCircle(name).get(0));
        return new ModelAndView(TO_CIRCLE);
    }

    //关注圈子
    @RequestMapping("/follow")
    public ModelAndView followCircle(HttpSession session, @RequestParam("circleName") String circleName) {
        String name = (String) session.getAttribute("name");
        List<User> users = userdao.queryByName(name);
        Circle circle = circledao.findCircle(circleName).get(0);
        if (!users.get(0).getCircles().contains(circle)) {
            circle.getCircleUsers().add(users.get(0));
            users.get(0).getCircles().add(circle);
            circle.setUserCount(circle.getUserCount() + 1);
            circledao.save(circle);
            userdao.save(users.get(0));
        }
        session.setAttribute("myCircles", users.get(0).getCircles());
        return new ModelAndView(TO_CIRCLE);
    }
}


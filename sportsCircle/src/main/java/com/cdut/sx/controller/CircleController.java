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

@Controller
public class CircleController {
    @Autowired
    CircleService circledao;
    public final String CIRCLE_PAGE = "views/circle";

    @RequestMapping("/toCircle")
    public ModelAndView toCircle() {
        return new ModelAndView(CIRCLE_PAGE);
    }
    @RequestMapping("/circle")
    public ModelAndView circle(@ModelAttribute Circle circle) {
        circledao.save(circle);
        return new ModelAndView(CIRCLE_PAGE);
    }
    @RequestMapping("/searchCircle")
    public ModelAndView search(@RequestParam("key")String key){
        ModelAndView mav = new ModelAndView(CIRCLE_PAGE);
        mav.addObject("circles", circledao.search(key));
        return mav;
    }

    @RequestMapping("/deleteCircle")
    public ModelAndView deleteCircle(@RequestParam("circle_name") String name) {
        circledao.delete(circledao.findCircle(name));
        ModelAndView mav = new ModelAndView(CIRCLE_PAGE);
        return mav;
    }

    @RequestMapping("/follow")
    public ModelAndView followCircle(HttpSession session, @RequestParam("circleName") String circleName) {
        String name = (String) session.getAttribute("name");
        UserService userdao = new UserService();
        List<User> users = userdao.queryByName(name);
        Circle circle = circledao.findCircle(circleName);
        circle.getCircleUsers().add(users.get(0));
        circle.
                circle.setUserCount(circle.getUserCount() + 1);
        return new ModelAndView(CIRCLE_PAGE);
    }
}


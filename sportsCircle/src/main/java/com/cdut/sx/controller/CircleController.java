package com.cdut.sx.controller;

import com.cdut.sx.pojo.Circle;
import com.cdut.sx.service.CircleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CircleController {
    @Autowired
    CircleService circledao;

    @RequestMapping("/circle")
    public ModelAndView circle(@ModelAttribute Circle circle) {
        circledao.save(circle);
        return new ModelAndView("views/circle");
    }
    @RequestMapping("/searchCircle")
    public ModelAndView search(@RequestParam("key")String key){

        return new ModelAndView("views/circle");
    }
}


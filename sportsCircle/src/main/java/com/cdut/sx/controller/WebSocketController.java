package com.cdut.sx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebSocketController {
    @RequestMapping("/toContact")
    public ModelAndView contact() {
        return new ModelAndView("views/contact");
    }
}


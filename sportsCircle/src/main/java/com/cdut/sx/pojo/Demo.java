package com.cdut.sx.pojo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class Demo {
@RequestMapping("/show")
    public String show(){
    return "tony🐎";
}

}


package com.cdut.sx.controller;

import com.cdut.sx.pojo.User;
import com.cdut.sx.service.RedisService;
import com.cdut.sx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

@RestController
public class DakaController {
    @Autowired
    private UserService dao;
    @Autowired
    private RedisService redisService;
    private static Date getBeforeDate(Date date) { //获取当前天数的前一天
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date date1;
        date1 = new Date(calendar.getTimeInMillis());
        return date1;
    }

    @RequestMapping("/toDaka")
    public ModelAndView toDaka(){
        return new ModelAndView("views/daka");
    }

    @RequestMapping("/daka")
    public ModelAndView daka(HttpSession session, HttpServletRequest request, HttpServletResponse resp, @ModelAttribute String message) { //写判断函数 判断打卡天数
        String username = (String) session.getAttribute("name");
        redisService.set("dkMsg", message);
        ModelAndView mav=new ModelAndView("views/daka");
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        resp.setContentType("text/html;charset=utf-8");//不然打回来乱码
        User user = dao.queryByName(username).get(0);
        if (user.getLastProday().getDate() == getBeforeDate(new Date()).getDate()) {   //上次打卡日期为昨天
            int prodays = user.getProdays();//当前连续打卡天数
            user.setProdays(prodays + 1);//总打卡天数+1
            if (user.getProdays() > user.getMaxProdays()) {//已打卡天数大于打卡最大天数记录,则更新打卡最大天数
                user.setMaxProdays(user.getProdays());
                session.setAttribute("maxProdays", user.getMaxProdays());
            }
            user.setLastProday(new Date());
            int exExp = (int) (Math.pow(prodays, 1 / 2) + 2);//本次打卡增加的经验值
            user.setExp(user.getExp() + exExp);
        } else if (user.getLastProday().getDate() != new Date().getDate()) {//今天没打卡 且上次打卡日期不为昨天
            user.setProdays(1);//因为没有连续打卡 设为1
            if (user.getProdays() > user.getMaxProdays()) {//已打卡天数大于打卡最大天数记录,则更新打卡最大天数
                user.setMaxProdays(user.getProdays());
                session.setAttribute("maxProdays", user.getMaxProdays());
            }
        }
        user.setLastProday(new Date());//将今天设为last打卡day
        dao.save(user);
        session.setAttribute("lastProdays", user.getLastProday().getDate());//在session中更新上次打卡时间与连续打卡时间
        session.setAttribute("prodays", user.getProdays());
        return mav;
    }

}


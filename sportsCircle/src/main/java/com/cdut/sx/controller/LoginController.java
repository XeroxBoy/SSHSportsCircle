package com.cdut.sx.controller;

import com.cdut.sx.pojo.Message;
import com.cdut.sx.pojo.User;
import com.cdut.sx.service.RedisService;
import com.cdut.sx.service.UserService;
import com.cdut.sx.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.*;

@Controller
public class LoginController {
    @Autowired
    UserService userdao;
    @Autowired
    private User User;
    @Autowired
    private RedisService redisService;
    public static Date getNextDay(Date date) {//获取今天的前一天
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }
    @RequestMapping("/")
    public String start(){
        return "views/Login";
    }

    static int n = 0;


    public void setCookie(HttpServletResponse response) {
        Cookie cookie1 = new Cookie("zh", User.getUsername());

        Cookie cookie2 = new Cookie("mm", User.getPassword());

        cookie1.setMaxAge(60 * 60 * 24);//保存一天
        cookie2.setMaxAge(60 * 60 * 24);
        cookie1.setPath("/");//保存路径，不设这个保存不好
        cookie2.setPath("/");
        response.addCookie(cookie1);
        response.addCookie(cookie2);
    }

    @RequestMapping("/logout")
    public ModelAndView logout() {
        return new ModelAndView("views/Login");
    }

    @RequestMapping("/toError")
    public ModelAndView error() {
        return new ModelAndView("views/error");
    }



    /**
     * 登录
     */
    @SuppressWarnings("deprecation")
    @RequestMapping("/login")
    public ModelAndView user(@Validated @ModelAttribute User User, HttpSession session,
                             HttpServletRequest request, HttpServletResponse response) {
        this.User = User; //验证方法 进行数据库操作
        String remember = request.getParameter("remember");//是否勾选记住账号密码
        String code = request.getParameter("code");//获取用户输入的验证码
        List<User> user = userdao.queryByName(User.getUsername());//获取用户信息
        String reqPass = MD5.encodeMd5(User.getPassword());
        ModelAndView mav = new ModelAndView("forward:/findArea");
        ModelAndView errormav = new ModelAndView("views/Login");
        if (!user.isEmpty()) {
            User user1 = user.get(0);//获取数据库中的对象
            if (user1 == null || !user1.getPassword().equals(reqPass))//没查到用户信息,或者密码不匹配
                return errormav; //查询失败 去失败页面
            if (!code.equals(String.valueOf(redisService.get("randstr")))) {//验证码数错了 不能用== 不然比较的是引用
                return errormav;
            }
            if (remember != null)
                this.setCookie(response);//把用户信息保存到COOKIE中
            int date = user1.getLastProday().getDate();//获取上次打卡的日期
            Date beforeday = getNextDay(new Date());//获取昨天

            if (user1.getLastProday().getDate() != beforeday.getDate() && user1.getLastProday().getDate() != new Date().getDate()) {//如果昨天今天都没打卡
                user1.setProdays(0);//连续打卡天数设为0
                userdao.save(user1);
            }

            if (user1.getProdays() != 0) {//连续打卡了
                if (!user1.getLastProday().equals(beforeday) || user1.getLastProday().getMonth() == new Date().getMonth()) {

                } else {
                    date = date - user1.getProdays();//处理打卡日期月底和月初连续的情况 适应js中的日历函数 比如说31，1，2打了三次卡
                    //那他这个月应该画勾的日期会从 2-3=-1算起 打3个勾 最终效果就是1，2号打勾

                }
            }
            session.setAttribute("myCircles", user1.getCircles());
            session.setAttribute("id", user1.getUserId());//把用户信息存到session 以供其他页面使用
            session.setAttribute("name", user1.getUsername());
            session.setAttribute("email", user1.getEmail());
            session.setAttribute("birthday", user1.getBirthday());
            session.setAttribute("lastProdays", date);
            session.setAttribute("maxProdays", user1.getMaxProdays());
            session.setAttribute("prodays", user1.getProdays());
            session.setAttribute("sex", user1.getSex());
            session.setAttribute("area", user1.getAreabelongto());
            return mav;
        } else
            return errormav;
    }

    /**
     * 找到当前用户的信息
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

    /*
     * 验证码
     * */
    @ResponseBody
    @RequestMapping("/randstr")
    public void rangstr(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache");
        int width = 60, height = 20;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //获取画笔
        Graphics g = image.getGraphics();
        //设定背景色
        g.setColor(new Color(100, 100, 100));
        g.fillRect(0, 0, width, height);
        //取随机产生的验证码
        Random rnd = new Random();
        int randNum = rnd.nextInt(8999) + 1000;
        String randStr = String.valueOf(randNum);
        //将验证码存入session
//        session.setAttribute("randstr", randStr);
        redisService.set("randstr", randStr);
        //将验证码显示在图像中
        g.setColor(Color.black);
        g.setFont(new Font("", Font.PLAIN, 20));
        g.drawString(randStr, 10, 17); //10，17为画笔的高度，宽度
        for (int i = 0; i < 50; i++) {
            int x = rnd.nextInt(width);
            int y = rnd.nextInt(height);
            g.drawOval(x, y, 1, 1);
        }
        try {
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


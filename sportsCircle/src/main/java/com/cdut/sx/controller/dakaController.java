package com.cdut.sx.controller;

import com.cdut.sx.dao.Userdao;
import com.cdut.sx.dao.impl.UserdaoImp;
import com.cdut.sx.pojo.user;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Calendar;
import java.util.Map;

public class dakaController {
    public static Date getBeforeDate(Date date) { //获取当前天数的前一天
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DAY_OF_MONTH, -1);
        Date date1 = new Date(calendar.getTimeInMillis());
        return date1;
    }

    public String daka(){ //写判断函数 判断打卡天数
        Map<String, Object> session=ActionContext.getContext().getSession();
        String username=(String) session.get("name");
        HttpServletRequest request=ServletActionContext.getRequest();
        HttpServletResponse resp=ServletActionContext.getResponse();
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        resp.setContentType("text/html;charset=utf-8");//不然打回来乱码
        Userdao dao=new UserdaoImp();
        user user=dao.queryByName(username).get(0);
        System.out.println(getBeforeDate(new Date()).getDate());
        if(user.getLastProday().getDate()==getBeforeDate(new Date()).getDate()){   //上次打卡日期为昨天
            int prodays=user.getProdays();//当前连续打卡天数
            user.setProdays(prodays+1);//总打卡天数+1
            if(user.getProdays()>user.getMaxProdays()){//已打卡天数大于打卡最大天数记录,则更新打卡最大天数
                user.setMaxProdays(user.getProdays());
                session.put("maxProdays", user.getMaxProdays());
            }
            user.setLastProday(new Date());
            int exExp=(int) (Math.pow(prodays, 1/2)+2);//本次打卡增加的经验值
            user.setExp(user.getExp()+exExp);
        } else if(user.getLastProday().getDate()!=new Date().getDate())//今天没打卡 且上次打卡日期不为昨天
            user.setProdays(1);//因为没有连续打卡 设为1
        user.setLastProday(new Date());//将今天设为last打卡day
        dao.update(user);
        session.put("lastProdays", user.getLastProday().getDate());//在session中更新上次打卡时间与连续打卡时间
        session.put("prodays", user.getProdays());
        return "success";
    }

}


package com.cdut.sx.controller;

import com.cdut.sx.dao.impl.messagedaoImp;
import com.cdut.sx.dao.impl.reminddaoImp;
import com.cdut.sx.dao.messagedao;
import com.cdut.sx.dao.reminddao;
import com.cdut.sx.pojo.PageBean;
import com.cdut.sx.pojo.message;
import com.cdut.sx.pojo.remind;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class messageController {
    reminddao reminddao = new reminddaoImp();
    private message message=new message();
    private Integer currPage = 1;
    private messagedao dao=new messagedaoImp();
    public String message(){ //验证状态是否合理
        Map<String,Object> session=ActionContext.getContext().getSession();//将用户信息存入session
//  System.out.println(message);
        message.setActive("active");
        if(message==null || session.get("name")==null)
            return "fail";
        message.setUserId((String)session.get("name"));//给message赋值
        dao.save(message);
        return "success";
    }
    @Override
    public message getModel() {
        // TODO Auto-generated method stub
        if(message==null)
            return new message();
        else
            return message;
    }
    public Integer getCurrPage() {
        return currPage;
    }
    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }
    public String findAll(){
        System.out.println("进入findAll");
        PageBean<message> pageBean=dao.findByPage(currPage);
        //System.out.println("执行了findBypage,接下来遍历pageBean里面的list");
/*	for(int i=0;i<pageBean.getList().size();++i)
	{
		System.out.println(pageBean.getList().get(i));
	}*/

        //将pageBean存入到值栈中
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAll";
    }

    public String solve() { //将message状态置为解决
        HttpServletRequest request = ServletActionContext.getRequest();
        String messageId = request.getParameter("messageId");
        List<message> message = dao.queryById(Integer.valueOf(messageId));
        message.get(0).setActive("dead");
        return "makePromiseSuccess";
    }

    /**
     * 查找不同领域（打球圈,健身圈，跑步圈，……）
     * @return
     */
    public String findArea()
    {
	/*	messagedao Messagedao=new messagedaoImp();
		message message1=Messagedao.queryById(message.getMessageid()).get(0);
		message=message1;//把评论给他
*/		HttpServletRequest request = ServletActionContext.getRequest();
        Map session = ActionContext.getContext().getSession();
        //	System.out.println(message.getComments());
        String username = (String) session.get("name");
        ArrayList<remind> reminds = reminddao.queryAll(username);

        String area=request.getParameter("area");//如果是点链接跳过来的话
        if(area==null)//如果是通过登录等方式跳过来的话
            area=(String) session.get("area");
        else{//如果是点链接跳过来的话
            try {
                area = new String(URLDecoder.decode(area, "utf-8"));//转码
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }}
        System.out.println("area" + area);
        if (currPage == 0)
            currPage = 1;
        session.put("area", area);//重新赋值 分页查询才会正确显示其他圈子的状态
        //System.out.print(area);
        PageBean<message> pageBean = dao.findByArea(currPage,area);
        for (int i = 0; i < pageBean.getList().size(); i++) {
            System.out.println(pageBean.getList().get(i));
        }
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAreaSuccess";

    }
    /**
     * 查找我的发出的messages
     * @return
     */
    public String findMine() {
        //	System.out.println("进入了findMine()");
        Map session = ActionContext.getContext().getSession();
        String userId;
        userId = String.valueOf(session.get("id")); // Servlet 中获取 Session 对象
        System.out.println(userId);
        PageBean<message> pageBean = dao.findMineByPage(currPage, userId);
        for (int i = 0; i < pageBean.getList().size(); ++i) {
            System.out.println(pageBean.getList().get(i));
        }
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findMine";
    }

    public String sendPromise() {
        //	HttpServletRequest request = ServletActionContext.getRequest();
        //	request.getParameter("")
        sendMessageToHost();//给楼主发送通知
        return "sendPromiseSuccess";
    }

    private void sendMessageToHost() {
    }

    public String makePromise() {
        message.setActive("dead");
        dao.save(message);
        return "makePromiseSuccess";
    }

    public String delete() {
        HttpServletRequest request = ServletActionContext.getRequest();
        int messageid = Integer.parseInt(request.getParameter("messageid"));
        List<message> messages = dao.queryById(messageid);
        if (messages != null && !messages.isEmpty()) {
            messages.get(0).setActive("dead");
        }
        dao.save(messages.get(0));
        return "makePromiseSuccess";
    }
}


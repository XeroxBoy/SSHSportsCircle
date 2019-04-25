package com.cdut.sx.websocket;

import com.cdut.sx.pojo.User;
import com.cdut.sx.utils.GetHttpSessionConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint(value = "/websocket/{id}", configurator = GetHttpSessionConfigurator.class)
@Component
public class Websocket {
    private static int onlineCount = 0;
    private static Map<User, Websocket> clients = new HashMap<>();
    private WebsocketService websocketService = new WebsocketService();
    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        Websocket.applicationContext = applicationContext;
    }

    @OnClose
    public void onClose(Session conn) throws IOException {
        websocketService.removeUser(conn);
        subOnlineCount();
        websocketService.sendMessagetoAll("有一连接关闭！当前在线人数为" + getOnlineCount());

    }

    @OnError
    public void onError(Throwable error) throws IOException {
        System.out.println(error.getMessage());
//        websocketService.removeUser(conn);
        subOnlineCount();
//        websocketService.sendMessagetoAll("用户: 跪了" + error.getMessage());
    }


    public void onStart() {

    }

    @OnMessage
    public void onMessage(Session conn, String message, @PathParam("id") String id) throws IOException {
//        WebsocketService.addUser(username, conn);
//        String userFrom = message.split(" ")[0];
        String userTo = message.split(" ")[0];
        websocketService.sendMessage(message.split(" ")[2], userTo);
    }


    @OnOpen
    public void onOpen(Session conn, @PathParam("id") String username, EndpointConfig config) {
        HttpSession session = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        websocketService.addUser((String) session.getAttribute("name"), conn);

        System.out.println("服务器端websocket已打开" + username);
        try {
            websocketService.sendMessage("你好", "godv");
            websocketService.sendMessagetoAll("已开启");
            addOnlineCount();
            websocketService.sendMessagetoAll(username + "加入！当前在线人数为" + getOnlineCount());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        Websocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        Websocket.onlineCount--;
    }


    public static synchronized Map<User, Websocket> getClients() {
        return clients;
    }

}
	



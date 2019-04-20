package com.cdut.sx.websocket;

import com.cdut.sx.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket/{id}")
@Component
public class Websocket {
    private static int onlineCount = 0;

    private static Map<User, Websocket> clients = new ConcurrentHashMap<User, Websocket>();

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        Websocket.applicationContext = applicationContext;
    }

    @OnClose
    public void onClose(Session conn) throws IOException {
        // TODO Auto-generated method stub
        WebsocketService.removeUser(conn);
        WebsocketService.sendMessagetoAll("用户下线了");
    }

    @OnError
    public void onError(Throwable error) throws IOException {
        // TODO Auto-generated method stub
        WebsocketService.sendMessagetoAll("用户: 跪了" + error.getMessage());
    }


    public void onStart() {

    }

    @OnMessage
    public void onMessage(Session conn, String message, @PathParam("id") String id) throws IOException {
        // TODO Auto-generated method stub
//        WebsocketService.addUser(username, conn);
//        String userFrom = message.split(" ")[0];
        String userTo = message.split(" ")[0];
        Set<User> users = clients.keySet();
        for (User user : users) {
            if (user.getUsername().equals(userTo)) {
                WebsocketService.sendMessage(message.split(" ")[2], userTo);
            }
        }
    }


    @OnOpen
    public void onOpen(Session conn, @PathParam("id") String username) {
        WebsocketService.addUser(username, conn);
        // TODO Auto-generated method stub
        try {
            WebsocketService.sendMessagetoAll("已开启");
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
	



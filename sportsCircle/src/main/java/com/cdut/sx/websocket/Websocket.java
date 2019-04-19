package com.cdut.sx.websocket;

import com.cdut.sx.pojo.User;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket")
public class Websocket {
    private static Thread t = new Thread();
    private static int onlineCount = 0;

    private static Map<User, Websocket> clients = new ConcurrentHashMap<User, Websocket>();

    private Session session;

    private User user;

    private static int id = 0;


    @OnClose
    public void onClose(Session conn, int message, String reason, boolean remote) throws IOException {
        // TODO Auto-generated method stub
        WebsocketService.removeUser(conn);
        WebsocketService.sendMessagetoAll("用户下线了");
    }

    @OnError
    public void onError(Session arg0, Exception arg1) {
        // TODO Auto-generated method stub

    }


    public void onStart() {

    }

    @OnMessage
    public void onMessage(Session conn, String message) throws IOException {
        // TODO Auto-generated method stub
        WebsocketService.addUser(message, conn);
        String userFrom = message.split(" ")[0];
        String userTo = message.split(" ")[1];
        Set<User> users = clients.keySet();
        for (User user : users) {
            if (user.getUsername().equals(userTo)) {
                WebsocketService.sendMessage(message, userTo);
            }
        }
    }


    @OnOpen
    public void onOpen() {
        // TODO Auto-generated method stub

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
	



package com.cdut.sx.websocket;

import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;



public class WebsocketService {
    private WebsocketService() {
    }

    private static final Map<Session, String> userconnections = new HashMap<Session, String>();

    //向连接池中添加连接
    public static void addUser(String user, Session conn) {
        userconnections.put(conn, user); // 添加连接
    }

    // 移除连接池中的连接
    public static boolean removeUser(Session conn) {
        if (userconnections.containsKey(conn)) {
            userconnections.remove(conn); // 移除连接
            return true;
        } else {
            return false;
        }
    }

    // 向所有的用户发送消息
    public static void sendMessagetoAll(String message) throws IOException {
        Set<Session> keySet = userconnections.keySet(); //获取目前用户的socket
        synchronized (keySet) {
            for (Session conn : keySet) {
                String user = userconnections.get(conn);
                if (user != null) {
                    conn.getBasicRemote().sendText(message);
                }
            }
        }
    }

    public static void sendMessage(String message, String username) throws IOException {
        Set<Session> keySet = userconnections.keySet(); //获取目前用户的socket
        Set<Map.Entry<Session, String>> messageEntry = userconnections.entrySet();
        for (Map.Entry<Session, String> connection : messageEntry) {
            if (connection.getValue().equals(username)) {
                connection.getKey().getBasicRemote().sendText(message);
            }
        }
    }


    public static boolean isEmpty() {
        return userconnections.size() == 0;
    }

}




package com.cdut.sx.websocket;

import org.java_websocket.WebSocket;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
*/


public class WebsocketService {
    private static final Map<WebSocket, String> userconnections = new HashMap<WebSocket, String>();

    //向连接池中添加连接
    public static void addUser(String user, WebSocket conn) {
        userconnections.put(conn, user); // 添加连接
    }

    // 移除连接池中的连接
    public static boolean removeUser(WebSocket conn) {
        if (userconnections.containsKey(conn)) {
            userconnections.remove(conn); // 移除连接
            return true;
        } else {
            return false;
        }
    }

    // 向所有的用户发送消息
    public static void sendMessage(String message) {
        Set<WebSocket> keySet = userconnections.keySet(); //获取目前用户的socket
        synchronized (keySet) {
            for (WebSocket conn : keySet) {
                String user = userconnections.get(conn);
                System.out.println(user);

                if (user != null) {
                    conn.send(message);
                }
            }
        }
    }

    public static boolean isEmpty() {
        return userconnections.size() == 0;
    }

}




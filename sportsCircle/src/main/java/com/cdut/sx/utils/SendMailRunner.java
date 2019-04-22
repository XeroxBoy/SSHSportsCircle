package com.cdut.sx.utils;

import com.cdut.sx.websocket.Websocket;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class SendMailRunner implements CommandLineRunner {
    private Websocket ws;
    @Override
    public void run(String... args) throws Exception {
//        int n = 1;
//        Sendmail.sendMail(n);
//        while (true) {
//            Thread.sleep(100000);
//            Sendmail.sendMail(++n);
//        }
    }

}


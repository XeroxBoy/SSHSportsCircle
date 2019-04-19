package com.cdut.sx.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class SendMailRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        int n = 1;
        Sendmail.sendMail(n);
        while (true) {
            Thread.sleep(10000);
            Sendmail.sendMail(++n);
        }
    }
}


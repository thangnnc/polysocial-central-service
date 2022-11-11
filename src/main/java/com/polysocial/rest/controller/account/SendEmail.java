package com.polysocial.rest.controller.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.polysocial.utils.SendMail;

@Controller
public class SendEmail {

    @GetMapping("/test")
    public String send(@RequestParam String email) {
        SendMail send = new SendMail();
        System.out.println("123");
        send.sendMail(email);
        System.out.println("456");
        return "done";
    }
}

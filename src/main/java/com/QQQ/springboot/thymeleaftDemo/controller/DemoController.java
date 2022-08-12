package com.QQQ.springboot.thymeleaftDemo.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/hello")
    public String hello(Model theModel) {
        theModel.addAttribute("date", new Date());
        
        return "helloworld";
    }

}

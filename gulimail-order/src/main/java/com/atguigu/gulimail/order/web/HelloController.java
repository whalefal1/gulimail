package com.atguigu.gulimail.order.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HelloController {


    @GetMapping("/{page}.html")
    public String listPage(@PathVariable("page") String page , Model model){
       return  page;
    }
}

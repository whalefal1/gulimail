package com.atguigu.gulimail.product.web;


import com.atguigu.gulimail.product.entity.CategoryEntity;
import com.atguigu.gulimail.product.service.CategoryService;
import com.atguigu.gulimail.product.vo.Catelog2Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    CategoryService categoryService;


    @GetMapping({"/","/index.html"})
    public String indexPage(Model model){
        List<CategoryEntity> categoryEntities = categoryService.getLevel1Categorys();
        //视图解析器进行拼串
        //classpath:/templates/xxxx.html
        model.addAttribute("categorys",categoryEntities);
        return "index";
    }


    @ResponseBody
    @GetMapping("/index/json/catalog.json")
    public Map<String,List<Catelog2Vo>> getCatelogJson(HttpSession session){
        System.out.println("用户："+session.getAttribute("loginUser")+"请求了首页数据");
        //1.查出所有分类
        Map<String,List<Catelog2Vo>> map  = categoryService.getCatelogJson();
        return map;
    }
}

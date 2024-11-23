package com.atguigu.gulimail.search.controller;


import com.atguigu.gulimail.search.service.MailSearchService;
import com.atguigu.gulimail.search.vo.SearchParam;
import com.atguigu.gulimail.search.vo.SearchResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SearchController {

    @Autowired
    private MailSearchService mailSearchService;

    /**
     * Description
     * 自动将请求的数据映射到对象中
     * @param param
     * @return {@link String }
     * @author 李朋逊
     * @date 2024/04/05
     */
    @GetMapping("/list.html")
    public String search(SearchParam param, HttpServletRequest request, Model model) {

        //获取所有原生查询条件
        param.set_queryString(request.getQueryString());

        //1、根据传递来的页面的查询参数，去es中检索商品
        SearchResult result = mailSearchService.search(param);

        model.addAttribute("result",result);
        return "list";
    }
}

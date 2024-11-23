package com.atguigu.gulimail.search.service;

import com.atguigu.gulimail.search.vo.SearchParam;
import com.atguigu.gulimail.search.vo.SearchResult;

public interface MailSearchService {
    SearchResult search(SearchParam searchParam);
}

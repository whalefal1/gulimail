package com.atguigu.gulimail.member.service;

import com.atguigu.gulimail.member.exception.PhoneException;
import com.atguigu.gulimail.member.exception.UsernameException;
import com.atguigu.gulimail.member.vo.MemberUserLoginVo;
import com.atguigu.gulimail.member.vo.MemberUserRegisterVo;
import com.atguigu.gulimail.member.vo.SocialUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.gulimail.member.entity.MemberEntity;

import javax.swing.*;
import java.util.Map;

/**
 * 会员
 *
 * @author lipengxun
 * @email lipengxun38@gmail.com
 * @date 2024-01-26 09:41:34
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void register(MemberUserRegisterVo vo);

    void checkPhoneUnique(String phone) throws PhoneException;

    /**
     * 判断用户名是否重复
     * @param userName
     * @return
     */
    void checkUserNameUnique(String userName) throws UsernameException;

    MemberEntity login(MemberUserLoginVo vo);

    MemberEntity login(SocialUser socialUser) throws Exception;
}


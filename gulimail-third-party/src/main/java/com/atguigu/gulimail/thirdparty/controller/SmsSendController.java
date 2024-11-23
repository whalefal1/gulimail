package com.atguigu.gulimail.thirdparty.controller;


import com.atguigu.common.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class SmsSendController {

    //    SmsComponent smsComponent;



    /**
     * Description
     *  提供给别的服务调用
     * @param phone
     * @param code
     * @return {@link R }
     * @author 李朋逊
     * @date 2024/04/11
     */
    @GetMapping("/sendcode")
    public R sendCode(@RequestParam("phone") String phone, @RequestParam("code") String code) {
        //        smsComponent.sendCode(phone, code);
        return R.ok();
    }
}

package com.atguigu.gulimail.auth.controller;


import com.alibaba.fastjson.TypeReference;
import com.atguigu.common.constant.AuthServerConstant;
import com.atguigu.common.exception.BizCodeEnume;
import com.atguigu.common.utils.R;
import com.atguigu.common.vo.MemberResponseVo;
import com.atguigu.gulimail.auth.feign.MemberFeignService;
import com.atguigu.gulimail.auth.feign.ThirdPartyFeignService;
import com.atguigu.gulimail.auth.vo.UserLoginVo;
import com.atguigu.gulimail.auth.vo.UserRegisterVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Controller
public class LoginController {

    @Autowired
    private ThirdPartyFeignService thirdPartyFeignService;

    @Autowired
   private MemberFeignService memberFeignService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @GetMapping("/sms/sendcode")
    @ResponseBody
    public R sendCode(@RequestParam("phone") String phone){

        //TODO:  1、接口防刷

        //2、验证码的再次校验，redis

        String redisCode = stringRedisTemplate.opsForValue().get(AuthServerConstant.SMS_CODE_CACHE_PREFIX + phone);


        if(!StringUtils.isEmpty(redisCode)){
            long l = Long.parseLong(redisCode.split("_")[1]);
            //60s内不允许再次发送
            if(redisCode != null && System.currentTimeMillis() - l < 60000){
                //60s内不允许再次发送
                return R.error(BizCodeEnume.SMS_CODE_EXCEPTION.getCode(),BizCodeEnume.SMS_CODE_EXCEPTION.getMessage());
            }
        }
        String code = UUID.randomUUID().toString().substring(0, 5)+"_"+System.currentTimeMillis();
        //验证码存入redis并指定过期时间
        stringRedisTemplate.opsForValue().set(AuthServerConstant.SMS_CODE_CACHE_PREFIX + phone,code,10, TimeUnit.MINUTES);

        thirdPartyFeignService.sendCode(phone,code);

        return R.ok();
    }


    @PostMapping("/register")
    public String regist(@Valid UserRegisterVo vo, BindingResult result,
                         RedirectAttributes attributes) {
        if (result.hasErrors()) {

            Map<String, String> errors = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            attributes.addFlashAttribute("errors", errors);
            System.out.println(errors);
            return "redirect:http://localhost:8150/reg.html";
        }

        //1、校验验证码
        String code = vo.getCode();

        String s = stringRedisTemplate.opsForValue().get(AuthServerConstant.SMS_CODE_CACHE_PREFIX + vo.getPhone());
        if (!StringUtils.isEmpty(s)) {
            if (code.equals(s.split("_")[0])) {
                //删除验证码;令牌机制
                stringRedisTemplate.delete(AuthServerConstant.SMS_CODE_CACHE_PREFIX + vo.getPhone());
                //验证码通过
                R register = memberFeignService.register(vo);
                if (register.getCode() == 0) {
                    //成功
                    return "redirect:http://localhost:8150/login.html";
                } else {
                    //失败
                    Map<String, String> errors = new HashMap<>();
                    errors.put("msg", register.getData("msg",new TypeReference<String>(){}));
                    attributes.addFlashAttribute("errors",errors);
                    return "redirect:http://localhost:8150/reg.html";
                }
            } else {
                Map<String, String> errors = new HashMap<>();
                errors.put("code", "验证码错误");
                attributes.addFlashAttribute("errors", errors);
                return "redirect:http://localhost:8150/reg.html";
            }
        }
            return "redirect:http://localhost:8150/reg.html";
        }


        @PostMapping("/login")
        public String login(UserLoginVo vo, RedirectAttributes attributes, HttpSession session,Model model) {
            R login = memberFeignService.login(vo);
            if (login.getCode() == 0) {
                MemberResponseVo data = login.getData("data", new TypeReference<MemberResponseVo>() {
                });

                System.out.println(data.toString());
                session.setAttribute(AuthServerConstant.LOGIN_USER,data);
                model.addAttribute("session.loginUser",data);
                return "redirect:http://localhost:10000";
            } else {
                Map<String, String> errors = new HashMap<>();
                errors.put("msg", login.getData("msg", new TypeReference<String>() {
                }));
                attributes.addFlashAttribute("errors", errors);
                return "redirect:http://localhost:8150/login.html";
            }
        }
}

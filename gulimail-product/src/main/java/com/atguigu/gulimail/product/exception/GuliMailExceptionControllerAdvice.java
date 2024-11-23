package com.atguigu.gulimail.product.exception;

import com.atguigu.common.exception.BizCodeEnume;
import com.atguigu.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * GuliMailProductController统一异常处理
 * @author 李朋逊
 * @date 2024/03/01
 */
//@ControllerAdvice(basePackages = "com.atguigu.gulimail.product.controller")
//@ResponseBody
@RestControllerAdvice(basePackages = "com.atguigu.gulimail.product.controller")
@Slf4j
public class GuliMailExceptionControllerAdvice {


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
        public R handleValidException(MethodArgumentNotValidException e) {
            log.error("数据校验出现问题{},异常类型{}", e.getMessage(), e.getClass());
            BindingResult bindingResult = e.getBindingResult();
            Map<String,String> errorMap = new HashMap<>();
            bindingResult.getFieldErrors().forEach((fieldError) -> {
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            });
            return R.error(BizCodeEnume.VAILD_EXCEPTION.getCode(), BizCodeEnume.VAILD_EXCEPTION.getMessage()).put("data", errorMap);
        }

        @ExceptionHandler(value = Throwable.class)
        public R handleException(Throwable throwable) {
            log.error("错误：", throwable);
            return R.error(BizCodeEnume.UNKNOW_EXCEPTION.getCode(), BizCodeEnume.UNKNOW_EXCEPTION.getMessage());
        }
}

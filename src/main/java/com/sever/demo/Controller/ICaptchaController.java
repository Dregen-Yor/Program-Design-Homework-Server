package com.sever.demo.Controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
    这个现在没用
 */
@RestController
@RequestMapping("/Get")
public class ICaptchaController {
    @GetMapping("/Captcha")
    public String getCap(){
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(152, 76);
        return lineCaptcha.getImageBase64();
    }
}

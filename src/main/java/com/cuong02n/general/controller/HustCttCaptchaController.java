package com.cuong02n.general.controller;

import com.cuong02n.general.service.hustservice.captcha.HustCttCaptchaService;
import com.cuong02n.general.service.hustservice.loginctt.LoginCttService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ctt-captcha")
@RequiredArgsConstructor
public class HustCttCaptchaController {
    final HustCttCaptchaService captchaService;
    private final LoginCttService loginCttService;

//    @GetMapping("")
//    public ResponseEntity<?> generateCaptcha() {
//        return ResponseEntity.status(302).header("Location", captchaService.getCaptchaImageUrl()).build();
//    }

//    @GetMapping("fetch-model-captcha")
//    public void fetchModelCaptcha(@RequestParam("time") int time) {
//        captchaService.getAndSaveMultipleCaptchaImage(time);
//    }
    @GetMapping("login")
    public void login(@RequestParam String username, @RequestParam String password) {
        loginCttService.loginCtt(username,password);
    }
}

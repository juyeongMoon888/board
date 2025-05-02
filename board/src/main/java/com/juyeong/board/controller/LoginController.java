package com.juyeong.board.controller;

import com.juyeong.board.domain.Member;
import com.juyeong.board.service.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginForm form) {
        return "loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "loginForm";
        }
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        log.info("loginMember={}", loginMember);
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다");
            return "loginForm";
        }

        //로그인 성공 처리
        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getLoginId()));
        response.addCookie(idCookie);
        return "redirect:/";
    }
}

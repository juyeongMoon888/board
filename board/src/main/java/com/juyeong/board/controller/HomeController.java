package com.juyeong.board.controller;

import com.juyeong.board.domain.Member;
import com.juyeong.board.repository.MemberRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final MemberRepository memberRepository;

    @RequestMapping("/")
    public String homeLogin(
            @CookieValue(name = "memberId", required = false) Long memberId,
                            Model model) {

        if (memberId == null) {
            return "home"; //아무것도 받지 않는 html이 필요
        }

        //로그인 한 사용자 멤버 저장소에서 회원 꺼내기
        Member loginMember = memberRepository.findById(memberId);
        if (loginMember == null) {
            //쿠키가 너무 오래전에 만들어졌으면 DB에 데이터가 없을 수도 있다.
            return "home";
        }
        model.addAttribute("member", loginMember);
        return "loginHome";
    }

}

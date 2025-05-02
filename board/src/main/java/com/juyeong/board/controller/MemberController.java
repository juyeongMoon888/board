package com.juyeong.board.controller;

import com.juyeong.board.domain.Member;
import com.juyeong.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(@ModelAttribute("form") MemberForm form) {
        return "members/createMemberForm2";
    }

    @PostMapping("/members/new")
    public String createV2(@ModelAttribute("form") MemberForm form, BindingResult bindingResult) {

        //검증 로직
        if (!StringUtils.hasText(form.getLoginId())) {
            bindingResult.addError(new FieldError("form", "LoginId", "아이디는 필수 입니다."));
        }
        //한글, 정귶
        if (form.getLoginId().matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) {
            bindingResult.addError(new FieldError("form", "LoginId", "영문으로 입력하세요"));
        }

        //검증에 실패하면 다시 입력 폼으로
        if (bindingResult.hasErrors()) {
            return "members/createMemberForm2";
        }

        //성공 로직
        Member member = new Member();
        member.setLoginId(form.getLoginId());
        member.setPassword(form.getPassword());
        member.setName(form.getName());
        memberService.join(member);
        return "redirect:/";//localhost:8080으로 돌려보낸다. -> 환영합니다 페이지로!
    }
}

package com.juyeong.board.service;

import com.juyeong.board.controller.LoginForm;
import com.juyeong.board.domain.Member;
import com.juyeong.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    @Autowired
    MemberRepository memberRepository;

    @Transactional
    public Member login(String loginId, String password) {
        List<Member> findMember = memberRepository.findByLoginId(loginId);
        return findMember.stream()
                .filter(m -> m.getPassword().equals(password))
                .findAny()
                .orElse(null);
    }

}

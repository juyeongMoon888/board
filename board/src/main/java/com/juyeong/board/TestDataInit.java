package com.juyeong.board;

import com.juyeong.board.domain.Member;
import com.juyeong.board.repository.MemberRepository;
import com.juyeong.board.service.MemberService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @PostConstruct
    public void init() {
        Member member = new Member();
        member.setLoginId("test");
        member.setPassword("test!");
        member.setName("테스터");
        memberService.join(member);//서비스 계층에서 transation하는데 memberRepository(transactionX)에서 바로 save해서 안된듯
    }
}

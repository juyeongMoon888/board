package com.juyeong.board;

import com.juyeong.board.domain.Member;
import com.juyeong.board.repository.MemberRepository;
import com.juyeong.board.service.MemberService;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*@SpringBootTest
@Transactional
class RepositoryTest {


    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;
    @Autowired
    MemberService memberService;

    @Test
    void findById() throws Exception{

        Member member = new Member();
        member.setLoginId("ki");
        member.setName("memberP");
        member.setPassword("12");

        Long saveMember = memberService.join(member); //세팅해주고 꼭 save 해줘야함.
        List<Member> ki = memberRepository.findByLoginId("ki");

        em.flush();
        Assertions.assertThat(member.getLoginId()).isEqualTo("ki");

    }
}*/

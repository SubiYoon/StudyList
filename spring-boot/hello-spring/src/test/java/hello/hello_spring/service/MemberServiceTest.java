package hello.hello_spring.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;

// @SpringBootTest
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    void BeforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    void AfterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");
        // when
        Long saveId = memberService.join(member);
        // then
        Member result = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(result.getName());
    }

    @Test
    void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        // when
        Long saveId1 = memberService.join(member1);
        try {
            Long saveId2 = memberService.join(member2);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
        // then
        Member result = memberService.findOne(saveId1).get();
        assertThat(member1.getName()).isEqualTo(result.getName());
    }

    @Test
    void findMembers() {
        // given

        // when

        // then
    }

    @Test
    void findOne() {
        // given

        // when

        // then
    }

}

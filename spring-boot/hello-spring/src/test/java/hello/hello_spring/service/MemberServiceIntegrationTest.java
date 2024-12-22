package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring");
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

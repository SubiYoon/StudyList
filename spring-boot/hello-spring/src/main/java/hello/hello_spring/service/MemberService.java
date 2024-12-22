package hello.hello_spring.service;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     *
     * @param member
     * @return
     */
    public Long join(Member member) {
        // 중복 검사
        validateDuplicateMember(member);

        memberRepository.save(member);

        return member.getId();
    }

    /**
     * 중복회원 검사
     *
     * @param member
     * @throws throw new IllegalStateException("이미 존재하는 회원입니다.");
     */
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체회원 조회
     *
     * @return
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 특정 회원 조회
     *
     * @param id
     * @return
     */
    public Optional<Member> findOne(Long id) {
        return memberRepository.findById(id);
    }
}

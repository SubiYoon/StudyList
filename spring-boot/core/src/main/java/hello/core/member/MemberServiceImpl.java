package hello.core.member;

public class MemberServiceImpl implements MemberService{
    private MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
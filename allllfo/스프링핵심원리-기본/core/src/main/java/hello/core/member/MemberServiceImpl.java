package hello.core.member;

public class MemberServiceImpl implements MemberService { //구현체 하나만 있는 경우 관례상 impl이라고 많이 씀

    private final MemberRepository memberRepository;
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

}

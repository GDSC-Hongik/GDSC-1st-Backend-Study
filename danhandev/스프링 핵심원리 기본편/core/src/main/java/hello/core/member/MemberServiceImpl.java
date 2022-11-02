package hello.core.member;

// 회원 서비스 구현체
public class MemberServiceImpl implements MemberService{

    private final MemberRespository memberRespository;

    public MemberServiceImpl(MemberRespository memberRespository) {
        this.memberRespository = memberRespository;
    }

    @Override
    public void join(Member member) {
        memberRespository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRespository.findById(memberId);
    }

    //테스트 용도
    public MemberRespository getMemberRepository() {
        return memberRespository;
    }
}

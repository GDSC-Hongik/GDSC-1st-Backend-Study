package hello.core.member;

// 회원 저장소 인터페이스
public interface MemberRespository {

    void save(Member member);

    Member findById(Long memberId);
}

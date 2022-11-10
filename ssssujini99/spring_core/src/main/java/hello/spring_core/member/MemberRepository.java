package hello.spring_core.member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);
}

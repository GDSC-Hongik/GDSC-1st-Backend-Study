package hello.spring_core.member;

public interface MemberService {

    void join(Member member); // 서비스 기능1 - 회원 가입

    Member findMember(Long memberId); // 서비스 기능2 - 회원 조회
}

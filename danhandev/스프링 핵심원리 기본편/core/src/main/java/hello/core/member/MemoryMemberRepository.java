package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

// 메모리 회원 저장소 구현체
@Component
public class MemoryMemberRepository implements MemberRespository{
    // HashMap은 동시성 이슈가 발생할 수 있다. ConcurrentHashMap 사용
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}

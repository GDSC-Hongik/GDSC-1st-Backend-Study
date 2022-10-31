package GDSC.Hongik.hellospring.repository;

import GDSC.Hongik.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// @Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // long형 0

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // id를 1씩 증가시킴
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        // 만약에 없으면? 널 가능성 있다. 따라서 Optional.ofNullable로 감싸준다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) // lambda expression
                .findAny();
    // stream은 데이터 흐름이다. 파이썬의 iterable 생각하면 쉬울듯?
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear(); // 멤버를 담는 레포지토리 해시맵을 클리어한다
    }
}

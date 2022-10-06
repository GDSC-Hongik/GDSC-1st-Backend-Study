package GDSC.Hongik.hellospring.repository;

import GDSC.Hongik.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // long형 0

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
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
}

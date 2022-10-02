// 3. 회원 관리 예제 - 백엔드 개발 - 2) 회원 도메인과 리포지토리 만들기

package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store= new HashMap<>(); // import(Map, HashMap) 필요. 저장할 곳.
    private static long sequence= 0L;

    // implement methods 선택해서 추가

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // store에 넣기 전에 id값 세팅. save하기 전에 name은 미리 넘어온 상태라고 보면 됨.
        store.put(member.getId(), member); // store에(map에) 저장함.
        return member; // 스펙에 따라 저장된 결과 반환해줌.
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
}

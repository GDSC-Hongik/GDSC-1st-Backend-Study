// 3. 회원 관리 예제 - 백엔드 개발 - 2) 회원 도메인과 리포지토리 만들기
// 3. 회원 관리 예제 - 백엔드 개발 - 3) 회원 리포지토리 테스트 케이스 작성
// 회원 리포지토리 구현체

package hello.hellospring.repository;

import hello.hellospring.domain.Member; // implement결과
import java.util.*; // implement 결과

public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store= new HashMap<>(); // import(Map, HashMap) 필요. 저장할 곳. member의 id와 member가 key,value꼴로 저장됨.
    private static long sequence= 0L;

    // implement methods 선택해서 추가
    @Override
    public Member save(Member member) {
        member.setId(++sequence); // store에 넣기 전에 member의 id값 세팅. save하기 전에 member에 name은 미리 넘어온 상태라고 보면 됨.
        store.put(member.getId(), member); // store에(map에) 저장함.
        return member; // 스펙에 따라 저장된 결과 반환해줌.
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // store에서 꺼내면 됨. 단, 그 결과가 없으면 null이 될 것임. null이 반환될 가능성이 있기에 이를 optional로 감쌈
    } // 감싸서 반환하면 클라가 무언갈 할 수 있음

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // store(map)를 루프로 돌림 (java 8 lambda사용)
                .filter(member -> member.getName().equals(name)) // member에서 member.getName()이 parameter로 넘어온 name과 같은지 확인
                .findAny(); // 같은 경우에만 필터링되고 그 중 찾으면 반환함. 결과가 optional로 반환됨.
    }

    @Override
    public List<Member> findAll() { // map인데 반환은 List임.
        return new ArrayList<>(store.values()); // store에 있는 value들(member들) 반환.
    }

    public void clearStore(){ // 3.3 - 테스트 코드 위해 만듬
        store.clear();
    }
}

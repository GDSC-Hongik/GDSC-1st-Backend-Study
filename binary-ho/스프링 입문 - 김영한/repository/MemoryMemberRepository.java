//@Repository
public class MemoryMemberRepository implements MemberRepository {

  // 실무에서는 동시성 문제가 있을 수 있어서 ConcurrentHashMap
  // AtomicLong 등을 써야한다
  private static Map<Long, Member> store = new HashMap<>();
  private static long sequence = 0L; // 키값을 생성해준다

  @Override
  public Member save(Member member) {
    member.setId(++sequence);
    store.put(member.getId(), member);
    return member;
  }

  @Override
  public Optional<Member> findById(Long id) {
    return Optional.ofNullable(store.get(id));
  }

  // 동명이인 일떄는 어쩌려고?
  @Override
  public Optional<Member> findByName(String name) {
    return store.values().stream()
        .filter(member -> member.getName().equals(name))
        .findAny();
  }

  @Override
  public List<Member> findAll() {
    return new ArrayList<>(store.values());
  }

  public void clearStore() {
    store.clear();
  }
}

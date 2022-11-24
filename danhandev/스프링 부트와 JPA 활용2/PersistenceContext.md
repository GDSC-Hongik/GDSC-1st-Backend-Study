# EntityManager

EntityManager는 자바 객체(@Entity)를 DB에 저장된 데이터와 맵핑해주는 ORM 기술을 정의한 인터페이스이다.

@Entity를 달고 있는 Entity 객체들을 관리한다. 실제 DB 테이블과 매핑하여 데이터를 조회/수정/저장하는 중요한 기능을 수행한다. PersistenceContext라는 논리적 영역을 두어, 내부적으로 Entity의 생애주기를 관리한다.

```java
@Repository
public class JpaRepository {

	@PersistenceContext
	private EntityManage em;
}
```

# PersistenceContext

영속성 컨텍스트는 Entity를 영구 저장하는 환경으로 논리적인 개념이다. 어플리케이션과 데이터베이스 사이에서 객체를 보관하는 가상의 DB 역할을 한다고 볼 수 있다.

# Entity의 생애 주기

Entity 객체는 네 가지 상태 중 하나의 상태를 가진다.

![](https://user-images.githubusercontent.com/78093844/203713394-b8b7bcc8-60d6-4fac-9e9a-4ea40118c622.png)

### New/Transient 비영속 상태

처음 생성되어 아직 EntityManager의 관리를 받지 않는 상태이다 순수한 자바 객체라고 볼 수 있다.

### Managed 영속 상태

EntityManager에 의해 관리되는 상태이다. EntityManager에서 persist() 메서드를 호출하거나 JPQL로 쿼리된 Entity 객체는 이 상태에 놓이게 된다. EntityManager 내부의 PersistenceContext에 데이터가 저장된 일종의 캐싱 상태에 가깝다. DB에 실제 데이터가 저장된 것은 아니다.

### Detached 준영속 상태

EntityManger가 더 이상 관리하지 않는 상태이다. PersistenceContext에 저장되었다가 분리된 상태이다.

### Removed 삭제 상태

EntityManager의 PersistenceContext에서 완전히 삭제된 상태이다. Detached 상태와 달리, DB에 맵핑된 데이터도 함께 제거된다.

```java
@Repository
pubilc class JpaRepository {

	@PersistenceContext
	private EntityManger em;

	public Long save(Member member) { // new
		em.persist(member);             // managed
		em.detach(member);              // detached
		return member.getId();
	}
}
```

## EntityManager의 이점

EntityManger는 데이터베이스와 어플리케이션 사이에 위치하여 편리한 기능들을 제공한다. 대표적인 특징으로는 First Level Cache, Identity, Write-Behind, Dirty Check, Lazy Loading 등이 있다.

### First Level Cache 1차 캐시

데이터베이스와 어플리케이션 사이에 위치하기 때문에 캐싱 기능을 사용할 수 있다.  PersistenceContext에 등록된 Entity 객체의 @Id가 붙은 필드 값을 사용하며 Map<Id, Entity> 형태로 저장한다.

어플리케이션에서 데이터 조회를 요청하면, EntityManager는 먼저 내부에 캐싱된 Entity가 있는지 확인한다. 데이터가 존재하면 DB에 쿼리를 전송하지 않고, 캐싱된 Entity를 반환하기 때문에 쿼리 성능이 최적화된다. 네트워크 리소스를 아낄 수 있다.

1차 캐시는 트랜잭션 단위로 유지된다.


1. 1차 캐시에서 조회
2. 1차 캐시에서 없을 경우
      DB에서 조회 → 1차 캐시에 저장 → 반환

### Identity 동일성 보장

EntityManger는 repeatable read 등급의 트랜잭션 격리 수준을 어플리케이션에서도 제공한다. 1차 캐시로 Entity를 저장하기 때문에 동일한 Entity를 요청할 때는 동일한 Entity를 반환함을 보장한다.

> Repeatable read 등급 
> - 트랜잭션 격리 수준이란 여러개의 트랜잭션이 처리될 때에 다른 트랜잭션에서 변경 및 조회하는 데이터를 확인할 수 있도록 허용할지에 대한 수준 제한
> - 트랜잭션 격리 수준은 0~3단계 중 2단계
> - 트랜잭션이 완료될 때까지 한 번 SELECT한 데이터를 다시 SELECT해도 결과는 같음을 보장
>

```java
Member a = em.find(Member.class, "member1");
Member b = em.find(Member.class, "member1");

System.out.println(a == b) // 동일성 보장
```

### Write-Behind 쓰기 지연

EntityManager가 생성된 후 transaction.begin()으로 트랜젝션을 시작한다. 그 사이에 데이터를 삽입/수정/조회 등의 작업을 한다. 이 작업들은 커밋하지 전이므로 DB에 반영되지 않는다. 시작한 트랜잭션을 커밋하지 않으면 DB에 전달되지 않으므로 쓰기 지연이 발생한다.

필요한 작업들을 모두 모아 한번에 DB에 SQL을 보내고 트랜잭션을 커밋해버리기 때문에 다른 비즈니스 로직에서 데이터베이스의 락이 풀릴때까지 기다리지 않아도 된다. 데이터를 처리하는 과정에서 의도치 않은 에러가 발생한 경우 roll-back이 용이하며, 네트워크 비용을 최소화할 수 있다는 장점을 지닌다.

```java
EntityManager em = emf.createEntityManager();
EntityTransaction transaction = em.getTransaction();
//엔티티 매니저는 데이터 변경시 트랜잭션을 시작해야 한다.
transaction.begin(); // [트랜잭션] 시작
em.persist(memberA);
em.persist(memberB);

//여기까지 INSERT SQL을 데이터베이스에 보내지 않는다.
//커밋하는 순간 데이터베이스에 INSERT SQL을 보낸다.
transaction.commit(); // [트랜잭션] 커밋
```

### Dirty Check 변경 감지

EntityManager에 등록된 Entity에 수정이 발생하면, 트랜잭션이 commit되는 시점에서 내부적으로 flush를 호출한다. Entity를 PersistenceContext에 보관할 때 최초 상태를 복사해 저장해두는 데 이를 스냅샷이라고 한다. 이 스냅샷과 Entity를 비교해 변경사항을 감지하고 UPDATE 쿼리를 자동으로 실행한다. 이 쿼리는 flush의 직접 호출이나 commit을 통해 DB에 반영된다.

<aside>
💡 flush는 PersistenceContext의 변경 내용을 DB에 동기화하는 작업

</aside>

```java
EntityManager em = emf.createEntityManager();
EntityTransaction transaction = em.getTransaction();
transaction.begin(); // [트랜잭션] 시작
// 영속 엔티티 조회
Member memberA = em.find(Member.class, "memberA");
// 영속 엔티티 데이터 수정
memberA.setUsername("hi");
memberA.setAge(10);

//em.update(member) 이런 코드가 없어도 된다!
transaction.commit(); // [트랜잭션] 커밋
```

### Lazy Loading

@ManyToOne 같이 다대일 관계를 가진 Entity를 조회할 때, 실제로 해당 Entity의 데이터를 직접 접근하는 시점에 쿼리를 실행하는 방식을 의미한다.SELECT시 한번에 Join 해서 다 가져오지 않고 프록시로 반환한 후, 매핑된 엔티티를 사용해야 할 때 다시 조회해서 가져온다. Lazy Loading을 통해 EntityManager는 불필요한 쿼리를 최소화한다.
# chapter 3: 객체 지향 원리 적용
## 의도한 할인 정책 
![img.png](img.png)

## 현실
![img_1.png](img_1.png)

DiscountPolicy 라는 인터페이스를 잘 활용하는 듯 보이지만, 코드를 보면 그렇지 않다.

할인 정책이 바뀔때 OrderServiceImpl(클라이언트)의 코드도 같이 수정되고 있다.

이는 OCP위반이다.
```java
public class OrderServiceImpl implements OrderService{
    
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
```

코드를 아래와 같이 수정하면 인터페이스에만 의존하도록 할 수 있다.
```java
public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private DiscountPolicy discountPolicy;
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
```

이제 새로운 문제가 생긴다.

바로 NullPointerException이다.

discoutPolicy에 할당된 것이 없으니 당연하다.

이를 해결하기 위해 AppConfig를 도입한다.

AppConfig란 애플리케이션의 전체 동작 방식을 구성(config)하기 위해, 구현 객체를 생성하고, 연결하는 책임을 가지는
별도의 설정 클래스이다.

```java
public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }
    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
```
AppConfig에서 어떤 구현 객체를 주입할 지 모두 결정해주고 있다.

AppConfig의 등장으로 구성영역과 사용영역으로 분리된다.

할인 정책이 변해도 사용영역은 그대로 두고 구성영역만 수정하면 된다.


# chapter4: 스프링 컨테이너와 스프링 빈

앞선 3장의 끝자락에서는 MemberApp과 OrderApp에 ApplicationContext로 스프링 컨테이너를 적용해줬다.

AppConfig는 아래와 같이 변해 스프링 컨테이너에 스프링 빈을 등록할 수 있도록 한다.

```java
@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
```
'@Bean' annotation이 붙은 method들은 스프링 컨테이너에 스프링 빈으로 등록된다.

method의 이름이 스프링 빈의 이름이 되고, method가 반환하는 것이 스프링 빈의 객체가 된다.

'스프링 입문을 위한 자바 객체 지향의 원리와 이해'를 읽고
XML에 대해 발표 준비를 하면서 XML이 고전적인 방식임은 접한 적이 있다.

따라서 XML을 활용한 예시는 생략한다.


# chapter5: 싱글톤 컨테이너
이전에 책을 통해 싱글톤을 접했을 때는 무슨 용도인지 잘 이해하지 못했다.

싱글톤 디자인 패턴이 적용되지 않은 코드는
고객 트래픽이 둘 이상이라면 클래스의 인스턴스를 2개 이상 생성하게 된다.

이는 메모리 낭비가 심하고 경우에 따라 개발자가 원치 않는 결과물을 만들 수도 있다.

그래서 사용하는 것이 싱글톤이라는 것이다.

```java
public class SingletonService {
    
     private static final SingletonService instance = new SingletonService();

     public static SingletonService getInstance() {
         return instance;
    } 

     private SingletonService() {
     }
     public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
     }
}
```
+ static으로 객체를 하나만 생성하고 이를 계속해서 활용한다.
+ public으로 선언된 getInstance()를 통해 접근.
+ private으로 생성자를 선언하여 외부 생성을 방지.

## 싱글톤 패턴의 문제점
+ 의존관계상 클라이언트가 구체 클래스에 의존하여 DIP를 위반함.
+ 유연성이 떨어짐.

    => 이러한 문제점 해결하기 위해 싱글톤 컨테이너를 사용.

## 싱글톤 컨테이너

```java
public class SingletonTest {
  @Test
  @DisplayName("스프링 컨테이너와 싱글톤")
  void springContainer() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    MemberService memberService1 = ac.getBean("memberService", MemberService.class);
    MemberService memberService2 = ac.getBean("memberService", MemberService.class);
    //참조값이 같은 것을 확인
    System.out.println("memberService1 = " + memberService1);
    System.out.println("memberService2 = " + memberService2);
    //memberService1 == memberService2
    assertThat(memberService1).isSameAs(memberService2);
  }
}
```
+ 스프링 컨테이너는 싱글턴 패턴을 적용하지 않아도, 객체 인스턴스를 싱글톤으로 관리.
+ 고객의 요청이 올 때 마다 객체를 생성하는 것이 아니라, 이미 만들어진 객체를
  공유해서 효율적으로 재사용.

## 싱글톤 주의점
여러 클라이언트가 하나의 같은 객체 인스턴스를 공유하기 때문에 싱글톤 객체는 무상태(stateless)로 설계해야 함.

```java
public class StatefulService {
    private int price; //상태를 유지하는 필드
    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; //여기가 문제!
     }
    public int getPrice() {
        return price;
    }
}
```
위와 같은 경우에는 price를 공유하므로 사용자간에 price가 섞이는 일이 발생한다.

## @Configuration
AppConfig를 다시 살펴보면, memberRepository()를 두 곳에서 호출한다.

별개의 메소드에서 new를 호출하지만, 결론적으로 두 곳에서 같은 memberRepository()를 사용한다.

그 이유는 @Configuration을 사용했기 때문이다.

@Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고, 스프링 빈이 없으면
생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어진다.

# chapter6: 컴포넌트 스캔
XML을 통한 의존관계 주입은 번거롭고 누락하기 쉽다.

이를 위해 스프링은 컴포넌트 스캔이라는 기능을 제공한다.

@ComponentScan은 @Component가 붙은 모든 클래스를 스프링 빈으로 등록.

생성자에 @Autowired 를 지정하면, 스프링 컨테이너가 자동으로 해당 스프링 빈을 찾아서 주입.

## 빈 충돌
1. 자동 빈 등록 vs 자동 빈 등록
   + ConflictingBeanDefinitionException 예외 발생
2. 수동 빈 등록 vs 자동 빈 등록
   + 수동 빈 등록이 우선권
   + 최근 스프링 부트에서는 수동 빈 등록과 자동 빈 등록이 충돌나면 오류가 발생

# chapter7: 의존관계 자동 주입

## 여러가지 방법
1. 생성자 주입
   + 생성자를 이용해 초기 1회만.
   + 불변, 필수 의존관계에 사용.
   + 생성자가 1개만 있으면 '@Autowired'를 생략해도 자동 주입.
2. 수정자 주입(setter 주입)
   + 선택, 변경이 있는 의존관계에 사용.
3. 필드 주입
4. 일반 메서드 주입


## 생성자 주입을 많이 쓰는 이유
+ 대부분의 의존관계는 한 번 설정하면 변경할 일이 없다.
+ setter를 사용하는 경우, setter를 public으로 설정해야한다.
+ 수정할 일이 없는데 굳이 public으로 열어두는 것은 좋지 않다.
+ final 키워드를 통해 컴파일 시점에 오류를 잡을 수 있다.

## 조회 대상 빈이 여러개일 때
1. '@Autowired' 필드명 매칭
    + 필드 명을 빈 이름으로 설정.
   ```java
    @Autowired
    private DiscountPolicy rateDiscountPolicy;
    ```
2. '@Qualifier'
    + 빈 등록시 '@Qualifier'를 사용.
    ```java
    @Component
    @Qualifier("mainDiscountPolicy")
    public class RateDiscountPolicy implements DiscountPolicy {}
    ```
3. '@Primary'
    + '@Primary'가 붙은 빈이 우선.
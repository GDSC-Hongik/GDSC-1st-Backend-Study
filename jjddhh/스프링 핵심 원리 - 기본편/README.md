# 스프링 핵심 원리 - 기본편

<br/>

## 섹션 1. 객체 지향 설계와 스프링

#### - 스프링의 핵심

```
• 스프링은 자바 언어 기반의 프레임워크

• 자바 언어의 가장 큰 특징 - 객체 지향 언어

• 스프링은 객체 지향 언어가 가진 강력한 특징을 살려내는 프레임워크

• 스프링은 좋은 객체 지향 애플리케이션을 개발할 수 있게 도와주는 프레임워크
```

<br/>

#### - 좋은객체 지향 설계의 5가지 원칙(SOLID)

```
OCP 개방-폐쇄 원칙의 문제점

• 구현 객체를 변경하려면 클라이언트 코드를 변경해야 한다.

• 분명 다형성을 사용했지만 OCP 원칙을 지킬 수 없다.


OCP를 지키기 위해서는 객체를 생성하고, 연관관계를 맺어주는 별도의 조립, 설정자가 필요하다 
=> 스프링 컨테이너가 이러한 역할들을 해준다. 한마디로 스프링은 스프링 컨테이너를 사용해서 객체 지향적 설계를 할 수 있게 도와준다.
```

<br/>

## 섹션 2. 스프링 핵심 원리 이해1 - 예제 만들기

<br/>

## 섹션 3. 스프링 핵심 원리 이해2 - 객체 지향 원리 적용

```java
public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }

    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
```

<br/>

#### - IOC/DI 컨테이너

```
AppConfig 처럼 객체를 생성하고 관리하면서 의존관계를 연결해 주는 것을 IoC 컨테이너 또는 DI 컨테이너라 한다.
 
의존관계 주입에 초점을 맞추어 최근에는 주로 DI 컨테이너라 한다.

또는 어샘블러, 오브젝트 팩토리 등으로 불리기도 한다
```

<br/>

#### - 스프링 컨테이너
```
• ApplicationContext 를 '스프링 컨테이너'라 한다.

• 기존에는 개발자가 AppConfig 를 사용해서 직접 객체를 생성하고 DI를 했지만, 이제부터는 스프링 컨테이너를 통해서 사용한다.

• 스프링 컨테이너는 @Configuration 이 붙은 AppConfig 를 설정(구성) 정보로 사용한다. 여기서 @Bean
이라 적힌 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록한다. 이렇게 스프링 컨테이너에
등록된 객체를 '스프링 빈'이라 한다.

• 스프링 빈은 @Bean 이 붙은 메서드의 명을 스프링 빈의 이름으로 사용한다.

• 이전에는 개발자가 필요한 객체를 AppConfig 를 사용해서 직접 조회했지만, 이제부터는 스프링 컨테이너를 통해서 필요한 스프링 빈(객체)를 찾아야 한다. 
스프링 빈은 applicationContext.getBean() 메서드를 사용해서 찾을 수 있다.

• 기존에는 개발자가 직접 자바코드로 모든 것을 했다면 이제부터는 스프링 컨테이너에 객체를 스프링 빈으로 등록하고, 스프링 컨테이너에서 스프링 빈을 찾아서 사용하도록 변경되었다
```
> 코드가 약간 더 복잡해진 것 같은데, 스프링 컨테이너를 사용하면 어떤 장점이 있을까?

<br/>

## 섹션 4. 스프링 컨테이너와 스프링 빈
 
#### - BeanFactory 와 ApplicationContext
```
BeanFactory 는 주로 빈을 관리하고 검색하는 기능을 제공해주고,

ApplicationContext 는 Beanfactory 의 서브 클래스로써 수 많은 부가기능(국제화, 환경 변수, 이벤트, 리소스 조회 등)을 더 제공해준다.
```

<br/>

#### - 스프링 빈 설정 메타 정보 - BeanDefinition
```
스프링 컨테이너 입장에서는 Bean을 등록하기 위해 빈 설정 메타 정보인 BeandDefinition을 알면 된다.

따라서 annotation, xml 등과 같은 다양한 방법에서 BeanDefinition만 만들어 넘겨준다면, 스프링 컨테이너에 Bean 등록을 할 수 있다 
```

<br/>

#### - Annotation Bean 등록 vs Xml Bean 등록
```
Xml
- 스프링 컨테이너에 바로 Bean 등록이 된다.

Annotation
- factoryBean 을 통해서 스프링 컨테이너에 Bean 등록이 된다. 

```

<br/>

## 섹션 5. 싱글톤 컨테이너

#### - 싱글톤 패턴 문제점
```
• 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.

• 의존관계상 클라이언트가 구체 클래스에 의존한다. DIP를 위반한다.

• 클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다.

• 테스트하기 어렵다.

• 내부 속성을 변경하거나 초기화 하기 어렵다.

• private 생성자로 자식 클래스를 만들기 어렵다.

• 결론적으로 유연성이 떨어진다.

• 안티패턴으로 불리기도 한다
```
> => 스프링 컨테이너는 싱글톤 패턴의 문제점을 전부 해결하면서, 객체 인스턴스를 싱글톤으로 관리하게 해준다.

<br/>

#### - @Configuration
```
@Configuration 설정 시, 바이트코드를 조작하는 CGLIB 기술을 사용해서 싱글톤을 보장한다.

@Bean 만 사용해도 스프링 빈으로는 등록되지만, 싱글톤을 보장하지 않는다
```

## 섹션 6. 컴포넌트 스캔

<br/>

## 섹션 7. 의존관계 자동 주입

<br/>

## 섹션 8. 빈 생명주기 콜백

<br/>

## 섹션 9. 빈 스코프
## Aspect-Oriented Programing

관점 지향 프로그래밍

우아한 [테코톡](https://youtu.be/Hm0w_9ngDpM) 제이의 Spring Aop 편을 보고 정리한 글입니당!

### AOP에 대하여

서비스 레이어에서 필요한 내용은 비즈니스 로직만 있으면 됨!

핵심 기능만 수행할 수 있으면 가눙!
![횡단관심사](https://user-images.githubusercontent.com/13329304/193819520-b1844856-9cf4-4aad-8f4e-4c6a0092776a.jpg)

- 인프라 로직

  그외에 시간을 잰다던지
  권한을 체크한다던지
  트랜잭션을 건다던지
  로깅을 한다던지
  부가기능이있음

  비즈니스 로직과 함께 있으면 비즈니스 로직을 이해하기 어려워짐
  중복된 코드 때문에

- cores-cutting concern 횡단 관심사

  유명한 김밥썰기

- OOP vs AOP ?

aspect-oriented programmin complements object-oriented programming by providing, another way of thinking
about program structure

> 보완을 하는 거임

## AOP

- Target

어떤 대상에 부가 기능을 부여할 것인가

- Advice

어떤 부가 기능 을 부여할 것인가 , Before , AfterReturning, After Throwing , After , Around

- Join point

어디에 적용할 것인가 ? 메서드 , 필드 , 객체, 생성자 등
스프링은 메서드만 지원

- Point cut

실제 Advice가 적용될 지점 , Spring AOP 에서는 advice가 적용될 메서드를 선정

```java
// 스프링이 지원해주는 aop 는 메소드 레벨에서만 적용이됨!
// 어라운드는 함수 앞뒤로.
@Around("excution(* org.woowacourse.aoppractice.service.AuthServiceImpl.*(..))")
// 또는 PerformaceCheck 라는 어노테이션이 달린 메서드에게 적용
@Around("@annotation(PerformaceCheck)")

public Object stopWatch(ProceedingJoinPoint joinPoint) throws Throwable {
 stopWatch stopWatch = new StopWatch();
 try{
   stopWatch.start();
   return joinPoint.proceed();
 }finally{
   stopWatch.stop();
   log.info("request spent {}ms" , stopWatch.getLastTaskTimeMillis());
 }
}

```

### AOP 의 구현 방법

- 컴파일

java -> class 로 컴파일 하는 시점에 aop 적용

- 클래스 로드시

class -> class Loder가 메모리에 클래스를 올릴 때 적용

- 프록시 패턴

스프링 aop 에서 사용하는 방식이 프록시 패턴
그냥 프록시 일일히 만들어주면 그만인데
스프링이 프록시 패턴을 어느 때에 이용을 하냐!

빈에 객체를 등록할 때 적용을 해버림

> ioc, di 기반 --> 빈 후처리기가 있음!!!

### 빈 후 처리기

EnhancerBySpringCGLIB$$
![프록시예시](https://user-images.githubusercontent.com/13329304/193819516-7b8e6679-080a-403d-8a5c-e5f571998e99.jpg)

```java
// 소스

@Slf4j
@Configuration
static class BeanPostProcessorConfig {
    @Bean(name = "beanA")
    public A a() {
        return new A();
    }

    @Bean
    public AToBPostProcessor helloPostProcessor() {
        return new AToBPostProcessor();
    }
}

@Slf4j
static class A {
    public void helloA() {
        log.info("hello A");
    }
}

@Slf4j
static class B {
    public void helloB() {
        log.info("hello B");
    }
}
// 빈 후처리기

@Slf4j
static class AToBPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("beanName={} bean={}", beanName, bean);
        if (bean instanceof A) {
            return new B();
        }
        return bean;
    }
}
// 테스트코드

@Test
void basicConfig() {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanPostProcessorConfig.class);

    //beanA 이름으로 B 객체가 빈으로 등록된다.
    B b = applicationContext.getBean("beanA", B.class);
    b.helloB();

    //A는 빈으로 등록되지 않는다.
    B bean = applicationContext.getBean(B.class);
}

출처 : https://gong-story.tistory.com/25
```

[빈 후 처리기](https://gong-story.tistory.com/25)

> 일일히 만들기 힘듬 -> advice만 구현하면 가능 ( 어떤 부가기능을 부여할건지 , 어디에 적용시킬건지 ) 해서 어드바이저 라고 불림

[JDK 다이나믹 프록시 , CGLIB 프록시](https://www.youtube.com/watch?v=RHxTV7qFV7M&list=PLgXGHBqgT2TvpJ_p9L_yZKPifgdBOzdVH&index=215)

### spring AOP AspectJ

![aopaspectj](https://user-images.githubusercontent.com/13329304/193819507-9a1f364c-9092-4930-bcd0-3e9781dd5c9e.jpg)

weaving --> aop를 끼어넣는 타임.!

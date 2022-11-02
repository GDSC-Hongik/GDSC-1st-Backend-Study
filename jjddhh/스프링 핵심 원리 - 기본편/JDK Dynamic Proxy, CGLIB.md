## 프록시

- 대리자로써 클라이언트 코드의 변경없이 아래와 같은 기능들이 가능하다

```
프록시의 주요 기능

* 접근 제어
    1) 권한에 따른 접근 차단
    2) 캐싱
    3) 지연 로딩
    
* 부가 기능 추가
    1) 원래 서버가 제공하는 기능에 더해서 부가 기능을 수행 (ex. 로그)
        
```

<br/>

## 프록시 패턴과 데코레이터 패턴

- 둘다 프록시를 사용하는 방법이지만 GOF 디자인 패턴에서는 이 둘을 의도(intent)에 따라서 프록시 패턴과 데코레이터 패턴으로 구분한다.

```
* 프록시 패턴: 접근 제어가 목적
* 데코레이터 패턴: 새로운 기능 추가가 목적

- 정리
프록시를 사용하고 해당 프록시가 접근 제어가 목적이라면 프록시 패턴이고, 
새로운 기능을 추가하는 것이 목적이라면 데코레이터 패턴이 된다.
```

<br/>

## 프록시 구현 방법과 단점

- 프록시 2가지 구현 방법
    1) 클래스 기반 프록시
    2) 인터페이스 기반 프록시

<br/>

- 두 가지 방법의 공통적인 단점

```  
적용하려는 클래스마다 프록시 클래스를 만들어야 한다.

만약 적용해야 하는 대상 클래스가 100개라면 프록시 클래스도 100개 작성해야 한다.
```

![proxy](https://user-images.githubusercontent.com/92728780/198859162-99e28a94-c990-44c6-9a8f-cca88d914897.JPG)


<br/>

## 프록시 단점을 극복하기 위한 두 가지 기술. JDK 동적 프록시, CGLIB

적용 클래스마다 프록시 클래스를 만들어줘야 하는 번거로움을 없애주는 두 가지 기술이 있다. 바로 JDK 동적 프록시와 CGLIB 이다

<br/>

### JDK 동적 프록시

동적 프록시 기술을 사용하면 개발자가 직접 프록시 클래스를 만들지 않아도 된다. 이름 그대로 프록시 객체를 동적으로 런타임에 개발자 대신 만들어준다. 그리고 동적 프록시에 원하는 실행 로직을 지정할 수 있다

#### JDK 동적 프록시를 이용한 구현 방법.

JDK 동적 프록시에 적용할 로직은 InvocationHandler 인터페이스를 구현해서 작성하면 된다.

* JDK 동적 프록시를 사용하여 부가할 추가 로직 작성

```java
public interface InvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;
}

---------------------------------------------------------------------

public class TimeInvocationHandler implements InvocationHandler {
    private final Object target;

    public TimeInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws
            Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

        // 원래 호출하고자 했던 메서드 호출 부분 (Reflection 사용)
        Object result = method.invoke(target, args);

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeProxy 종료 resultTime={}", resultTime);
        return result;
    }
}
```

<br/>

* JDK 동적 프록시로 프록시 생성 후 사용

```java
public class JdkDynamicProxyTest {

    @Test
    void dynamicA() {
        AInterface target = new AImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);
        // 프록시 생성
        AInterface proxy = (AInterface)
                Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]
                        {AInterface.class}, handler);
        proxy.call();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
    }

    @Test
    void dynamicB() {
        BInterface target = new BImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);
        // 프록시 생성
        BInterface proxy = (BInterface)
                Proxy.newProxyInstance(BInterface.class.getClassLoader(), new Class[]
                        {BInterface.class}, handler);
        proxy.call();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
    }
}
```

JDK 동적 프록시 기술 덕분에 적용 대상 만큼 프록시 객체를 만들지 않아도 된다. 그리고 같은 부가 기능 로직을 한번만 개발해서 공통으로 적용할 수 있다. 만약 적용 대상이 100개여도 동적 프록시를 통해서
생성하고, 각각 필요한 InvocationHandler 만 만들어서 넣어주면 된다

<br/>

## Reflection 이란?

```
* 리플렉션은 구체적인 클래스 타입을 알지 못해도 그 클래스의 메소드와 타입 그리고 변수들을 접근할 수 있도록 해주는 자바 API 이다.

* 본인이 작성하는 코드인데 구체적인 클래스 타입을 모르는 경우
- Framework, Hibernate, ...
```

* JDK 동적 프록시에서 작성한 프록시를 보면 어떤 클래스의 메서드를 실행할 지 추상화 되어있다. 이 부분이 Reflection 을 사용한 경우이다.

### CGLIB

* CGLIB는 바이트코드를 조작해서 동적으로 클래스를 생성하는 기술을 제공하는 라이브러리이다.<br/>
* CGLIB를 사용하면 인터페이스가 없어도 구체 클래스만 가지고 동적 프록시를 만들어낼 수 있다.<br/>
* CGLIB는 원래는 외부 라이브러리인데, 스프링 프레임워크가 스프링 내부 소스 코드에 포함했다. 따라서 스프링을 사용한다면 별도의 외부 라이브러리를 추가하지 않아도 사용할 수 있다.

#### CGLIB을 이용한 구현 방법.

JDK 동적 프록시에서 실행 로직을 위해 InvocationHandler 를 제공했듯이, CGLIB는 MethodInterceptor 를 제공한다.

* CGLIB 프록시의 실행 로직을 정의한다

```java
public interface MethodInterceptor extends Callback {
    Object intercept(Object obj, Method method, Object[] args, MethodProxy
            proxy) throws Throwable;
}

---------------------------------------------------------------------

public class TimeMethodInterceptor implements MethodInterceptor {
    private final Object target;

    public TimeMethodInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args,
                            MethodProxy proxy) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();
        Object result = proxy.invoke(target, args);
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeProxy 종료 resultTime={}", resultTime);
        return result;
    }
}
```

<br/>

* 프록시 생성

```java
public class CglibTest {
    @Test
    void cglib() {
        ConcreteService target = new ConcreteService();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteService.class);
        enhancer.setCallback(new TimeMethodInterceptor(target));
        ConcreteService proxy = (ConcreteService) enhancer.create();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
        proxy.call();
    }
}
```

<br/>

> 남은 문제
> 
> 현재로써는 두 기술을 함께 사용하여 부가 기능을 제공하기 위해서는, 
> 클래스의 인터페이스 구현 유무에 따라 JDK 동적 프록시가 제공하는 InvocationHandler 와 CGLIB가 제공하는 MethodInterceptor 를 각각 만들어 줘야 한다.

<br/>

## 프록시 팩토리

스프링은 위에서 말한 문제를 해결해주는 '프록시 팩토리'를 제공한다.<br/>
개발자는 InvocationHandler 나 MethodInterceptor 를 신경쓰지 않고, 'Advice' 만 만들면 된다.
> JVM(프록시 팩토리) 을 통해 One Source(Advice) Multi Use(JDK 동적 프록시, CGLIB) 를 달성하는 경우를 생각해보자.

![advice](https://user-images.githubusercontent.com/92728780/198859098-ded28820-d23c-44f7-882a-f5303ed0308b.JPG)


* Adivce

```java
public class TimeAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();
        Object result = invocation.proceed();
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeProxy 종료 resultTime={}ms", resultTime);
        return result;
    }
}
```

<br/>

* 프록시 팩토리를 통한 프록시 생성

```java
public class ProxyFactoryTest {
    @Test
    @DisplayName("인터페이스가 있으면 JDK 동적 프록시 사용")
    void interfaceProxy() {
        ServiceInterface target = new ServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
        proxy.save();
        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
        assertThat(AopUtils.isCglibProxy(proxy)).isFalse();
    }

    @Test
    @DisplayName("구체 클래스만 있으면 CGLIB 사용")
    void concreteProxy() {
        ConcreteService target = new ConcreteService();

        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());

        ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
        proxy.call();
        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }
}

```

## 여기까지의 의문

지금까지 프록시가 무엇인지, 어떻게 반복 작업을 하지 않으며 종류(JDK 동적 프록시, CGLIB)에 상관없이 프록시를 생성할 수 있는지(프록시 팩토리) 에 대해 알아보았다. 여기까지 왔을 때, 두 가지 이해가
안되는 부분이 있다.

1) 스프링에서는 AOP 를 사용하여 로그를 기록하는 등의 작업을 하였는데, 우리는 방금 위에서 살펴본 프록시 생성 코드를 작성한 기억이 없다. 스프링에서는 어떻게 자동으로 위와 같은 작업을 하여 프록시를 만들어내는
   것 일까?

2) 생성된 Bean 을 언제, 어떻게 프록시로 바꾸어 스프링 빈 저장소에 저장할까?

1 의 경우는 나중에 고민하도록 하고, 먼저 2의 의문에 대해 생각해보자.

우리가 원하는 pointcut 에 추가하고자 하는 advice 를 적용한 메서드를 호출하기 위해서는, 스프링 빈 저장소에 프록시가 저장되 있어야 한다. 수동으로 프록시를 만들어 넣어준다면 문제가 없지만,
Component Scan 을 통해 빈 등록을 하려 할 때는 문제가 생긴다. 생성된 빈에 프록시를 적용할 수 없기 때문이다. 이러한 문제를 해결할 수 있는 기술이 '빈 후처리기' 이다.

## 빈 후처리기

이름 그대로 빈을 생성하여 빈 저장소에 등록하기 전, 조작을 가능하게 하는 기술이다. <br/>
빈 후처리기를 사용하여 생성된 빈으로 프록시를 생성하여, 프록시를 빈 저장소에 등록할 수 있다.

![빈후처리기](https://user-images.githubusercontent.com/92728780/198859123-1fabfbc6-9dd4-4ae7-8b44-5611063b27b3.JPG)

* 빈 후처리기 적용 예
```java
public class PackageLogTraceProxyPostProcessor implements BeanPostProcessor {
    private final String basePackage;
    private final Advisor advisor;

    public PackageLogTraceProxyPostProcessor(String basePackage, Advisor
            advisor) {
        this.basePackage = basePackage;
        this.advisor = advisor;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws
            BeansException {
        log.info("param beanName={} bean={}", beanName, bean.getClass());
        //프록시 적용 대상 여부 체크
        //프록시 적용 대상이 아니면 원본을 그대로 반환
        String packageName = bean.getClass().getPackageName();
        if (!packageName.startsWith(basePackage)) {
            return bean;
        }
        //프록시 대상이면 프록시를 만들어서 반환
        ProxyFactory proxyFactory = new ProxyFactory(bean);
        proxyFactory.addAdvisor(advisor);
        Object proxy = proxyFactory.getProxy();
        log.info("create proxy: target={} proxy={}", bean.getClass(),
                proxy.getClass());
        return proxy;
    }
}
```

<br/>
스프링에서는 AnnotationAwareAspectJAutoProxyCreator 라는 빈 후처리기를 스프링 빈에 자동으로 등록해주고,
이 빈 후처리기는 스프링 빈으로 등록된 Advisor 들을 자동으로 찾아서 프록시가 필요한 곳에 자동으로 프록시를 적용해준다

![자동 프록시 생성기](https://user-images.githubusercontent.com/92728780/198859120-3fb37ad2-74d6-409f-a4d7-076f62cb49e0.JPG)

<br/>
빈 후처리기를 통해 

1. 너무 많은 설정 - 프록시를 적용하고자 하는 클래스마다 관련 설정을 해주어야 함
2. 컴포넌트 스캔 사용 시, 프록시 적용 

두 가지의 문제를 해결 할 수 있었다.

<br/>

참조:
[김영한 - 스프링 핵심 원리 고급편](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%ED%95%B5%EC%8B%AC-%EC%9B%90%EB%A6%AC-%EA%B3%A0%EA%B8%89%ED%8E%B8/)


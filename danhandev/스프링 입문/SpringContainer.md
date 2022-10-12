## DI

DI는 외부에서 두 객체 간의 관계를 결정해주는 디자인 패턴이다. 인터페이스를 사이에 둬어 클래스 레벨에서는 의존관계가 고정되지 않도록 하고 런타임 시에 관계를 동적으로 주입하여 유연성을 확보하고 결합도를 낮출 수 있게 해준다.

하나의 객체 A가 다른 객체 B를 의존(사용)해야 할 때, A의 코드 내부에서 B를 만드는 것이 아니라, 외부에서 B를 만들고 A 내부로 주입하는 것이다.

## IoC

IoC는 제어권 역전으로 개발자가 프로그램의 흐름을 제어하는 것이 아니라 프레임워크가 프로그램의 흐름을 주도하는 것을 의미한다. 쉽게 말해 개발자가 자바를 통해 생성하고 호출했던 작업들을 설정 파일을 통해 스스로 생성하고 호출하도록 만드는 것이다.

일반적으론 `객체 생성(A) → 내부에서 의존성 객체 생성(B) → 의존성 객체 (B)의 메서드 호출` 순서이지만 IoC가 적용된 경우 `객체 생성(A,B) → 의존성 객체 주입(B) → 의존성 객체(B)의 메서드 호출`로 진행된다.

## Spring Container

스프링 컨테이너는 스프링 프레임워크에서 사용되는 의존성 객체(Bean)을 생성하고 필요한 곳에 주입한다. Bean의 생애 주기를 관리하며, 추가적인 기능을 제공하는 역할을 한다. 개발자는 new 연산자, 인터페이스 호출, 팩토리 호출 방식으로 객체를 생성하고 소멸시킬 수 있는데, 스프링 컨테이너가 이 역할을 대신한다. 스프링 컨테이너의 종류는 BeanFactory와 ApplicationContext 2가지 있다.

![image](https://user-images.githubusercontent.com/78093844/195335689-1107e298-fe89-4012-bc20-ea4fcdec6586.png)

### BeanFactory

BeanFactory는 단순한 형태의 스프링 컨테이너로 Bean 객체를 등록, 생성, 조회, 반환하는 기본적인 작업만 수행한다. BeanFactory 계열의 인터페이스만 구현한 클래스는 단순히 컨테이너에서 객체를 생성하고 DI를 처리해주는 기능만을 제공한다. 팩토리 디자인 패턴을 구현한 것으로 빈을 생성하고 분배하는 책임을 진다.

BeanFactory는 Bean의 정의는 즉시 로딩하는 반면, Bean 자체가 필요하게 되기 전까지는 인스턴스화를 하지 않는다. getBean() 메소드가 호출될 때 Bean이 인스턴스화된다.

### ApplicationContext

ApplicationContext는 BeanFactory의 확장판으로, BeanFactory의 Bean을 관리하는 기능을 상속받아서 제공한다. Transaction 관리, 메시지 기반 다국어 처리, AOP 적용과 같은 부가적인 기능들을 제공한다. 일반적으로 스프링 컨테이너라고 하면 ApplicationContext를 뜻한다.

부가기능

- EnvironmentCapable : 프로파일(Profile)을 설정하고 소스 설정 및 프로퍼티 값 제공.
- MessageSource : 메세지 설정 파일을 모아서 각 국가마다 로컬라이징해 각 지역에 맞춤 메시지 제공.
- ApplicationEventPublisher : 이벤트를 발행하고 구독하는 모델을 편리하게 지원
- ResourceLoader : 파일, 클래스패스, 외부 등에서 리소스를 편리하게 조회

  [https://kyun-s-world.gitbook.io/nowstart/spring/springframeworkcore/2-applicationcontext](https://kyun-s-world.gitbook.io/nowstart/spring/springframeworkcore/2-applicationcontext)


### 차이점

BeanFactory는 Bean 객체가 실제로 요구되는 시점에 이를 생성하는 Lazy Loading이 특징이다. ApplicationContext는 BeanFacotory와 달리 생성되는 시점에 Bean을 모두 생성하는 Pre Loading 방식을 사용한다. 컨테이너가 구동되는 시점에 모든 Bean을 미리 로드하기 때문에 애플리케이션 가동 후에는 Bean을 지연 없이 받을 수 있다.

### **ApplicationContext의 종류**

- ClassPathXmlApplicationContext : ClassPath에 위치한 xml 파일을 읽어 설정 정보를 로딩, root로부터 경로를 지정

    ```java
    ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");
    ```

- FileSystemXmlApplicationContext : 파일 경로로 지정된 곳의 xml을 읽어 설정 정보를 로딩

    ```java
    ApplicationContext context = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/spring/spring-beans.xml");
    ```


- GenericApplicationContext
    - 가장 일반적인 구현 클래스로써 실무에서 사용될 모든 기능을 갖춤.
    - 외부에 있는 빈 설정 메타정보를 BeanDefinitionReader를 통해 읽음.
    - JUnit을 사용할 때 종종 사용됨.

    ```java
    GenericApplicationContext gac=new GenericApplicationContext();
    XmlBeanDefinitionReader xbdr=new XmlBeanDefinitionReader(gac);
    xbdr.loadBeanDefinitions("classpath:root-context.xml");
    
    Hello hello = gac.getBean("hello",Hello.class);
    ```

- GenericXmlApplicationContext
    - 파일시스템이나 클래스 경로에 있는 Xml 파일을 설정 정보로 사용하는 스프링 컨테이너.
    - GenericApplicationContext에 XmlBeanDefinitionReader를 내장.

    ```java
    GenericXmlApplicationContext context=new GenericXmlApplicationContext("classpath:applicationContext.xml");
     
    A aClass=context.getBean("aClass");
    ```

- WebApplicationContext
    - 가장 많이 사용되는 ApplicationContext
    - 웹 기반의 스프링 애플리케이션을 개발할 때 사용하는 컨테이너
    - 웹 어플리케이션에 위치한 곳에서 xml파일을 읽어 설정 정보를 로딩
    - XmlWebApplicationContext, AnnotationConfigWebApplicationContext

[https://m.blog.naver.com/todoskr/220861944737](https://m.blog.naver.com/todoskr/220861944737)
[https://mangkyu.tistory.com/198](https://mangkyu.tistory.com/198)
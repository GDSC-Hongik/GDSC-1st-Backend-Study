## Spring Bean


> 💡 핵심 포인트
>
> **싱글톤(singleton) 스코프** vs. **프로토타입(prototype) 스코프**



## 1. 싱글톤 스코프 vs. 프로토타입 스코프

### ✔️ 싱글톤 스코프의 빈 요청

![Untitled](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F14778de1-b48a-4a56-939c-a87c415e1520%2FUntitled.png?table=block&id=6713db9a-c076-4944-ba25-68971f9bb610&spaceId=9d8417d1-3ee9-4dcc-8330-1ff1af8f3951&width=2000&userId=8d73ef51-8e98-4687-b671-90df60216d71&cache=v2)

> 싱글톤 스코프의 빈을 요청하면 **모두 동일한 스프링 빈**을 반환한다
>

### ✔️ 프로토타입 스코프의 빈 요청

![Untitled](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fa619b837-5ca1-4aba-96ab-e5cd9d7f20af%2FUntitled.png?table=block&id=4f4ecf9e-182b-4aec-9161-16ce749103df&spaceId=9d8417d1-3ee9-4dcc-8330-1ff1af8f3951&width=2000&userId=8d73ef51-8e98-4687-b671-90df60216d71&cache=v2)

> **프로토타입 스코프의 스프링 빈 요청시**   
> 
> 1️⃣ 클라이언트에서 프로토타입 스코프의 스프링 빈을 스프링 컨테이너에 요청   
> 2️⃣ 스프링 컨테이너는 이 시점에서 프로토타입 빈을 생성하고, 의존관계 주입(DI)   
> 3️⃣ 생성한 프로토타입 빈을 클라이언트에 반환   
>
- 여기서 프로토타입은 싱글톤 타입의 스프링 빈과는 다르게 빈 생성, 의존관계 주입, 초기화까지만 진행한다. 그렇기에 그 이후 스프링 빈을 클라이언트에 반환한 이후로는 관리하지 않기에 소멸 메서드같은 것은 모두 클라이언트에서 자체적으로 관리해야 한다


> 💡 정리
>
> ✅ 싱글톤은 스프링 컨테이너와 생명주기를 같이하지만, 프로토타입 스프링 빈은 생명주기를 달리한다   
> ✅ 싱글톤 스프링 빈은 매번 스프링 컨테이너에서 **동일한 인스턴스를 반환**하지만, 프로토타입 스프링 빈은 스프링 컨테이너에 **요청할때마다 새로운 스프링 빈을 생성 후 의존관계까지 주입 및 초기화 진행 후 반환**한다   
> ✅ 프로토타입 스프링 빈은 소멸 메서드가 호출되지 않는다   
> ✅ 클라이언트가 프로토타입 스프링 빈은 직접 관리해야 한다 (소멸 메서드도 직접 호출해야 한다)   


- 싱글톤 예시

```java
// Student.java

package com.springboot.dibasics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton") // 싱글톤 일때는 생략 가능함
 public class Student {

    private int id;
    private String name;

    public Student() {
        System.out.println("Student Init");
    }

}
```

```java
// DlbasicsApplication.java

package com.springboot.dibasics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DIbasicsApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(DIbasicsApplication.class, args);
        Student s1 = ctx.getBean(Student.class);
        Student s2 = ctx.getBean(Student.class);

        if(s1==s2) System.out.println("s1 == s2");
        if(s1!=s2) System.out.println("s1 != s2");

}
```

```shell
/Library/Java/JavaVirtualMachines/jdk-11.0.16.jdk/Contents/Home/bin/java -XX:TieredStopAtLevel=1 -noverify -Dspring.output.ansi.enabled=always -Dcom.sun.management.jmxremote -Dspring.jmx.enabled=true -Dspring.liveBeansView.mbeanDomain -Dspring.application.admin.enabled=true -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=52636:/Applications/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/sujin/Downloads/DIbasics/target/classes:/Users/sujin/.m2/repository/org/springframework/boot/spring-boot-starter/2.7.2/spring-boot-starter-2.7.2.jar:/Users/sujin/.m2/repository/org/springframework/boot/spring-boot/2.7.2/spring-boot-2.7.2.jar:/Users/sujin/.m2/repository/org/springframework/spring-context/5.3.22/spring-context-5.3.22.jar:/Users/sujin/.m2/repository/org/springframework/spring-aop/5.3.22/spring-aop-5.3.22.jar:/Users/sujin/.m2/repository/org/springframework/spring-beans/5.3.22/spring-beans-5.3.22.jar:/Users/sujin/.m2/repository/org/springframework/spring-expression/5.3.22/spring-expression-5.3.22.jar:/Users/sujin/.m2/repository/org/springframework/boot/spring-boot-autoconfigure/2.7.2/spring-boot-autoconfigure-2.7.2.jar:/Users/sujin/.m2/repository/org/springframework/boot/spring-boot-starter-logging/2.7.2/spring-boot-starter-logging-2.7.2.jar:/Users/sujin/.m2/repository/ch/qos/logback/logback-classic/1.2.11/logback-classic-1.2.11.jar:/Users/sujin/.m2/repository/ch/qos/logback/logback-core/1.2.11/logback-core-1.2.11.jar:/Users/sujin/.m2/repository/org/apache/logging/log4j/log4j-to-slf4j/2.17.2/log4j-to-slf4j-2.17.2.jar:/Users/sujin/.m2/repository/org/apache/logging/log4j/log4j-api/2.17.2/log4j-api-2.17.2.jar:/Users/sujin/.m2/repository/org/slf4j/jul-to-slf4j/1.7.36/jul-to-slf4j-1.7.36.jar:/Users/sujin/.m2/repository/jakarta/annotation/jakarta.annotation-api/1.3.5/jakarta.annotation-api-1.3.5.jar:/Users/sujin/.m2/repository/org/springframework/spring-core/5.3.22/spring-core-5.3.22.jar:/Users/sujin/.m2/repository/org/springframework/spring-jcl/5.3.22/spring-jcl-5.3.22.jar:/Users/sujin/.m2/repository/org/yaml/snakeyaml/1.30/snakeyaml-1.30.jar:/Users/sujin/.m2/repository/org/slf4j/slf4j-api/1.7.36/slf4j-api-1.7.36.jar com.springboot.dibasics.DIbasicsApplication

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.7.2)

2022-10-16 00:35:37.439  INFO 78114 --- [           main] c.s.dibasics.DIbasicsApplication         : Starting DIbasicsApplication using Java 11.0.16 on isujin-ui-MacBookPro.local with PID 78114 (/Users/sujin/Downloads/DIbasics/target/classes started by sujin in /Users/sujin/Downloads/DIbasics)
2022-10-16 00:35:37.440  INFO 78114 --- [           main] c.s.dibasics.DIbasicsApplication         : No active profile set, falling back to 1 default profile: "default"
Student Init
2022-10-16 00:35:37.620  INFO 78114 --- [           main] c.s.dibasics.DIbasicsApplication         : Started DIbasicsApplication in 0.297 seconds (JVM running for 0.678)
s1 == s2

Process finished with exit code 0
```

* 싱글톤 분석
  * 스프링 컨테이너 시작 ~ 종료시점까지 유지되는 기본적인 범위의 스코프
  * 일체의 Student 오브젝트 생성 없이도 Spring Bean으로 등록될 때 Spring IoC Container에 등재되면서 생성자 호출되는 것 확인
  * 생성자 호출은 1번만 일어남 ⭐️
  * @Component 는 singleton 즉 오직 1개의 오브젝트만 생성하는 것이 Default
  * Singleton이므로 getBean(Student.class)를 두 번 수행해도 singleton 1개만 생성
  * 스프링 컨테이너 종료 시 소멸 메서드도 자동으로 실행됨


---

- 프로토타입 예시

```java
// Student.java

package com.springboot.dibasics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")  // same as @Scope("prototype") -> 이건 필요할때마다 새롭게 만들어서 쓰기
public class Student {

    private int id;
    private String name;

    public Student() {
        System.out.println("Student Init");
    }

    public void hi(){
        System.out.println("hi");
    }

}
```

```java
// Phone.java

package com.springboot.dibasics;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Phone {
    private String number;

    public Phone() {
        System.out.println("Phone Init");
    }

    public void ringing() {
        System.out.println("Ring, Ring, Ringing");
    }
}
```

```java
// DlbasicsApplication.java

package com.springboot.dibasics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DIbasicsApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(DIbasicsApplication.class, args);
        Student s1 = ctx.getBean(Student.class); // 이 때에 스프링 컨테이너에 스프링 빈으로 생성됨 // 이때에 생성자 호출
        s1.hi();

        Student s2 = ctx.getBean(Student.class); // 이 때에 스프링 컨테이너에 스프링 빈으로 생성됨 // 이때에 생성자 호출
        s2.hi();

        if(s1==s2) System.out.println("s1 == s2");
        if(s1!=s2) System.out.println("s1 != s2");

        Phone p1 = ctx.getBean(Phone.class); // 이 때에 스프링 컨테이너에 스프링 빈으로 생성됨 // 이때에 생성자 호출
        p1.ringing();

        Phone p2 = ctx.getBean(Phone.class); // 이 때에 스프링 컨테이너에 스프링 빈으로 생성됨 // 이때에 생성자 호출
        p2.ringing();

        if(p1==p2) System.out.println("p1 == p2");
        if(p1!=p2) System.out.println("p1 != p2");

    }
}
```

```shell
/Library/Java/JavaVirtualMachines/jdk-11.0.16.jdk/Contents/Home/bin/java -XX:TieredStopAtLevel=1 -noverify -Dspring.output.ansi.enabled=always -Dcom.sun.management.jmxremote -Dspring.jmx.enabled=true -Dspring.liveBeansView.mbeanDomain -Dspring.application.admin.enabled=true -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=52759:/Applications/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/sujin/Downloads/DIbasics/target/classes:/Users/sujin/.m2/repository/org/springframework/boot/spring-boot-starter/2.7.2/spring-boot-starter-2.7.2.jar:/Users/sujin/.m2/repository/org/springframework/boot/spring-boot/2.7.2/spring-boot-2.7.2.jar:/Users/sujin/.m2/repository/org/springframework/spring-context/5.3.22/spring-context-5.3.22.jar:/Users/sujin/.m2/repository/org/springframework/spring-aop/5.3.22/spring-aop-5.3.22.jar:/Users/sujin/.m2/repository/org/springframework/spring-beans/5.3.22/spring-beans-5.3.22.jar:/Users/sujin/.m2/repository/org/springframework/spring-expression/5.3.22/spring-expression-5.3.22.jar:/Users/sujin/.m2/repository/org/springframework/boot/spring-boot-autoconfigure/2.7.2/spring-boot-autoconfigure-2.7.2.jar:/Users/sujin/.m2/repository/org/springframework/boot/spring-boot-starter-logging/2.7.2/spring-boot-starter-logging-2.7.2.jar:/Users/sujin/.m2/repository/ch/qos/logback/logback-classic/1.2.11/logback-classic-1.2.11.jar:/Users/sujin/.m2/repository/ch/qos/logback/logback-core/1.2.11/logback-core-1.2.11.jar:/Users/sujin/.m2/repository/org/apache/logging/log4j/log4j-to-slf4j/2.17.2/log4j-to-slf4j-2.17.2.jar:/Users/sujin/.m2/repository/org/apache/logging/log4j/log4j-api/2.17.2/log4j-api-2.17.2.jar:/Users/sujin/.m2/repository/org/slf4j/jul-to-slf4j/1.7.36/jul-to-slf4j-1.7.36.jar:/Users/sujin/.m2/repository/jakarta/annotation/jakarta.annotation-api/1.3.5/jakarta.annotation-api-1.3.5.jar:/Users/sujin/.m2/repository/org/springframework/spring-core/5.3.22/spring-core-5.3.22.jar:/Users/sujin/.m2/repository/org/springframework/spring-jcl/5.3.22/spring-jcl-5.3.22.jar:/Users/sujin/.m2/repository/org/yaml/snakeyaml/1.30/snakeyaml-1.30.jar:/Users/sujin/.m2/repository/org/slf4j/slf4j-api/1.7.36/slf4j-api-1.7.36.jar com.springboot.dibasics.DIbasicsApplication

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.7.2)

2022-10-16 01:00:04.994  INFO 82621 --- [           main] c.s.dibasics.DIbasicsApplication         : Starting DIbasicsApplication using Java 11.0.16 on isujin-ui-MacBookPro.local with PID 82621 (/Users/sujin/Downloads/DIbasics/target/classes started by sujin in /Users/sujin/Downloads/DIbasics)
2022-10-16 01:00:04.995  INFO 82621 --- [           main] c.s.dibasics.DIbasicsApplication         : No active profile set, falling back to 1 default profile: "default"
2022-10-16 01:00:05.169  INFO 82621 --- [           main] c.s.dibasics.DIbasicsApplication         : Started DIbasicsApplication in 0.295 seconds (JVM running for 0.672)
Student Init
hi
Student Init
hi
s1 != s2
Phone Init
Ring, Ring, Ringing
Phone Init
Ring, Ring, Ringing
p1 != p2

Process finished with exit code 0
```

* 프로토타입 분석
    * 스프링 빈의 생성과 의존관계 주입시점까지만 스프링 컨테이너에서 관리
      * ```@Scope(”prototype”)``` annotation은 Spring container에 default로 생성되는 것이 아니라 getBean(Student.class) 메소드를 실행할 때마다 Student.class Bean이 생성됨 → 이 때 만들어지므로 이 때에 생성자가 호출됨
    * 스프링 빈을 가져올때마다 싱글톤 타입 빈과 다르게 계속 생성되고 초기화 콜백 메서드도 매번 수행
    * 스프링 컨테이너가 종료되도 소멸 콜백메서드를 수행하지 않음

---

## 생성 패턴 - 싱글톤 패턴(Singleton Pattern)

## 1. 싱글톤 패턴?
* 생성 패턴 중 하나로, 인스턴스를 오직 한 개만 만들어서 제공하는 클래스가 필요한 경우에 사용하는 패턴

### 1-1) 정의
* 소프트웨어 디자인 패턴에서 싱글턴 패턴(Singleton pattern)을 따르는 클래스는, 생성자가 여러 차례 호출되더라도 실제로 생성되는 객체는 하나이고 최초 생성 이후에 호출된 생성자는 최초의 생성자가 생성한 객체를 리턴한다. 주로 공통된 객체를 여러 개 생성해서 사용하는 DBCP(DataBase Connection Pool)와 같은 상황에서 많이 사용된다고 한다.

> **"한마디로 객체를 메모리에 한 번만 올리고, 해당 메모리에 다시 접근한다"**


### 1-2) 사용 이유

시스템 런타임, 환경 세팅 관련 정보 등 인스턴스가 여러 개일때 문제가 발생하는 경우 등이 있는데,   
싱글톤 패턴을 사용함으로써 가져갈 수 이점은 다음과 같다   

* 메모리, 속도: 고정된 메모리 영역을 사용
* 데이터 공유: 인스턴스가 전역으로 사용
* 인스턴스가 **한 개만 존재하는 것을 보장**하고 싶은 경우


### 1-3) 싱글톤 패턴의 구현

* private constructor 선언
* static method 사용

싱글톤 패턴에서는 **생성자를 클래스 자체에서만 접근할 수 있어야 하기 때문에** private으로 접근 제어를 해야 한다   

>  인스턴스가 하나만 존재함을 보장해야 하기 때문에 **Single Thread**에서는 문제가 되지 않지만,   
>  **Multi Thread** 환경에서는 객체에 접근 시 초기화 관련한 문제가 발생할 수 있다

### 이를 해결하기 위한 방법은? 🤔
  * 이른 초기화 (Eager Initialization)
  * 늦은 초기화 (Lazy Initialization)
  * Lazy Initialization with Synchronized
  * 늦은 동기화, DCL (Double Checked Locking)

---


### ✔️ 1-4) 이른 초기화 (Eager Initialization)

> static 키워드를 통해 **클래스 로더가 초기화하는 시점**에 **정적 바인딩(Static Binding)**을 통해 해당 인스턴스를 메모리에 등록하기 때문에 **Thread-safe**!


```java
public class Singleton {
    private static final Singleton INSTANCE = new Singleton();
    
    private Singleton() {}
    
    public static Singleton getInstance() {
        return INSTANCE;
    }
}
```

* 장점: Thread-safe
* 단점: 미리 만들어두기 때문에 실제 해당 인스턴스를 사용하지 않으면 메모리 측면에서 손해

---

### ✔️ 1-5) 늦은 초기화 (Lazy Initialization)

> 인스턴스를 **실제 사용하는 시점에서 생성**하는 방법 - **동적 바인딩(Dynamic Binding)**

* 이른 초기화 방법보다 메모리 측면에서는 효율적
* 아래 getInstance() 는 **멀티 스레드 환경에서는 안전하지 ❌**


```java
public class Singleton {
    private static Singleton instance;
    private Singleton() { }
 
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

* 발생할 수 있는 문제 ? 🧐
  * 만약 두 Thread가 동시에 해당 인스턴스에 접근 시 인스턴스가 생성되어 있지 않은 것으로 보고,    
  중복으로 싱글톤 인스턴스를 생성할 수 있기 때문



* 장점: 사용 시점에 인스턴스를 생성하여 메모리를 효율적으로 사용
* 단점: Thread Safe 하지 않음

---

### ✔️  1-6) 늦은 동기화 문제의 해결 -> Lazy Initialization with Synchronized   



1-5) 의 늦은 동기화의 멀티 스레드 문제는 ```Synchronized``` 키워드를 사용해 동기화 문제를 해결할 수 있음   
* race condition이 발생할 수 있는 synchronized 블록으로 감싸기 (ex -> 메소드 안에서 동기화 처리)


```java
public class Singleton {
    private static Singleton instance;
    private Singleton() { }
 
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```


* 장점: 메모리를 효율적으로 사용 + Thread Safe
* 단점: 인스턴스 생성 여부와 상관없는 동기화 때문에 성능이 떨어짐
  * getInstance( )를 호출 시에 해당 인스턴스의 생성 여부와 상관없이 동기화 블록을 거쳐야 함
  * 동기화는 기본적으로 **Lock**을 거는 메커니즘을 사용하기 때문에 성능이 떨어짐

---

### ✔️ 1-6)의 방식을 개선 ->  늦은 동기화, DCL (Double Checked Locking)

위 동기화 블록 방식을 개선한 방식으로, **먼저 인스턴스의 생성 여부를 확인** 하는 방법 !

⭐️ 인스턴스가 생성되지 않은 경우에 동기화 처리를 하기 때문에, **효율적으로 동기화 블록을 만들 수 ⭕️**


```java
public class Singleton {
    private volatile static Singleton instance;
    private Singleton() { }
 
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if(instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}​
```


> ### volatile 키워드 ? 🧐
> volatile 변수를 사용하고 있지 않는 멀티 스레드 어플리케이션에서는 작업(Task)을 수행하는 동안 성능 향상을 위해 Main Memory에서 읽은 변수 값을 CPU Cache 에 저장하게 된다.    
> 만약에 멀티 스레드 환경에서 스레드가 변수 값을 읽어올 때 각각의 CPU Cache 에 저장된 값이 다르기 때문에 변수 값 불일치 문제가 발생하게 되는데,    
> volatile 키워드가 이런 문제를 해결할 수 ⭕️

-> 즉, volatile 변수는 Main Memory 에 값을 저장하고 읽어오기 때문에(read and write) 변수 값 불일치 문제가 생기지 않는다.


* 장점
  * 메모리 효율적으로 사용 가능
  * Thread Safe
  * 인스턴스 생성 여부 검사 (Lock 이슈 해결)

* 단점
  * 비동기화된 Resource 필드에 의존하게 되어 위 예시처럼 변수의 최신 값이나 원자성을 보장해줘야 한다
    * ex) volatile 키워드 이용

---

### ✅ 추가) Java volatile ?

> ### ✔️ Java volatile 이란?
>   * ```volatile``` keyword는 Java 변수를 Main Memory에 저장하겠다라는 것을 명시
>   * 매번 변수의 값을 Read할 때마다 CPU cache에 저장된 값이 아닌 Main Memory에서 읽음
>   * 또한 변수의 값을 Write할 때마다 Main Memory에 작성

### ✔️ 구조

![](https://nesoy.github.io/assets/posts/20180609/1.png)

### ✔️ 필요한 이유?

* volatile 변수를 사용하고 있지 않는 MultiThread 어플리케이션에서는 Task를 수행하는 동안 성능 향상을 위해 Main Memory에서 읽은 변수 값을 CPU Cache에 저장하게 됩니다.
* 만약에 **Multi Thread환경에서** Thread가 변수 값을 읽어올 때 각각의 CPU Cache에 저장된 값이 다르기 때문에 변수 값 불일치 문제가 발생하게 됩니다.


-> 해결
* ```volatile``` 키워드를 추가하게 되면 Main Memory에 저장하고 읽어오기 때문에 변수 값 불일치 문제를 해결
* Multi Thread 환경에서 read & write가 동시에 있는 상황에서 가장 최신의 값을 


### ✔️ volatile이 성능에 미치는 영향?
* volatile는 변수의 read와 write를 Main Memory에서 진행
* CPU Cache보다 Main Memory가 비용이 더 크기 때문에 변수 값 일치을 보장해야 하는 경우에만 volatile 사용하는 것이 좋음


### ✔️ volatile 총정리
* ```volatile```
  * Main Memory에 read & write를 보장하는 키워드
* 상황?
  * 하나의 Thread가 write하고 나머지 Thread가 읽는 상황인 경우 (multi-thread인 상황)
  * 변수의 값이 최신의 값으로 읽어와야 하는 경우
* 주의점
  * 성능에 어느정도 영향을 줄 수 있음
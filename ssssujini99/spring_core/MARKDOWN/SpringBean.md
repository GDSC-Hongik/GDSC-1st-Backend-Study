## Spring Bean


> π‘ ν΅μ¬ ν¬μΈνΈ
>
> **μ±κΈν€(singleton) μ€μ½ν** vs. **νλ‘ν νμ(prototype) μ€μ½ν**



## 1. μ±κΈν€ μ€μ½ν vs. νλ‘ν νμ μ€μ½ν

### βοΈ μ±κΈν€ μ€μ½νμ λΉ μμ²­

![Untitled](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F14778de1-b48a-4a56-939c-a87c415e1520%2FUntitled.png?table=block&id=6713db9a-c076-4944-ba25-68971f9bb610&spaceId=9d8417d1-3ee9-4dcc-8330-1ff1af8f3951&width=2000&userId=8d73ef51-8e98-4687-b671-90df60216d71&cache=v2)

> μ±κΈν€ μ€μ½νμ λΉμ μμ²­νλ©΄ **λͺ¨λ λμΌν μ€νλ§ λΉ**μ λ°ννλ€
>

### βοΈ νλ‘ν νμ μ€μ½νμ λΉ μμ²­

![Untitled](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fa619b837-5ca1-4aba-96ab-e5cd9d7f20af%2FUntitled.png?table=block&id=4f4ecf9e-182b-4aec-9161-16ce749103df&spaceId=9d8417d1-3ee9-4dcc-8330-1ff1af8f3951&width=2000&userId=8d73ef51-8e98-4687-b671-90df60216d71&cache=v2)

> **νλ‘ν νμ μ€μ½νμ μ€νλ§ λΉ μμ²­μ**   
> 
> 1οΈβ£Β ν΄λΌμ΄μΈνΈμμ νλ‘ν νμ μ€μ½νμ μ€νλ§ λΉμ μ€νλ§ μ»¨νμ΄λμ μμ²­   
> 2οΈβ£Β μ€νλ§ μ»¨νμ΄λλ μ΄ μμ μμ νλ‘ν νμ λΉμ μμ±νκ³ , μμ‘΄κ΄κ³ μ£Όμ(DI)   
> 3οΈβ£Β μμ±ν νλ‘ν νμ λΉμ ν΄λΌμ΄μΈνΈμ λ°ν   
>
- μ¬κΈ°μ νλ‘ν νμμ μ±κΈν€ νμμ μ€νλ§ λΉκ³Όλ λ€λ₯΄κ² λΉ μμ±, μμ‘΄κ΄κ³ μ£Όμ, μ΄κΈ°νκΉμ§λ§ μ§ννλ€. κ·Έλ κΈ°μ κ·Έ μ΄ν μ€νλ§ λΉμ ν΄λΌμ΄μΈνΈμ λ°νν μ΄νλ‘λ κ΄λ¦¬νμ§ μκΈ°μ μλ©Έ λ©μλκ°μ κ²μ λͺ¨λ ν΄λΌμ΄μΈνΈμμ μμ²΄μ μΌλ‘ κ΄λ¦¬ν΄μΌ νλ€


> π‘ μ λ¦¬
>
> βΒ μ±κΈν€μ μ€νλ§ μ»¨νμ΄λμ μλͺμ£ΌκΈ°λ₯Ό κ°μ΄νμ§λ§, νλ‘ν νμ μ€νλ§ λΉμ μλͺμ£ΌκΈ°λ₯Ό λ¬λ¦¬νλ€   
> βΒ μ±κΈν€ μ€νλ§ λΉμ λ§€λ² μ€νλ§ μ»¨νμ΄λμμ **λμΌν μΈμ€ν΄μ€λ₯Ό λ°ν**νμ§λ§, νλ‘ν νμ μ€νλ§ λΉμ μ€νλ§ μ»¨νμ΄λμ **μμ²­ν λλ§λ€ μλ‘μ΄ μ€νλ§ λΉμ μμ± ν μμ‘΄κ΄κ³κΉμ§ μ£Όμ λ° μ΄κΈ°ν μ§ν ν λ°ν**νλ€   
> βΒ νλ‘ν νμ μ€νλ§ λΉμ μλ©Έ λ©μλκ° νΈμΆλμ§ μλλ€   
> βΒ ν΄λΌμ΄μΈνΈκ° νλ‘ν νμ μ€νλ§ λΉμ μ§μ  κ΄λ¦¬ν΄μΌ νλ€ (μλ©Έ λ©μλλ μ§μ  νΈμΆν΄μΌ νλ€)   


- μ±κΈν€ μμ

```java
// Student.java

package com.springboot.dibasics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton") // μ±κΈν€ μΌλλ μλ΅ κ°λ₯ν¨
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

* μ±κΈν€ λΆμ
  * μ€νλ§ μ»¨νμ΄λ μμ ~ μ’λ£μμ κΉμ§ μ μ§λλ κΈ°λ³Έμ μΈ λ²μμ μ€μ½ν
  * μΌμ²΄μ Student μ€λΈμ νΈ μμ± μμ΄λ Spring BeanμΌλ‘ λ±λ‘λ  λ Spring IoC Containerμ λ±μ¬λλ©΄μ μμ±μ νΈμΆλλ κ² νμΈ
  * μμ±μ νΈμΆμ 1λ²λ§ μΌμ΄λ¨ β­οΈ
  * @Component λ singleton μ¦ μ€μ§ 1κ°μ μ€λΈμ νΈλ§ μμ±νλ κ²μ΄ Default
  * Singletonμ΄λ―λ‘ getBean(Student.class)λ₯Ό λ λ² μνν΄λ singleton 1κ°λ§ μμ±
  * μ€νλ§ μ»¨νμ΄λ μ’λ£ μ μλ©Έ λ©μλλ μλμΌλ‘ μ€νλ¨


---

- νλ‘ν νμ μμ

```java
// Student.java

package com.springboot.dibasics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")  // same as @Scope("prototype") -> μ΄κ±΄ νμν λλ§λ€ μλ‘­κ² λ§λ€μ΄μ μ°κΈ°
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
        Student s1 = ctx.getBean(Student.class); // μ΄ λμ μ€νλ§ μ»¨νμ΄λμ μ€νλ§ λΉμΌλ‘ μμ±λ¨ // μ΄λμ μμ±μ νΈμΆ
        s1.hi();

        Student s2 = ctx.getBean(Student.class); // μ΄ λμ μ€νλ§ μ»¨νμ΄λμ μ€νλ§ λΉμΌλ‘ μμ±λ¨ // μ΄λμ μμ±μ νΈμΆ
        s2.hi();

        if(s1==s2) System.out.println("s1 == s2");
        if(s1!=s2) System.out.println("s1 != s2");

        Phone p1 = ctx.getBean(Phone.class); // μ΄ λμ μ€νλ§ μ»¨νμ΄λμ μ€νλ§ λΉμΌλ‘ μμ±λ¨ // μ΄λμ μμ±μ νΈμΆ
        p1.ringing();

        Phone p2 = ctx.getBean(Phone.class); // μ΄ λμ μ€νλ§ μ»¨νμ΄λμ μ€νλ§ λΉμΌλ‘ μμ±λ¨ // μ΄λμ μμ±μ νΈμΆ
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

* νλ‘ν νμ λΆμ
    * μ€νλ§ λΉμ μμ±κ³Ό μμ‘΄κ΄κ³ μ£Όμμμ κΉμ§λ§ μ€νλ§ μ»¨νμ΄λμμ κ΄λ¦¬
      * ```@Scope(βprototypeβ)``` annotationμ Spring containerμ defaultλ‘ μμ±λλ κ²μ΄ μλλΌ getBean(Student.class) λ©μλλ₯Ό μ€νν  λλ§λ€ Student.class Beanμ΄ μμ±λ¨ β μ΄ λ λ§λ€μ΄μ§λ―λ‘ μ΄ λμ μμ±μκ° νΈμΆλ¨
    * μ€νλ§ λΉμ κ°μ Έμ¬λλ§λ€ μ±κΈν€ νμ λΉκ³Ό λ€λ₯΄κ² κ³μ μμ±λκ³  μ΄κΈ°ν μ½λ°± λ©μλλ λ§€λ² μν
    * μ€νλ§ μ»¨νμ΄λκ° μ’λ£λλ μλ©Έ μ½λ°±λ©μλλ₯Ό μννμ§ μμ

---

## μμ± ν¨ν΄ - μ±κΈν€ ν¨ν΄(Singleton Pattern)

## 1. μ±κΈν€ ν¨ν΄?
* μμ± ν¨ν΄ μ€ νλλ‘, μΈμ€ν΄μ€λ₯Ό μ€μ§ ν κ°λ§ λ§λ€μ΄μ μ κ³΅νλ ν΄λμ€κ° νμν κ²½μ°μ μ¬μ©νλ ν¨ν΄

### 1-1) μ μ
* μννΈμ¨μ΄ λμμΈ ν¨ν΄μμ μ±κΈν΄ ν¨ν΄(Singleton pattern)μ λ°λ₯΄λ ν΄λμ€λ, μμ±μκ° μ¬λ¬ μ°¨λ‘ νΈμΆλλλΌλ μ€μ λ‘ μμ±λλ κ°μ²΄λ νλμ΄κ³  μ΅μ΄ μμ± μ΄νμ νΈμΆλ μμ±μλ μ΅μ΄μ μμ±μκ° μμ±ν κ°μ²΄λ₯Ό λ¦¬ν΄νλ€. μ£Όλ‘ κ³΅ν΅λ κ°μ²΄λ₯Ό μ¬λ¬ κ° μμ±ν΄μ μ¬μ©νλ DBCP(DataBase Connection Pool)μ κ°μ μν©μμ λ§μ΄ μ¬μ©λλ€κ³  νλ€.

> **"νλ§λλ‘ κ°μ²΄λ₯Ό λ©λͺ¨λ¦¬μ ν λ²λ§ μ¬λ¦¬κ³ , ν΄λΉ λ©λͺ¨λ¦¬μ λ€μ μ κ·Όνλ€"**


### 1-2) μ¬μ© μ΄μ 

μμ€ν λ°νμ, νκ²½ μΈν κ΄λ ¨ μ λ³΄ λ± μΈμ€ν΄μ€κ° μ¬λ¬ κ°μΌλ λ¬Έμ κ° λ°μνλ κ²½μ° λ±μ΄ μλλ°,   
μ±κΈν€ ν¨ν΄μ μ¬μ©ν¨μΌλ‘μ¨ κ°μ Έκ° μ μ΄μ μ λ€μκ³Ό κ°λ€   

* λ©λͺ¨λ¦¬, μλ: κ³ μ λ λ©λͺ¨λ¦¬ μμ­μ μ¬μ©
* λ°μ΄ν° κ³΅μ : μΈμ€ν΄μ€κ° μ μ­μΌλ‘ μ¬μ©
* μΈμ€ν΄μ€κ° **ν κ°λ§ μ‘΄μ¬νλ κ²μ λ³΄μ₯**νκ³  μΆμ κ²½μ°


### 1-3) μ±κΈν€ ν¨ν΄μ κ΅¬ν

* private constructor μ μΈ
* static method μ¬μ©

μ±κΈν€ ν¨ν΄μμλ **μμ±μλ₯Ό ν΄λμ€ μμ²΄μμλ§ μ κ·Όν  μ μμ΄μΌ νκΈ° λλ¬Έμ** privateμΌλ‘ μ κ·Ό μ μ΄λ₯Ό ν΄μΌ νλ€   

>  μΈμ€ν΄μ€κ° νλλ§ μ‘΄μ¬ν¨μ λ³΄μ₯ν΄μΌ νκΈ° λλ¬Έμ **Single Thread**μμλ λ¬Έμ κ° λμ§ μμ§λ§,   
>  **Multi Thread** νκ²½μμλ κ°μ²΄μ μ κ·Ό μ μ΄κΈ°ν κ΄λ ¨ν λ¬Έμ κ° λ°μν  μ μλ€

### μ΄λ₯Ό ν΄κ²°νκΈ° μν λ°©λ²μ? π€
  * μ΄λ₯Έ μ΄κΈ°ν (Eager Initialization)
  * λ¦μ μ΄κΈ°ν (Lazy Initialization)
  * Lazy Initialization with Synchronized
  * λ¦μ λκΈ°ν, DCL (Double Checked Locking)

---


### βοΈ 1-4) μ΄λ₯Έ μ΄κΈ°ν (Eager Initialization)

> static ν€μλλ₯Ό ν΅ν΄ **ν΄λμ€ λ‘λκ° μ΄κΈ°ννλ μμ **μ **μ μ  λ°μΈλ©(Static Binding)**μ ν΅ν΄ ν΄λΉ μΈμ€ν΄μ€λ₯Ό λ©λͺ¨λ¦¬μ λ±λ‘νκΈ° λλ¬Έμ **Thread-safe**!


```java
public class Singleton {
    private static final Singleton INSTANCE = new Singleton();
    
    private Singleton() {}
    
    public static Singleton getInstance() {
        return INSTANCE;
    }
}
```

* μ₯μ : Thread-safe
* λ¨μ : λ―Έλ¦¬ λ§λ€μ΄λκΈ° λλ¬Έμ μ€μ  ν΄λΉ μΈμ€ν΄μ€λ₯Ό μ¬μ©νμ§ μμΌλ©΄ λ©λͺ¨λ¦¬ μΈ‘λ©΄μμ μν΄

---

### βοΈ 1-5) λ¦μ μ΄κΈ°ν (Lazy Initialization)

> μΈμ€ν΄μ€λ₯Ό **μ€μ  μ¬μ©νλ μμ μμ μμ±**νλ λ°©λ² - **λμ  λ°μΈλ©(Dynamic Binding)**

* μ΄λ₯Έ μ΄κΈ°ν λ°©λ²λ³΄λ€ λ©λͺ¨λ¦¬ μΈ‘λ©΄μμλ ν¨μ¨μ 
* μλ getInstance() λ **λ©ν° μ€λ λ νκ²½μμλ μμ νμ§ β**


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

* λ°μν  μ μλ λ¬Έμ  ? π§
  * λ§μ½ λ Threadκ° λμμ ν΄λΉ μΈμ€ν΄μ€μ μ κ·Ό μ μΈμ€ν΄μ€κ° μμ±λμ΄ μμ§ μμ κ²μΌλ‘ λ³΄κ³ ,    
  μ€λ³΅μΌλ‘ μ±κΈν€ μΈμ€ν΄μ€λ₯Ό μμ±ν  μ μκΈ° λλ¬Έ



* μ₯μ : μ¬μ© μμ μ μΈμ€ν΄μ€λ₯Ό μμ±νμ¬ λ©λͺ¨λ¦¬λ₯Ό ν¨μ¨μ μΌλ‘ μ¬μ©
* λ¨μ : Thread Safe νμ§ μμ

---

### βοΈ  1-6) λ¦μ λκΈ°ν λ¬Έμ μ ν΄κ²° -> Lazy Initialization with Synchronized   



1-5) μ λ¦μ λκΈ°νμ λ©ν° μ€λ λ λ¬Έμ λ ```Synchronized``` ν€μλλ₯Ό μ¬μ©ν΄ λκΈ°ν λ¬Έμ λ₯Ό ν΄κ²°ν  μ μμ   
* race conditionμ΄ λ°μν  μ μλ synchronized λΈλ‘μΌλ‘ κ°μΈκΈ° (ex -> λ©μλ μμμ λκΈ°ν μ²λ¦¬)


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


* μ₯μ : λ©λͺ¨λ¦¬λ₯Ό ν¨μ¨μ μΌλ‘ μ¬μ© + Thread Safe
* λ¨μ : μΈμ€ν΄μ€ μμ± μ¬λΆμ μκ΄μλ λκΈ°ν λλ¬Έμ μ±λ₯μ΄ λ¨μ΄μ§
  * getInstance( )λ₯Ό νΈμΆ μμ ν΄λΉ μΈμ€ν΄μ€μ μμ± μ¬λΆμ μκ΄μμ΄ λκΈ°ν λΈλ‘μ κ±°μ³μΌ ν¨
  * λκΈ°νλ κΈ°λ³Έμ μΌλ‘ **Lock**μ κ±°λ λ©μ»€λμ¦μ μ¬μ©νκΈ° λλ¬Έμ μ±λ₯μ΄ λ¨μ΄μ§

---

### βοΈ 1-6)μ λ°©μμ κ°μ  ->  λ¦μ λκΈ°ν, DCL (Double Checked Locking)

μ λκΈ°ν λΈλ‘ λ°©μμ κ°μ ν λ°©μμΌλ‘, **λ¨Όμ  μΈμ€ν΄μ€μ μμ± μ¬λΆλ₯Ό νμΈ** νλ λ°©λ² !

β­οΈ μΈμ€ν΄μ€κ° μμ±λμ§ μμ κ²½μ°μ λκΈ°ν μ²λ¦¬λ₯Ό νκΈ° λλ¬Έμ, **ν¨μ¨μ μΌλ‘ λκΈ°ν λΈλ‘μ λ§λ€ μ β­οΈ**


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
}β
```


> ### volatile ν€μλ ? π§
> volatile λ³μλ₯Ό μ¬μ©νκ³  μμ§ μλ λ©ν° μ€λ λ μ΄νλ¦¬μΌμ΄μμμλ μμ(Task)μ μννλ λμ μ±λ₯ ν₯μμ μν΄ Main Memoryμμ μ½μ λ³μ κ°μ CPU Cache μ μ μ₯νκ² λλ€.    
> λ§μ½μ λ©ν° μ€λ λ νκ²½μμ μ€λ λκ° λ³μ κ°μ μ½μ΄μ¬ λ κ°κ°μ CPU Cache μ μ μ₯λ κ°μ΄ λ€λ₯΄κΈ° λλ¬Έμ λ³μ κ° λΆμΌμΉ λ¬Έμ κ° λ°μνκ² λλλ°,    
> volatile ν€μλκ° μ΄λ° λ¬Έμ λ₯Ό ν΄κ²°ν  μ β­οΈ

-> μ¦, volatile λ³μλ Main Memory μ κ°μ μ μ₯νκ³  μ½μ΄μ€κΈ° λλ¬Έμ(read and write) λ³μ κ° λΆμΌμΉ λ¬Έμ κ° μκΈ°μ§ μλλ€.


* μ₯μ 
  * λ©λͺ¨λ¦¬ ν¨μ¨μ μΌλ‘ μ¬μ© κ°λ₯
  * Thread Safe
  * μΈμ€ν΄μ€ μμ± μ¬λΆ κ²μ¬ (Lock μ΄μ ν΄κ²°)

* λ¨μ 
  * λΉλκΈ°νλ Resource νλμ μμ‘΄νκ² λμ΄ μ μμμ²λΌ λ³μμ μ΅μ  κ°μ΄λ μμμ±μ λ³΄μ₯ν΄μ€μΌ νλ€
    * ex) volatile ν€μλ μ΄μ©

---

### β μΆκ°) Java volatile ?

> ### βοΈ Java volatile μ΄λ?
>   * ```volatile``` keywordλ Java λ³μλ₯Ό Main Memoryμ μ μ₯νκ² λ€λΌλ κ²μ λͺμ
>   * λ§€λ² λ³μμ κ°μ Readν  λλ§λ€ CPU cacheμ μ μ₯λ κ°μ΄ μλ Main Memoryμμ μ½μ
>   * λν λ³μμ κ°μ Writeν  λλ§λ€ Main Memoryμ μμ±

### βοΈ κ΅¬μ‘°

![](https://nesoy.github.io/assets/posts/20180609/1.png)

### βοΈ νμν μ΄μ ?

* volatile λ³μλ₯Ό μ¬μ©νκ³  μμ§ μλ MultiThread μ΄νλ¦¬μΌμ΄μμμλ Taskλ₯Ό μννλ λμ μ±λ₯ ν₯μμ μν΄ Main Memoryμμ μ½μ λ³μ κ°μ CPU Cacheμ μ μ₯νκ² λ©λλ€.
* λ§μ½μ **Multi Threadνκ²½μμ** Threadκ° λ³μ κ°μ μ½μ΄μ¬ λ κ°κ°μ CPU Cacheμ μ μ₯λ κ°μ΄ λ€λ₯΄κΈ° λλ¬Έμ λ³μ κ° λΆμΌμΉ λ¬Έμ κ° λ°μνκ² λ©λλ€.


-> ν΄κ²°
* ```volatile``` ν€μλλ₯Ό μΆκ°νκ² λλ©΄ Main Memoryμ μ μ₯νκ³  μ½μ΄μ€κΈ° λλ¬Έμ λ³μ κ° λΆμΌμΉ λ¬Έμ λ₯Ό ν΄κ²°
* Multi Thread νκ²½μμ read & writeκ° λμμ μλ μν©μμ κ°μ₯ μ΅μ μ κ°μ 


### βοΈ volatileμ΄ μ±λ₯μ λ―ΈμΉλ μν₯?
* volatileλ λ³μμ readμ writeλ₯Ό Main Memoryμμ μ§ν
* CPU Cacheλ³΄λ€ Main Memoryκ° λΉμ©μ΄ λ ν¬κΈ° λλ¬Έμ λ³μ κ° μΌμΉμ λ³΄μ₯ν΄μΌ νλ κ²½μ°μλ§ volatile μ¬μ©νλ κ²μ΄ μ’μ


### βοΈ volatile μ΄μ λ¦¬
* ```volatile```
  * Main Memoryμ read & writeλ₯Ό λ³΄μ₯νλ ν€μλ
* μν©?
  * νλμ Threadκ° writeνκ³  λλ¨Έμ§ Threadκ° μ½λ μν©μΈ κ²½μ° (multi-threadμΈ μν©)
  * λ³μμ κ°μ΄ μ΅μ μ κ°μΌλ‘ μ½μ΄μμΌ νλ κ²½μ°
* μ£Όμμ 
  * μ±λ₯μ μ΄λμ λ μν₯μ μ€ μ μμ
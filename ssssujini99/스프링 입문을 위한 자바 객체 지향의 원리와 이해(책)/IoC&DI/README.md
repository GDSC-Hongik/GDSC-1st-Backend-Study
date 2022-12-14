# ๐ IoC/DI - ์ ์ด์ ์ญ์ /์์กด์ฑ ์ฃผ์

```java
new Car();
Car ์์ฑ์์์ new Tire(); // Car๊ฐ Tire์ ์์กดํ๋ค
```

* ์ ์ฒด๊ฐ ๋ถ๋ถ์ ์์กดํ๋ค
* ํ๋ก๊ทธ๋๋ฐ์์ ์์กด ๊ด๊ณ๋ new๋ก ํํ๋๋ค   


* ๋ชฉ์ฐจ
  *  1) (์คํ๋ง โ) ์์ฑ์๋ฅผ ํตํ ์์กด์ฑ ์ฃผ์ 1
  *  2) (์คํ๋ง โ) ์์ฑ์๋ฅผ ํตํ ์์กด์ฑ ์ฃผ์ 2
  *  3) (์คํ๋ง โ) ์์ฑ์ ํตํ ์์กด์ฑ ์ฃผ์
  *  4) (์คํ๋ง โญ๏ธ) XML ํ์ผ ์ฌ์ฉ
  *  5) (์คํ๋ง โญ๏ธ) ์คํ๋ง ์ค์  ํ์ผ(XML)์์ ์์ฑ ์ฃผ์   
  *  6) (์คํ๋ง โญ๏ธ) @Autowired๋ฅผ ํตํ ์์ฑ ์ฃผ์   
  *  7) (์คํ๋ง โญ๏ธ) @Resource๋ฅผ ํตํ ์์ฑ ์ฃผ์   
  
---

## 1) (์คํ๋ง โ) ์์ฑ์๋ฅผ ํตํ ์์กด์ฑ ์ฃผ์ 1

```java
// Tire.java

package expert001_01;

public interface Tire {
  String getBrand();
}
```

```java
// KoreaTire.java

package expert001_01;

public class KoreaTire implements Tire{

  public String getBrand() {
    return "์ฝ๋ฆฌ์ ํ์ด์ด";
  }
}
```

```java
// AmericaTire.java

package expert001_01;

public class AmericaTIre implements Tire {
  public String getBrand() {
    return "๋ฏธ๊ตญ ํ์ด์ด";
  }
}
```

```java
// Car.java

package expert001_01;

public class Car {
  Tire tire;

  public Car() { // ์์ฑ์๋ฅผ ํตํ ์์กด์ฑ ์ฃผ์
    tire = new KoreaTire(); // ์๋์ฐจ๊ฐ ํ์ด์ด๋ฅผ ์์ฐํ๋ ๋ถ๋ถ // ์์กด ๊ด๊ณ๊ฐ ์ผ์ด๋๊ณ  ์๋ ๋ถ๋ถ
    // ์ฌ๊ธฐ์๋ Car ๊ฐ์ฒด๊ฐ Tire๋ฅผ ์ง์  ์์ฐํ๋,
    // ์ฆ Tire์ ๋ํ ์์กด์ฑ์ ์์ฒด์ ์ผ๋ก ํด๊ฒฐํ๋ ๋ฐฉ์
  }

  public String getTireBrand() {
    return "์ฅ์ฐฉํ ํ์ด์ด: " + tire.getBrand();
  }
}
```

```java
// Driver.java

package expert001_01;

public class Driver {
  public static void main(String[] args){
    Car car = new Car();
    System.out.println(car.getTireBrand());
  }
}
```

> ### โญ๏ธ ํน์ง ์ ๋ฆฌ   
> ```
> public Car() {
> tire = new KoreaTire();
> }
> ```
> * ์์ฑ์๋ฅผ ํตํ ์์กด์ฑ ์ฃผ์
> * Car ๊ฐ์ฒด๊ฐ Tire๋ฅผ ์ง์  ๋ง๋ค์ด์ ์ฃผ์
> * ์ ์ฐ์ฑ์ด ๋จ์ด์ง

* ํ์คํธ์ฝ๋ ๊ฒฐ๊ณผ    

![img.png](img/img.png)

---

## 2) (์คํ๋ง โ) ์์ฑ์๋ฅผ ํตํ ์์กด์ฑ ์ฃผ์ 2

```java
// Car.java

package expert001_02;

public class Car {
  Tire tire;

  public Car(Tire tire) { // Car์ ์์ฑ์์ ์ธ์๊ฐ ์๊น
    this.tire = tire; // new๊ฐ ์ฌ๋ผ์ง๊ณ  //  ์ธ๋ถ์์ ์์ฐ๋ tire ๊ฐ์ฒด๋ฅผ Car์ ์์ฐ์์ ์ธ์๋ก ์ฃผ์ํ๋ ํํ
  }

  public String getTireBrand() {
    return "์ฅ์ฐฉ๋ ํ์ด์ด: " + tire.getBrand();
  }
}
```

```java
// Driver.java

package expert001_02;

public class Driver {
  public static void main(String[] args) {
    Tire tire = new KoreaTire(); // ์ด์ ์๊ฐ ํ์ด์ด๋ฅผ ์ ํ
    Car car = new Car(tire); // "๋ง๋ค์ด์ง" tire ๊ฐ์ฒด๋ฅผ car์ ์ฃผ์

    System.out.println(car.getTireBrand());
  }
}
```


> ### โญ๏ธ ํน์ง ์ ๋ฆฌ
>
> * Car์ ์์ฑ์์ Tire ์ธ์๋ฅผ ๋ฐ์
> * ์ธ์๋ก ๋ฐ์ ๊ฐ์ฒด๋ก ์์กด์ฑ ์ฃผ์
>   * Car๋ ๊ทธ์  Tire ์ธํฐํ์ด์ค๋ฅผ ๊ตฌํํ ์ด๋ค ๊ฐ์ฒด๊ฐ ๋ค์ด์ค๊ธฐ๋ง ํ๋ฉด ๋จ
> * ์๋ก์ด ํ์ด์ด ๋ธ๋๋๊ฐ ์๊ฒจ๋ Tire ์ธํฐํ์ด์ค๋ง ๊ตฌํํด์ค ๋ค์ Driver์์๋ง ์ฃผ์ํด์ฃผ๋ฉด ๋จ
> * ์ ์ฐ์ฑ๊ณผ ํ์ฅ์ฑ ์ฆ๊ฐ


* ํ์คํธ์ฝ๋ ๊ฒฐ๊ณผ   

![img_1.png](img/img_1.png)

---

## 3) (์คํ๋ง โ) ์์ฑ์ ํตํ ์์กด์ฑ ์ฃผ์

* ์ฒ์ ์์ฑ๋  ๋ ์ฃผ์ํด์ฃผ๋ ๊ฒ ์๋๋ผ, ์ํ๋ ์์ ์ ์์กด์ฑ ์ฃผ์ํ๊ธฐ


```java
// Car.java

package expert001_03;

public class Car {
  Tire tire;

  public Tire getTire() {
    return tire;
  }

  public void setTire(Tire tire) { // ์์ฑ์ ํตํ ์์กด์ฑ ์ฃผ์
    this.tire = tire;
  }

  public String getTireBrand() {
    return "์ฅ์ฐฉ๋ ํ์ด์ด: " + tire.getBrand();
  }
}
```

```java
// Driver.java

package expert001_03;

public class Driver {
  public static void main(String[] args) {
    Tire tire = new KoreaTire();
    Car car = new Car();
    car.setTire(tire);

    System.out.println(car.getTireBrand());
  }
}
```

> ### โญ๏ธ ํน์ง ์ ๋ฆฌ
>
> * ~~์์ฑ์๊ฐ ์๋~~ ์์ฑ์ ํตํ ์์กด์ฑ ์ฃผ์
> * ์ํ๋ ์์ ์ ์์กด์ฑ ์ฃผ์ ๊ฐ๋ฅ
> * ํ์ง๋ง, ์์ฑ์๋ฅผ ํตํ ์์กด์ฑ ์ฃผ์์ด ๋ ๋ง์ด ์ ํธ๋จ
>   * ํ๋ก๊ทธ๋จ์์๋ ํ๋ฒ ์ฃผ์๋ ์์กด์ฑ์ ๊ณ์ ์ฌ์ฉํ๋ ๊ฒฝ์ฐ๊ฐ ๋ ์ผ๋ฐ์ ์ด๊ธฐ ๋๋ฌธ

 
* ํ์คํธ์ฝ๋ ๊ฒฐ๊ณผ   

![img_2.png](img/img_2.png)

---

## 4) (์คํ๋ง โญ๏ธ) XML ํ์ผ ์ฌ์ฉ -> ์์ฑ์ ํตํ ์์กด์ฑ ์ฃผ์

* ์คํ๋ง์ ํตํ ์์กด์ฑ ์ฃผ์
  * ์์ฑ์๋ฅผ ํตํ ์์กด์ฑ ์ฃผ์
  * ์์ฑ์ ํตํ ์์กด์ฑ ์ฃผ์


```java
// expert002.xml

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

<bean id="tire" class="expert002.KoreaTire"></bean>  <!-- KoreaTire๊ฐ id=tire ๊ฐ์ผ๋ก ๋น์ ๋ฑ๋ก -->
<bean id="americaTire" class="expert002.AmericaTire"></bean>  <!-- AmericaTire๊ฐ id=americaTire ๊ฐ์ผ๋ก ๋น์ ๋ฑ๋ก -->
<bean id="car" class="expert002.Car"></bean>  <!-- Car๋ฅผ car ๊ฐ์ผ๋ก ๋น์ ๋ฑ๋ก -->

</beans>
```

```java
// Driver.java

package expert002;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Driver {
  public static void main(String[] args) {

    ApplicationContext context = new ClassPathXmlApplicationContext("expert002/expert002.xml");

    Car car = context.getBean("car", Car.class); // ๋น์์ ๋ฑ๋ก๋ ๊ฑธ ๊ฐ์ ธ์ด // (๊ตฌ๋งค)

    Tire tire = context.getBean("tire", Tire.class); // ๋น์์ ๋ฑ๋ก๋ ๊ฑธ ๊ฐ์ ธ์ด // (๊ตฌ๋งค)

    car.setTire(tire); // ์คํ๋ง์ ํตํ ์์กด์ฑ ์ฃผ์ (xml ํ์ผ ์ด์ฉ) + ์์ฑ์ ํตํ ์ฃผ์

    System.out.println(car.getTireBrand());
  }
}
```

* Driver.java ํ์ผ ์คํ ์
```shell
> Task :compileJava UP-TO-DATE
> Task :processResources
> Task :classes

> Task :Driver.main()
20:44:58.785 [main] DEBUG org.springframework.context.support.ClassPathXmlApplicationContext - Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@87f383f
20:44:58.910 [main] DEBUG org.springframework.beans.factory.xml.XmlBeanDefinitionReader - Loaded 3 bean definitions from class path resource [expert002/expert002.xml]
20:44:58.928 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'tire'
20:44:58.934 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'americaTire'
20:44:58.934 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'car'
์ฅ์ฐฉ๋ ํ์ด์ด: ์ฝ๋ฆฌ์ ํ์ด์ด
```


> ### โญ๏ธ ํน์ง ์ ๋ฆฌ
>
> * ์คํ๋ง์ ๋์ํด์ ์ป๋ ์ด์ 
>   * ์ฌ์ปดํ์ผ/์ฌ๋ฐฐํฌํ์ง ์์๋ XML ํ์ผ๋ง ์์ ํ๋ฉด ํ๋ก๊ทธ๋จ์ ์คํ ๊ฒฐ๊ณผ๋ฅผ ๋ฐ๊ฟ ์ ์์
>   * XML ํ์ผ์ ๋ณ๊ฒฝํ๊ณ  ํ๋ก๊ทธ๋จ์ ์คํํ๋ฉด ๋ฐ๋ก ๋ณ๊ฒฝ์ฌํญ์ด ์ ์ฉ๋จ


* ํ์คํธ์ฝ๋ ๊ฒฐ๊ณผ   

![img_3.png](img/img_3.png)

---

## 5) (์คํ๋ง โญ๏ธ) ์คํ๋ง ์ค์  ํ์ผ(XML)์์ ์์ฑ ์ฃผ์  


```java
// expert003.xml

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

<bean id="koreaTire" class="expert003.KoreaTire"></bean>

<bean id="americaTire" class="expert003.AmericaTire"></bean>

<bean id="car" class="expert003.Car">
<property name="tire" ref="americaTire"></property>  <!-- Car์์ tire ์์ฑ์ ์ค์  -->
</bean>

</beans>
```

```java
// Driver.java

package expert003;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Driver {
  public static void main(String[] args) {

    ApplicationContext context = new ClassPathXmlApplicationContext("expert003/expert003.xml");

    Car car = context.getBean("car", Car.class);

    System.out.println(car.getTireBrand());
  }
}
```

* Driver.java ํ์ผ ์คํ ์

```shell
> Task :compileJava UP-TO-DATE
> Task :processResources UP-TO-DATE
> Task :classes UP-TO-DATE

> Task :Driver.main()
20:49:00.337 [main] DEBUG org.springframework.context.support.ClassPathXmlApplicationContext - Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@87f383f
20:49:00.464 [main] DEBUG org.springframework.beans.factory.xml.XmlBeanDefinitionReader - Loaded 3 bean definitions from class path resource [expert003/expert003.xml]
20:49:00.482 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'koreaTire'
20:49:00.488 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'americaTire'
20:49:00.488 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'car'
์ฅ์ฐฉ๋ ํ์ด์ด: ๋ฏธ๊ตญ ํ์ด์ด
```

* ํ์คํธ์ฝ๋ ๊ฒฐ๊ณผ   

![img_4.png](img/img_4.png)

---

## 6) (์คํ๋ง โญ๏ธ) @Autowired๋ฅผ ํตํ ์์ฑ ์ฃผ์

* ์คํ๋ง ์์ฑ ์ฃผ์ ๋ฐฉ๋ฒ ์ค @Autowired ์ด์ฉํ๊ธฐ
  * import๋ฌธ ํ๋์ @Autowired ์ด๋ธํ์ด์์ ์ด์ฉํ๋ฉด ์ค์ ์ ๋ฉ์๋๋ฅผ ์ด์ฉํ์ง ์๊ณ ๋ ์คํ๋ง ํ๋ ์์ํฌ๊ฐ ๋์  ์์ฑ์ ์ฃผ์ํด ์ค๋ค.


```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context"
        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context  http://www.springframework.org/schema/context/spring-context-3.1.xsd">

<context:annotation-config />

<bean id="tire" class="expert004.KoreaTire"></bean>

<bean id="americaTire" class="expert004.AmericaTire"></bean>

<bean id="car" class="expert004.Car"></bean>

</beans>
```

```java
// Car.java

package expert004;

import org.springframework.beans.factory.annotation.Autowired;

public class Car {
  @Autowired
  Tire americaTire; // @Autowired๋ก Car์ americaTire ์ฃผ์ํด์ฃผ๊ธฐ

  public String getTireBrand() {
    return "์ฅ์ฐฉ๋ ํ์ด์ด: " + americaTire.getBrand();
  }
}
```

* Driver.java ํ์ผ ์คํ ์


```shell
> Task :compileJava UP-TO-DATE
> Task :processResources UP-TO-DATE
> Task :classes UP-TO-DATE

> Task :Driver.main()
20:54:10.693 [main] DEBUG org.springframework.context.support.ClassPathXmlApplicationContext - Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@87f383f
20:54:10.824 [main] DEBUG org.springframework.beans.factory.xml.XmlBeanDefinitionReader - Loaded 8 bean definitions from class path resource [expert004/expert004.xml]
20:54:10.835 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.annotation.internalConfigurationAnnotationProcessor'
20:54:10.856 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.event.internalEventListenerProcessor'
20:54:10.857 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.event.internalEventListenerFactory'
20:54:10.858 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.annotation.internalAutowiredAnnotationProcessor'
20:54:10.858 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.annotation.internalCommonAnnotationProcessor'
20:54:10.862 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'tire'
20:54:10.867 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'americaTire'
20:54:10.867 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'car'
์ฅ์ฐฉ๋ ํ์ด์ด: ๋ฏธ๊ตญ ํ์ด์ด
```

* ํ์คํธ์ฝ๋ ๊ฒฐ๊ณผ   

![img_5.png](img/img_5.png)
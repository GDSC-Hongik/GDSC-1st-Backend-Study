1장: 사람을 사랑한 기술
=======================
* 하나의 목적 파일, 하나의 소스 기반
* 스프링은 객체 지향 기반
* 스프링의 근원적 요소 3가지 - IoC/DI, AOP, PSA


2장: 자바와 절차적/구조적 프로그래밍
===================================
자바 구동을 위한 도구
-------
* JDK: 자바로 소프트웨어를 개발하기 위한 도구
* JRE: 자바 실행 환경(= operating system)
* JVM(= java virtual machine): 하드웨어

프로그램이 메모리를 사용하는 방식
-----
* 코드 실행 영역과  데이터 저장 영역을 구분.
* OOP에서는 데이터 저장 영역을 다시 3가지로 분리.
* 데이터 저장 영역 = static + stack + heap, T메모리 구조

자바에 남은 절차적/구조적 프로그래밍의 유산
----------------
* goto문 (not used)
    * not used인 이유: goto문을 사용하면 프로그램에서 여기저기 왔다갔다 함
    * → 프로그램에 대한 깊은 이해가 필요한데, 간단한 일이 아님.

main() method
--------------
1. JRE가 JVM을 가동
2. static area에 java.lang import
3. main의 ‘{’에서 스택 프레임 할당
4. args 저장을 위한 공간을 확보
5. main의 ‘}’에서 스택 프레임 소멸

변수와 메모리 & 블록 구문 & 지역변수
----------
* main method 진입할 때 생긴 스택 프레임 안에 위치.
* 초기화하지 않으면, 이전 프로그램이 버리고 간 쓰레기 값이 나옴.
* if문에 진입한다면…?
    * main 스택 프레임 안에 if(true) 스택 프레임이 생김.
    * if문 안에서 변수를 선언한다면, if(ture) 스택 프레임 안에 저장됨.
* 지역변수 - 스택 영역
* 클래스 멤버변수 - 스태틱 영역
* 객체 멤버변수 - 힙 영역

method 스택 프레임
----
```java
public class Start4 {
	public static void main(String[] args) {
		int k = 5;
		int m;

		m = square(k);
	}

	private static int square(int k) {
		int result;
		k = 25;
		result = k;
		return result;
	}
}
```
* 위의 프로그램 실행시, 메모리의 스택 영역에 main() 스택 프레임말고 square() 스택 프레임이 하나 더 생김.
* 스택 프레임간에는 접근이 불가능하다.


전역변수
----
* 사용하지 않는 것을 추천
    * 길어진 코드 어딘가에서 전역 변수를 건드렸다가 문제가 발생한다면, 어디에서 발생한 문제인지 찾는 것도 일이됨.
    * 그럼 아예 쓰지마?
          * 파이값처럼 이미 고정되어, 읽기만 하는 용도라면 적극 활용.



멀티 프로세스 vs 멀티 스레드
---
멀티 프로세스: 다수의 T메모리 구조
* 절대 침범 불가한 영역.
* 메모리 사용량이 큼.

멀티 스레드: 스택 영역만 스레드 개수만큼 분할하여 사용

정리
---
결국 OOP란 절차적/구조적 프로그래밍의 진화된 버전이므로, 절차적/구조적 프로그래밍도 잘 알아야 함.

스태틱: 클래스의 공간

스택: method의 공간

힙: 객체들의 공간


3장: 자바와 객체 지향
=========
객체 지향 == 인간 지향
-----
기존의 구조적 프로그래밍 언어의 핵심 → 함수

- 논리적인 단위로 분할하여 이를 정복(= 블록화하여 작성)

현실 세계는 객체들의 합

- 따라서, 우리가 주변에서 사물을 인지하는 방식대로 프로그래밍하자

객체지향의 4대 특성
------------
1. 캡슐화
2. 상속
3. 추상화
4. 다형성

클래스는 분류에 대한 개념, 객체는 실체

추상화
----------
- 추상화란 공통 특성/ 공통 속성 추출
- 객체 지향의 추상화는 곧 모델링이다
- 클래스는 같은 특성을 가진 여러 객체를 총칭하는 집합의 개념
- 객체는 유일무이한 사물, “unique”
- 클래스의 변수: 속성 = 명사로 표현되는 특성
- 클래스의 method: 기능/행위 = 수행 절차 or 로직
- 클래스 모델을 표현하는 방법: UML 클래스 다이어그램→ 논리적 설계 → 코드로 물리적 설계

static 키워드
---------
같은 클래스의 객체들이 모두 같은 값을 가지고 있다면, 자료형 앞에 ‘static’을 추가하여 정적 변수로 만든다.
이는 T 메모리 구조의 static area에 단 하나의 저장공간을 가지고, 모든 객체들이 이를 공유한다.

상속
--------
* 상속은 계층, 조직의 개념이 아니다.
* 상위 클래스의 특성을 하위 클래스에서 상속하고 확장하여 사용할 수 있다는 의미이다.
* 결국, 상속이란 분류도의 개념이다.
* 그러니 부모 클래스 - 자식 클래스로 이해하는 것은 옳지 않음.
* 상/하, 슈퍼/서브 등의 개념이 상속에 더 가깝다.
* 흔히 상속을 ‘is a’관계로 표현한다.
* 상속은 ‘is a’ 보다는 ‘is a kind of’관계로 표현되는 것이 바람직하다.
* 정리하자면, 상속은
    * 상위 클래스의 특성을 재사용하고
    *  상위 클래스의 특성을 확장하고
    * is a kind of 관계를 만족시킨다.


다중 상속
---
자바는 다중 상속을 지원하지 않음.
상위 클래스의 method들이 중복되는 경우에 문제가 발생하기 때문에 다중 상속대신 인터페이스를 사용.

다형성
------
오버라이딩: 같은 메서드 이름. 같은 인자 목록으로 상위 클래스의 메서드를 재정의
오버로딩: 같은 메서드 이름. 다른 인자 목록으로 중복 정의

4장: 자바가 확장한 객체 지향
=========
abstract
------
추상→ 선언만 하고 구현은 안하는.

형식적으로 존재만하는 메서드 혹은 클래스.

final
----------
자바에는 const가  not used로 지정되어 있어서 사용이 불가능하다.

이를 대체하는 것이 final 키워드.

클래스 앞에 쓰이면, 상속을 허락하지 않고,

변수 앞에 쓰이면, 값의 변경을 불허한다.

메서드의 경우, 오버라이딩이 금지된다.

instanceof
----------
* 객체_참조_변수 instanceof 클래스명
* 객체가 클래스의 인스턴스인지에 대해 boolean type으로 반환한다.

package
---------
* namespace를 만들어주는 역할
* 다른 기능의 동일 이름 클래스를 만드는 경우 그 둘의 구분을 위함이다.


5장: 객체 지향 설계 5원칙 - SOLID
=====

5원칙 - SOLID
-----------------
5원칙의 근본은 응집도 높이고 결합도 낮추라는 고전 원칙.

응집도를 높인다 == 각 모듈은 하나의 책임에 집중하여 독립성이 높아짐

결합도를 낮춘다 == 모듈간 의존성을 낮춤

- SRP(Single Responsibility Principle) - 단일 책임 원칙
- OCP(Open Closed Principle) - 개방 폐쇄 원칙
- LSP(Liskov Substitution Principle) - 리스코프 치환 원칙
- ISP(Interface Segregation Principle) - 인터페이스 분리 원칙
- DIP(Dependency Inversion Principle) - 의존 역전 원칙

SRP(Single Responsibility Principle) - 단일 책임 원칙
----------------
하나의 클래스가 역할과 책임에 따라 네 개의 클래스로 쪼개진 것

예를 들어, 남자와 여자처럼 차이점도 있지만 공통점도 많다면 공통점을 사람 클래스에 두고 차이점만 각각 남자 클래스와 여자 클래스에 구현하면 된다.

DB 테이블 설계시의 정규화 작업과 유사함

- 정규화 작업이란, RDBMS의 설계에서 **중복을 최소화하여 데이터를 구조화하는 것**

if문을 남발을 방지할 수 있음.

⇒ 추상화(모델링) 과정에서  단일 책임 원칙을 고려하도록 하자.

OCP(Open Closed Principle) - 개방 폐쇄 원칙
----------------
클래스는 자신의 확장에는 OPEN, 주변의 변경에는 CLOSED여야 한다.

이해가 되는 듯, 안 되는 듯하여 아래의 링크 참고함.

[개방-폐쇄 원칙 (OCP: Open-Closed Principle)](https://yoongrammer.tistory.com/97)

OCP의 목적은 기존 클래스를 바꾸기보다, 기능을 확장하는 것을 목표로 함.

LSP(Liskov Substitution Principle) - 리스코프 치환 원칙
--------------
클래스의 상속 관계는 계층도나 조직도가 아니라 분류도의 개념으로 이해해야 한다는 말의 원칙화이다.

만약 상위 - 하위 관계를 부모 - 자식 관계로 해석했을 때, 아버지(부모) - 딸(자식)의 관계는 상속이 불가능한 속성들이 생긴다. 

⇒ 부모 - 자식이 아니라, 상위 - 하위 혹은 base - subtype으로 해석하라.

ISP(Interface Segregation Principle) - 인터페이스 분리 원칙
-----------
사용하지 않는 method와 연결되면 안 된다.

상위 클래스의 method중에 하위 클래스에서 사용하지 않을 method가 있어서는 안 된다.

Interface를 이용해 분리.

Interface는 작을수록 좋으니 단일 책임 원칙에 따르도록 하자.

DIP(Dependency Inversion Principle) - 의존 역전 원칙
---------------
자신보다 변하기 쉬운 것에 의존하기보다 인터페이스나 상위 클래스를 두어 의존성을 줄임.

자동차 클래스가 스노우타이어 클래스를 상속하기 보다는 타이어 인터페이스에 의존하게 하고, 스노우 타이어 클래스도 타이어 인터페이스를 의존하게 함.


6장: 스프링이 사랑한 디자인 패턴
======
디자인 패턴- 객체 지향 특성과 설계 원칙을 기반

어댑터 패턴
-------
객체를 속성으로 만들어 참조하는 디자인 패턴

- 호출 당하는 쪽의 메서드를 호출하는 쪽의 코드에 대응하도록 중간에 변환기를 통해 호출하는 것

프록시 패턴
-------
인터페이스를 사용하여 실제 객체 자리에 서비스 객체를 넣어줌.

- 실제 객체 메서드를 호출했는지, 서브스 객체의 메서드를 호출했는지 모르게 처리 가능.

데코레이터 패턴
-------
클라이언트가 받는 반환값에 장식을 더한다.

- 메서드 호출의 반환값에 변화를 주기 위해 중간에 decorator를 두는 패턴

싱글턴 패턴
-------
인터페이스 하나만을 만들고, 이를 재사용

- 클래스의 인스턴스(객체)를 하나만 만들어 사용하는 패턴

템플릿 메서드 패턴
-------
같은 부분은 상위, 다른 부분은 하위 클래스로 보냄.

즉, 공통 로직과 하위 클래스에 오버라이딩을 강제하는 추상 메서드, 선택적으로 오버라이딩할 수 있는 Hook메서드를 두는 패턴.

팩터리 메서드 패턴
-------
오버라이드된 메서드가 객체를 반환하는 패턴

전략 패턴
-------
1. 전략 메서드를 가진 객체
2. 전략 객체를 사용하는 컨텍스트 
3. 전략 객체를 생성해 컨택스트를 주입하는 클라이언트

클라이언트가 객체를 생성하여 컨택스트에 주입하는 것.

템플릿 콜백 패턴
-------
전략 패턴과 비슷.

전략을 익명 내부 클래스로 정의하여 사용함.

- 중복되는 부분을 컨텍스트로 이관


7장: 스프링 삼각형과 설정 정보
======
IoC/DI
-------
IoC - 제어의 역전

DI - 의존성 주입

### 의존 관계란

전체가 부분에 의존하는 것,

예를 들어, Car 객체가 생성될 때 Car의 생성자에서 Tire 객체가 생성된다면 Car 객체는 Tire 객체에 의존하고 있다.

- Driver class
    
    ```java
    public class Driver {
    	public static void main(String[] args) {
    		Car car = new car();
    	}
    }
    ```
    
- Car class
    
    ```java
    public class car {
    	Tire tire;
    
    	public Car() {
    		tire = new KoreaTire(); //Car class depends on Tire class
    	}
    
    	public String getTireBrand() {
    		return tire.getBrand();
    	}
    }
    ```
    
- Tire class
    
    ```java
    interface Tire {
    	String getBrand();
    }
    
    public class KoreaTire implements Tire {
    	public String getBrand() {
    		return "한국 타이어";
    	}
    }
    ```
    

생성자를 통한 의존성 주입
----------------------------
- 변경된 car class
    
    ```java
    public class car {
    	Tire tire;
    
    	public Car(Tire tire) {
    		this.tire = tire;
    	}
    
    	public String getTireBrand() {
    		return tire.getBrand();
    	}
    }
    ```
    

기존의 car class와 달리 인자를 통해 tire의 종류를 입력 받으므로, 코드의 유연성이 좋아진다.

- 재배포와 재컴파일의 필요성이 낮아진다.

속성을 통한 의존성 주입
---------------------------
앞선 생성자를 통한 의존성 주입에서는 한 번 결정된 타이어의 종류를 바꿀 수 없었다.

이를 바꾸기 위해서 사용하는 것이 속성을 통한 의존성 주입이다.

car class와  driver class를 다음과 같이 바꿔보자.

- Car class
    
    ```java
    public class car {
    	Tire tire;
    
    	public Tire getTire() {
    		return tire;	
    	}
    
    	public void setTire() {
    		this.tire = tire;
    	}
    
    	public String getTireBrand() {
    		return tire.getBrand();
    	}
    }
    ```
    
- Driver class
    
    ```java
    public class Driver {
    	public static void main(String[] args) {
    		Tire tire = new KoreaTire();
    		Car car = new Car():
    		car.setTire(tire);
    
    	}
    }
    ```
    

스프링을 통한 의존성 주입 - XML 파일 사용
-----------------------------------
XML 파일을 이용하면 재컴파일과 재배포 없이 프로그램을 수정할 수 있다.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans 
		http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context 
		http://www.springframework.org/schema/context/spring-context-3.1.xsd">

	<context:annotation-config />
	
	<bean id="tire1" class="expert006.KoreaTire"></bean>
	<bean id="tire2" class="expert006.AmericaTire"></bean>

	<bean id="car" class="expert006.Car">
		<property name = "tire" ref = "tire1"></property>
	</bean>
</beans>
```

새로운 종류의 tire를 추가하고 싶다면, 새로운 tire의 bean을 추가해주면 된다.

car의 tire를 다른 종류로 바꾸고 싶다면, property의 ref 값을 수정해주기만 하면 된다.

### @Autowired

autowired를 이용하면 property 지정 없이도 tire가 지정된다.

### @Resource

autowired랑 다른 점은 매칭의 우선 순위이다.

autowired가 type을 우선으로 매칭하는 것과 달리 resource는 id를 우선으로 매칭한다.

이 밖에도 autowired는 스프링의 annotation이지만, resource는 자바의 표준이라는 차이가 있다.


XML
----
XML(Extensible Markup Language)이란 데이터를 저장하고 전달할 목적으로 만들어진 마크업 언어이다. 어떤 두 프로그램 간의 데이터 교환이 필요할 때 사용되기도 한다.

우리 책에서는 의존성을 주입을 위해 사용된다.

우리 책에 나오는 XML 파일의 내용이다.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context
		http://www.springframework.org/schema/context/spring-context-3.1.xsd">

	<context:annotation-config />

	<bean id="tire1" class="expert006.KoreaTire"></bean>
	<bean id="tire2" class="expert006.AmericaTire"></bean>

	<bean id="car" class="expert006.Car">
		<property name = "tire" ref = "tire1"></property>
	</bean>
</beans>
```

한줄씩 무슨 용도인지 살펴보도록 하자.

## 빈 설정

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans.xsd">
</beans>
```

## xmlns

xmlns는 namespace를 의미한다.

우리는 bean 태그를 사용하므로 이를 위한 스키마 문서를 불러와야한다.

따라서 xmlns의 값은 "http://www.springframework.org/schema/beans" 가 된다.

크롬창에 해당 URI를 입력해보면 아래와 같은 스키마 문서들이 버전별로 여러개 존재한다.

XSD 파일은 XML 파일의 스키마 정의를 의미한다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a2f67ac0-9f2d-4621-b9ed-df210a91a821/Untitled.png)

## annotation

@Autowired나 @Resource와 같은 annotation을 사용할 경우 빈 설정이 조금 달라진다.

context namespace가 추가된다.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans 
				http://www.springframework.org/schema/beans/spring-beans.xsd 
	      http://www.springframework.org/schema/context 
				http://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>
</beans>
```

\<context:annotation-config/\>는 ApplicationContext에 이미 등록된 bean의 annotation을 활성화하는 역할을 한다.

annotation이 활성화되면, 어딘가에 등록된 bean들의 @Autowired와 @Qualifier을 인식한다.

위의 XML에는 없지만 <context : component-scan/>도 비슷한 역할을 한다.

차이점은 bean이 등록되어 있지 않아도 @Controller, @Service, @Component, @Repository annotation이 붙은 클래스들은 모두 bean으로 등록된다.

## xml에 주석 달기

아래와 같은 bean 태그들을 xml 파일에 추가하다보면 각각에 대한 설명이 필요할 수 있다.

```xml
<bean id="tire1" class="expert006.KoreaTire"></bean>
<bean id="tire2" class="expert006.AmericaTire"></bean>

<bean id="car" class="expert006.Car">
	<property name = "tire" ref = "tire1"></property>
</bean>
```

주석의 시작은 ‘< ! - -’ 태그, 끝은 ‘- - >’
```xml
<!--this is comment-->
<!--만약 시작 태그와 --- 끝 태그의 사이에 하이픈이 두 개 이상 연속된다면 오류가 발생-->
<!--하이픈이-시작과-끝-태그-사이에-존재는-하지만-연속되지는-않는다면-정상-작동-->
```


# AOP - aspect oriented programming

- 스프링의 AOP == 로직 주입
- 횡단 관심사: 다수의 모듈에 공통적으로 등장하는 부분
- 반사/중복(횡단 관심사)은 분리해서 하나로 관리
- AOP를 적용하면 전체 코드의 양은 늘어도, 유지 보수할 때 신경 쓸 부분은 줄어듦.
- 아래와 같은 Boy.java와 Girl.java가 있다면 횡단 관심사를 뽑아낼 수 있다.
    - Boy.java
        
        ```java
        package aop001;
        
        public class Boy {
        	public void runSomething() {
        		System.out.println("열쇠로 문을 열고 집에 들어간다.");
        
        		try {
        			System.out.println("컴퓨터로 게임을 한다.");
        		} catch (Exception ex) {
        			if (ex.getMessage().equals("집에 불남")) {
        				System.out.println("119 에 신고한다.");
        			}
        		} finally {
        			System.out.println("소등하고 잔다.");
        		}
        
        		System.out.println("자물쇠를 잠그고 집을 나선다.");
        	}
        }
        ```
        
    - Girl.java
        
        ```java
        package aop001;
        
        public class Girl {
        	public void runSomething() {
        		System.out.println("열쇠로 문을 열고 집에 들어간다.");
        
        		try {
        			System.out.println("요리를 한다.");
        		} catch (Exception ex) {
        			if (ex.getMessage().equals("집에 불남")) {
        				System.out.println("119 에 신고한다.");
        			}
        		} finally {
        			System.out.println("소등하고 잔다.");
        		}
        
        		System.out.println("자물쇠를 잠그고 집을 나선다.");
        	}
        }
        Footer
        © 2022 GitH
        ```
        
    - 횡단 관심사
        - 문 열고 집 들어가기
        - 집에 불나면 신고하기
        - 소등하고 자기
        - 문 잠그고 집 나서기
- Boy.java와 Girl.java에서 제거한 횡단 관심사는 다른 java 클래스에서 처리.

### 용어 정리

- Pointcut: 횡단 관심사를 적용할 타깃 메서드를 지정하는 것
- JoinPoint: Aspect 적용이 가능한 지점
- Advice: 언제 무엇을 PointCut에 적용하는가
- Aspect: advices + PointCuts

# PSA

PSA == 일관성 있는 서비스 추상화

서비스 추상화 == 다수의 기술을 공통된 인터페이스로 제어할 수 있게 한 것.

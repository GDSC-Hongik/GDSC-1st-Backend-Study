# 스프링 입문을 위한 자바 객체 지향의 원리와 이해

## <a href="https://silent-apparatus-578.notion.site/f15d5230cad44f829b40ce184431c276" target="_blank">목록(노션으로 보기)</a>

Ⅰ. <a href="https://silent-apparatus-578.notion.site/4f01f38cbaf94194929be134132d6279" target="_blank">사람을 사랑한 기술</a>

Ⅱ. <a href="https://silent-apparatus-578.notion.site/644077bf3df541a19810fcd2cc6588d9" target="_blank">자바와 절차적/구조적 프로그래밍</a>

Ⅲ. <a href="https://silent-apparatus-578.notion.site/23ced6d7790044bb89ce324474409625" target="_blank">자바와 객체 지향</a>

Ⅳ. <a href="https://silent-apparatus-578.notion.site/11ccc5be51d94a9fbd04013f1f9f9b0b" target="_blank">자바가 확장한 객체 지향</a>

Ⅴ. <a href="https://silent-apparatus-578.notion.site/5-SOLID-9914f51a3ae34921861c4c045c4c2ebc" target="_blank">객체 지향 설계 5원칙 - SOLID</a>

Ⅵ. <a href="https://silent-apparatus-578.notion.site/423a168a82ed4546ab0c655ec8ea4508" target="_blank">스프링이 사랑한 디자인 패턴</a>

Ⅶ. <a href="https://silent-apparatus-578.notion.site/ca451899398b4e7b9d0609898fcf49fd" target="_blank">스프링 삼각형과 설정 정보</a>

# Ⅰ. 사람을 사랑한 기술

- 자바 - 객체 지향 언어
    - 클래스 외부에 존재할 수 있는 것은 없음
        - main() 메서드를 포함해 모든 메서드는 반드시 클래스 내부에 존재하고,
        - System.out.println()와 같이 모든 메서드는 클래스.메서드명() 또는 객체.메서드명()으로 접근해야함.
        
        
        
# Ⅱ. 자바와 절차적/구조적 프로그래밍

## 1. 자바 프로그램의 개발과 구동

- 현실 세계
    - 개발 도구 이용해 개발된 프로그램이
    - 운영체제 통해
    - 물리적 컴퓨터인 하드웨어 상에서 구동됨
- 자바의 가상 세계
    - 자바 개발 도구(JDK) 이용해 개발된 프로그램이 → 자바 소스 컴파일러 javac.exe 포함
    - 자바 실행 환경(JRE) 통해 (즉, JRE는 JVM용 OS인 셈) → 자바 프로그램 실행기 java.exe 포함
    - 가상의 컴퓨터인 자바 가상 기계(JVM) 상에서 구동됨

## 2. 자바의 절차적/구조적 프로그래밍적 특징

- 절차적 프로그래밍: goto 사용 금지(인간이 이해하기 복잡해져서)
    
    → 대신 제어 흐름 위해 순서도, 제어문 사용
    
- 구조적 프로그래밍: 함수 사용(중복 코드 제거, 논리 분할), 지역 변수 사용 권장

## 3. 자바 프로그램이 메모리를 사용하는 방식

- 코드 실행 영역
- 데이터 저장 영역 (T 메모리 구조)
    - 스택 영역 - 메서드가 사용
    - 스태틱 영역 - 클래스가 사용
    - 힙 영역 - 객체가 사용

## 4. main() 메서드를 실행하는 동안의 T 메모리 변화 (메서드 스택 프레임)

```java
public class Start {
	public static void main(String[] args) {
		System.out.println("hello");
	}
}
```

1. JRE(OS, 자바 프로그램 실행 환경)가 프로그램 안에 main() 메서드 있나 확인함
2. JRE가 JVM(가상 기계)을 부팅함
3. JVM이 java.lang 패키지(println 메서드 쓰게 해줌)를 스태틱 영역에 갖다 놓음 (전처리)
4. JVM이 개발자가 작성한 모든 클래스(Start)와 임포트 패키지를 스태틱 영역에 갖다 놓음 (전처리)
5. main() 메서드 스택 프레임이 스택 영역에 놓임
    - 스택 프레임: 클래스 정의와 관련된 걸 제외하고서, 여는 중괄호를 만나면 스택 프레임이 하나 만들어지고, 닫는 중괄호 만나면 스택 프레임이 하나 소멸됨.
6. main() 메서드 인자의 변수 공간을 스택 프레임 맨 밑(스택 영역)에 할당함
7. main() 메서드 안의 명령문 실행하고, main() 메서드 끝을 나타내는 닫는 중괄호 만남
8. main() 메서드의 스택 프레임이 스택 영역에서 소멸됨
9. JRE가 JVM을 종료함. JRE도 사라짐.

## 5. 변수가 있는 main() 메서드를 실행하는 동안의 T 메모리 변화

```java
public class Start2 {
	public static void main(String[] args) {
		int i=10;
		double d=20.0;
	}
}
```

- main() 메서드 스택 프레임 안의 밑에서부터 변수 공간을 마련함
- 선언 안 한 변수는 쓰레기 값(이전에 해당 공간의 메모리 사용한 타 프로그램이 두고 간 값)을 가짐

## 6. 블록이 있는 main() 메서드를 실행하는 동안의 T 메모리 변화 (블록 스택 프레임)

```java
public class Start3 {
	public static void main(String[] args) {
		int i=10;
		int k=20;
		if(i==10){
			int m=k+5;
			k=m;
		}
		else { }
	}
}
```

- main() 메서드 스택 프레임 안에 if 문의 블록 스택 프레임이 중첩되어 놓임
- m은 if(true) 스택 프레임 안의 밑에서부터 변수 공간이 할당됨
- k는 if 스택 프레임 밖에 있지만 연산에 참여시킴
- if 블록 종료하는 닫는 중괄호 만나면 if 블록 스택 프레임 사라짐. if 내부의 변수 저장공간도 사라짐

## 7. 변수

- T 메모리에서의 위치
    - 지역 변수: 스택 영역의 스택 프레임 안
        - 스택 프레임(지역) 소멸 시 함께 소멸
        - 스택 프레임(지역) 안에서만 사용 가능. 외부에선 사용 불가.
    - 클래스 멤버 변수: 스태틱 영역
        - JVM(가상 기계) 가동될 때 생성되어, JVM 종료될 때 소멸
        - ex) main() 메서드의 변수
    - 객체 멤버 변수: 힙 영역
        - 객체 생성자에 의해 객체 생성 시 생성되어, 힙 메모리 회수기(가비지 컬렉터)에 의해 객체 소멸 시 함께 소멸
- 외부 스택 프레임(블록)에서 내부 스택 프레임(블록)의 변수에 접근하는 것은 불가능하나,
    
    내부 스택 프레임(블록)에서 외부 스택 프레임(블록)의 변수에 접근하는 것은 가능함.
    
    - 예시
        
        → if 스택 프레임 안의 변수(지역변수)는 if문 블록이 끝나고 나면, 메모리에서 if 스택프레임과 함께 사라지기 때문에, if문 블록 바깥에선 이 변수에 접근 불가함.
        
        → if 스택 프레임 안의 변수(지역변수)는 if문 블록이 시작하기 전엔, 메모리에 존재하지 않기 때문에, if문 블록 바깥에선 이 변수에 접근 불가함. 
        
        → if 스택 프레임 외부의 변수(main 스택 프레임 안의 지역변수)는 if 스택 프레임 존재 여부와는 상관 없이, 메모리 상에 존재하기 때문에, if문 블록 내부에서 이 변수에 접근 가능함.
        

## 8. 메서드 호출이 있는  main() 메서드를 실행하는 동안의 T 메모리 변화 (메서드 스택 프레임 2)

```java
public class Start4 {
	public static void main(String[] args) {
		int k=5;
		int m;
		m=square(k);
	}

	private static int square(int k){
		int result;
		k=25;
		result=k;
		return result;
	}
}
```

- square() 메서드 호출 → square() 메서드의 스택 프레임이 스택 영역에 생성됨
- square() 메서드 스택 프레임 내부 모습: 반환값, 인자, 메서드의 지역변수 순으로 아래부터 자리잡음
    - 반환값: 메서드 종료시 반환해 줄 값을 갖고 있는 가상의 변수
- Call by Value(값에 의한 전달): square() 메서드의 인자 변수에 무슨 짓을 해도 main() 메서드에서 인자로 넘겨진 변수는 영향 받지 않음. 값을 복사해서 전달해줬기 때문임.
- 메서드의 블랙박스화: 인자와 반환값에 의해서만 메서드 사이에 값이 전달될 뿐, 서로 내부의 지역 변수를 볼 수 없음(접근 불가능)
    - main() 메서드는 square()메서드의 지역변수에 접근 불가함.
        
        → 이유: 호출문 실행 전엔 square() 메서드의 스택 프레임이 아직 존재하지 않고, 호출문 실행 후엔 square() 메서드의 스택 프레임이 사라진 상태이기 때문임.
        
    - square() 메서드는 main() 메서드의 지역변수에 접근 불가함.
        
        → 이유: 자바에서 금지함
        
        → 자바가 이를 금지한 이유: 자바엔 포인터가 없어서, 메서드는 서로의 고유 공간이라서
        

## 9. 전역 변수

→ 쓰지 말자

## 10. 멀티 스레드/멀티 프로세스

- 멀티 스레드
    - 정의: 하나의 T 메모리만 사용하는데 스택 영역만 스레드 개수만큼 분할해서 사용하는 구조
    - 장점: 하나의 T 메모리만 써서 메모리 적게 사용
    - 단점: 스택 영역만 분할한 것이라 스태틱, 힙 영역은 공유해서 사용함 (안전성 떨어짐)
- 멀티 프로세스
    - 정의: 각 프로세스마다 각자의 T 메모리가 있는 구조. 즉, 다수의 T 메모리를 사용하는 구조
    - 장점: 하나의 프로세스가 다른 프로세스의 T 메모리 영역을 절대 침범할 수 없음 (안전함)
    - 단점: 다수의 T 메모리를 써서 메모리 많이 사용
    
    
    
# Ⅲ. 자바와 객체 지향

## 1. 객체 지향은 인간 지향이다

- 객체 지향: 0과 1로 대변되는 기계에 맞춰 사고하던 방식을 버리고,
    
    인간이 현실 세계의 주변 사물(object)을 인지하는 방식대로 프로그래밍 해보자.
    
    - 직관적.
    - 속성 따로, 메서드 따로가 아닌 객체 단위의 프로그래밍.
- 객체(사물)(object): 세상에 존재하는 모든 것. ex) 김종민, 한효주, 김연아
    - 각 객체는 고유함. ex) 김종민, 한효주, 김연아
    - 객체는 분류해서 이해됨. ex) 위 예시의 세 객체는 사람이라는 분류(class)에 속함.
    - 객체는 속성을 가짐. ex) 사람이라는 분류 안의 각 객체(김종민)는 나이, 몸무게의 속성 가짐.
    - 객체는 행위를 함. ex) 사람이라는 분류 안의 각 객체(김종민)는 먹다, 자다 등의 행위 가짐.
    
    ```java
    객체명: 김종민
    class명: 사람
    김종민 객체의 속성: 나이 19, 몸무게 80
    김종민 객체의 행위: 먹다, 자다
    ```
    

## 2. 객체 지향의 4대 특성: 추상화, 상속, 다형성, 캡슐화

- 추상화(Abstraction): 모델링
- 상속: 재사용과 확장
- 다형성(Polymorphism): 사용 편의
- 캡슐화(Encapsulation): 정보 은닉

## 3. 클래스와 객체의 관계(분류와 사물의 관계)

- 클래스(class): 분류에 대한 개념. 실체가 아님 ex) 사람, 펭귄
    - 객체들을 특성에 따라 분류
    - 같은 속성(필드)과 기능(메서드)을 가진 여러 객체를 총칭하는 집합의 개념
    - 모델링의 결과
    - 속성의 값을 가질 수 없음. 따라서 나이나 제조 일자 없음
- 객체(object, instance): 실체 ex) 김연아, 뽀로로
    - 세상에 존재하는 유일무이한 사물
    - 속성의 값을 가짐. 따라서 나이나 제조 일자 있음
- 예시) 사람 줄리엣 = new 사람();
    - 사람이라는 클래스(분류)를 이용해 유일무이하고 새로운 하나의 사람(객체)를 만들어 줄리엣(객체 참조 변수)이라는 이름을 지어준 것임
    

## 4. 추상화(모델링)

- 추상화: 공통 특성/ 공통 속성 추출
    - 구체적인 것을 분해해서 관심 영역에 대한 특성만 가지고 재조합하는 것
    - 추상화는 곧 모델링임
    - 애플리케이션 경계: 관심 영역
        
        → 사람 클래스가 사람 객체들의 모든 특성을 나열할 필요 없음. 만들고자 하는 애플리케이션이 어디에서 사용될 것인가에 따라 클래스 설계가 달라짐.
        
- 자바에서 추상화
    - 클래스 객체참조변수 = new 클래스();
    - 새로운 객체를 하나 생성해 그 객체의 주소값을 객체 참조 변수에 할당
- 추상화 과정
    - 1. 클래스 모델링
        
        → 해당 클래스의 객체들로부터 공통 특성 뽑아내기
        
        ex) 쥐: 성명, 나이, 꼬리수, 울다()
        
        ```java
        package abstraction01;
        
        public class Mouse {
        	public String name;
        	public int age;
        	public int countOfTail;
        
        	public void sing() {
        		System.out.println(name+" 찍찍!!!");
        	}
        }
        ```
        
    - 2. 만든 클래스를 가지고 객체 생성
        
        ```java
        package abstraction01;
        
        public class MouseDriver {
        	public static void main(String[] args) {
        		Mouse mickey = new Mouse();
        		mickey.name="미키";
        		mickey.age=85;
        		mickey.countOftail=1;
        
        		mickey.sing();
        
        		mickey=null;
        
        		Mouse jerry = new Mouse();
        		jerry.name="제리";
        		jerry.age=73;
        		jerry.countOftail=1;
        
        		jerry.sing();
        	}
        }
        ```
        
        - 객체 생성 → 속성 값 저장 위한 메모리 공간이 힙 영역에 할당됨
        - Mouse mickey = new Mouse();
            - Mouse mickey
                - Mouse 객체에 대한 참조 변수 mickey를 만듬.
                - [스택] main()메서드 스택 프레임 안에 변수 mickey가 생성됨
            - new Mouse();
                - 객체 생성자를 호출해 Mouse 클래스의 인스턴스(객체)를 하나 만들어 힙에 배치함.
                - [힙] 생성된 객체가 힙 영역에 배치됨
            - Mouse mickey = new Mouse();
                - Mouse 객체에 대한 주소를 참조 변수 mickey에 할당함.
                - 객체 참조 변수 mickey가 Mouse 클래스의 인스턴스(객체)를 참조함
        - [mickey.name](http://mickey.name) = “미키”;
            - 참조 연산자(.)를 이용해 실제 힙에 있는 객체에 접근 가능
        - mickey = null;
            - 객체 참조 변수 mickey가 더이상 힙 영역에 존재하는 Mouse 객체를 참조하지 않음.
            - 가비지 컬렉터가 아무도 참조하지 않는 Mouse 객체를 수거해감.(힙 영역에서 빠짐)
        - Mouse jerry = new Mouse();
            - 이전에 생성됐다 사라진 Mouse객체와 다른 새로운 것임
- 클래스 멤버와 객체 멤버
    - 클래스 멤버 = static 멤버 = 정적 멤버
        - [스태틱] 객체가 아닌 클래스에 속해있어 JVM 구동 스태틱 영역에 바로 배치됨
        - 정적 멤버 메서드: 객체들의 존재 여부와 관계 없이 쓸 수 있어야 할 때
        - 정적 멤버 속성: 클래스의 모든 객체가 같은 값 가질 때
    - 객체 멤버 = 인스턴스 멤버 = 오브젝트 멤버
        - [힙] 객체 생성자 호출 시 힙 영역에 배치됨
    - 클래스 멤버 메서드와 객체 멤버 메서드
        - 클래스의 멤버 메서드(main())와 객체의 멤버 메서드(sing())는 static 키워드로 구분됨
            
            → static 붙으면 클래스의 멤버 메서드임
            
        - UML 표기법에서 클래스의 멤버 메서드는 밑줄을 긋고 객체의 멤버 메서드는 밑줄을 긋지않음.
        - main()메서드가 클래스 멤버 메서드(정적 메서드)여야 하는 이유
            
            → T 메모리 초기화된 순간 객체가 하나도 없어서 객체의 멤버 메서드는 실행 불가해서
            
        - main()메서드 이외에 정적 메서드 사용하는 경우
            - main() 메서드의 논리를 함수로 분할해서 사용하는 경우
            - 정적 변수에 대한 접근자 메서드(getter)와 설정자 메서드(setter)로 사용하는 경우
            - 클래스의 인스턴스를 만들지 않고 사용하게 되는 유틸리티성 메서드의 경우
    - 클래스 멤버 속성(static 변수)과 객체 멤버 속성(인스턴스 변수)
        - 꼬리 개수처럼 모든 객체가 같은 값을 갖고 있는 속성은 그 값을 클래스에 저장
            
            → how? static 키워드를 속성 앞에 붙임 (클래스의 멤버 속성)
            
            ```java
            package abstraction01;
            
            public class Mouse {
            	public String name;
            	public int age;
            	public static int countOfTail = 1;
            
            	public void sing() {
            		System.out.println(name+" 찍찍!!!");
            	}
            }
            ```
            
            → [스태틱] 꼬리 개수 속성은 스태틱 영역에 단 하나의 저장공간 갖게 됨
            
            → jerry(객체명).contOfTail 혹은 Mouse(클래스명).contOfTail로 접근 가능
            
- 변수 정리
    - static 변수: 클래스 멤버 속성(변수)
        - [스태틱] 영역
        - 자동 초기화
        - 전역 변수(공유 변수)
    - 인스턴스 변수: 객체 멤버 속성(변수)
        - [힙] 영역
        - 자동 초기화
        - 다수의 객체 메서드가 공유(공유 변수)
    - 지역 변수
        - 초기화 안하면 쓰레기값
            - [스택] 영역
    - 멤버 속성(변수)들을 자동 초기화 해주는 이유: 공유 변수 성격 가져서 딱히 누가 초기화해야 한다고 규정할 수 없어서
    

## 5. 상속(재사용과 확장)

- 상속: 상위 클래스(동물)의 특성을 하위 클래스(강아지)에 상속하고, 거기에 더해 필요한 특성을 추가해 사용
    - 즉, 하위 클래스는 상위 클래스의 특성을 재사용하면서 더 확장(세분화)한 것임
        - ex) 강아지(하위 클래스)는 동물(상위 클래스)이면서 멍멍 짖는다.
    - 결과적으로, 특성 개수로 따지면 하위 클래스(동물의 특성+멍멍 짖는 특성) > 상위 클래스(동물의 특성)
    - 상위 클래스(동물)일수록 추상화, 일반화 된 것.
    - 하위 클래스(강아지)일수록 구체화, 특수화 된 것.
- ”하위 클래스는 상위 클래스다.”
    - ex) 강아지는 동물이다!
- 자바에서 상속 → extends(확장) 키워드 사용
    
    ```java
    public class 동물 { ... } 
    public class 강아지 extends 동물 { ... }
    ```
    
- 상속의 강력함
    - 코드 작성 전 유의할 점: 클래스명(동물, 강아지)은 분류스럽게, 객체 참조 변수명(검둥이, 흰둥이)은 유일무이한 사물스럽게!
    - 동물.java
        
        ```java
        package inheritance01;
        
        public class 동물 {
        	String myClass;
        	
        	동물() { //생성자
        		myClass = "동물";
        	}
        
        	void showMe() {
        		System.out.println(myClass);
        	}
        }
        ```
        
    - 강아지.java
        
        ```java
        package inheritance01;
        
        public class 강아지 extends 동물 {
        	강아지() { //생성자
        		myClass = "강아지";
        	}
        }
        ```
        
    - [Driver01.java](http://Driver01.java) → 상속의 장점 1: 재사용
        
        ```java
        package inheritance01;
        
        public class Driver01 {
        	public static void main(String[] args) {
        		동물 검둥이 = new 동물();
        		강아지 흰둥이 = new 강아지();
        
        		검둥이.showMe(); // 동물 출력
        		흰둥이.showMe(); // 강아지 출력
        }
        ```
        
        - 상위 클래스에서만 showMe() 메서드를 구현했지만, 모든 하위 클래스의 객체에서 showMe() 메서드를 사용 가능! (재사용)
    - [Driver02.java](http://Driver02.java) → 상속의 장점 2: 인간의 논리라 객체 지향적
        
        ```java
        package inheritance01;
        
        public class Driver02 {
        	public static void main(String[] args) {
        		동물 검둥이 = new 동물();
        		동물 흰둥이 = new 강아지();
        
        		검둥이.showMe(); // 동물 출력
        		흰둥이.showMe(); // 강아지 출력
        }
        ```
        
        - “강아지는 동물이다”라는게 표현됨. 인간 논리를 코드로 구현 가능!
    - [Driver03.java](http://Driver03.java) → 상속의 장점 3: 반복문 효과 극대화 (서로 다른 클래스의 객체들 한 번에 접근 가능)
        
        ```java
        package inheritance01;
        
        public class Driver03 {
        	public static void main(String[] args) {
        		동물[] 둥이들 = new 동물[2];
        
        		둥이들[0] = new 동물(); // 동물 검둥이 = new 동물();
        		둥이들[1] = new 강아지(); // 동물 흰둥이 = new 강아지();
        
        		for(int =0; i<동물.length; i++) {
        			둥이들[i].showMe();
        		}
        	}
        }
        ```
        
        - 반복문 하나로 각기 다른 클래스(동물, 강아지)의 객체들을  한 번에 접근 가능
- 하위 클래스 is a kind of 상위클래스
    - is a 관계를 추천하지 않는 이유:  “하위 클래스는 하나의 상위클래스”라는 의미가 되어 하위 클래스가 객체처럼 취급되어버림. 하나의 클래스란 하나의 객체이기 때문.
    - is a kind of 관계를 추천하는 이유: “하위 클래스는 상위 클래스의 한 부류”라는 의미가 되어 하위 클래스가 분류/집단(클래스)임을 명확히 드러냄.
        - ex) Dog is a kind of animal.


## 6. 다형성(사용편의성)

- 오버라이딩 vs 오버로딩
    - 오버라이딩(올라타기)(재정의): 맨 위에 올라탄 존재만 보임
        - 같은 메서드 이름, 같은 인자 목록으로 상위 클래스의 메서드를 재정의
    - 오버로딩(적재하기)(중복정의): 옆으로 적재된 모든 적재물이 다 보임
        - 같은 메서드 이름, 다른 인자 목록으로 다수의 메서드를 중복 정의
- 다형성과 T 메모리
- 오버라이딩 장점
    - 상위 클래스 타입의 객체 참조 변수에서 하위 클래스가 오버라이딩한 메서드를 자동으로 호출해줌.
    
    ```java
    class Driver {
    	public static void main(String[] args) {
    		동물[] 동물들 = new 동물[5];
    
    		동물들[0] = new 쥐();
    		동물들[1] = new 고양이();
    		동물들[2] = new 강아지();
    		동물들[3] = new 송아지();
    
    		for(int i=0; i<동물들.length; i++) {
    			동물들[i].울어보세요();
    		}
    	}
    }
    ```
    
    - 동물들[i]는 동물 클래스 타입인데, 동물 클래스(상위)의 울어보세요() 메서드가 아닌 쥐, 고양이, 강아지, 송아지 클래스(하위) 타입에 맞는 울어보세요() 메서드가 호출됨. 하위 클래스가 상위 클래스의 메서드 오버라이딩 했기 때문임.
- 오버로딩 장점
    - 함수명 하나만 가지고 인자 목록만 달리하면 돼서 편리함. 만일 오버로딩 지원안하는 언어였다면 인자 목록 달라질 때마다 새로운 이름의 함수 만들어야 함.

## 7. 캡슐화(정보 은닉)

- 접근 제어자와 UML 표기법: private(-), protected(#), public(+), [default](~)


# Ⅳ. 자바가 확장한 객체 지향


## 1. abstract 키워드(추상 메서드와 추상 클래스)

- 추상 메서드
    - 정의: 선언부o, 구현부x
    - 특징
        - 추상 메서드 하나라도 있는 클래스 → 추상 클래스로 선언되어야함
        - 추상 클래스는 객체 만들 수 없음
        - 추상 메서드는 하위 클래스에서 반드시 구현되어야 함.(오버라이딩 필수)
    - 예시 및 필요성: 하위 클래스에선 의미가 있지만 상위 클래스에선 의미가 없는 메서드 && 상속 관계로 인해 상위 클래스에서 없어선 안되는 메서드 → 추상 메서드로 나타내면 됨.
        - Driver.java
            
            ```java
            package abstractMethod01;
            
            public class Driver {
            	public static void main(String[] args) {
            		동물[] 동물들 = new 동물[5];
            
            		동물들[0] = new 쥐();
            		동물들[1] = new 고양이();
            		동물들[2] = new 강아지();
            		동물들[3] = new 송아지();
            		동물들[4] = new 병아리();
            
            		for(int i=0; i<동물들.length; i++) {
            			동물들[i].울어보세요();
            		}
            
            		// 동물 짐승 = new 동물();
            	}
            }
            ```
            
        - 고양이.java
            
            ```java
            package abstractMethod01;
            
            public class 고양이 extends 동물 {
            	void 울어보세요() {
            		system.out.println("냐옹 냐옹");
            	}
            }
            ```
            
        - [동물.java](http://동물.java) (잘못된 버전)
            
            ```java
            package abstractMethod01;
            
            public class 동물 {
            	void 울어보세요() {
            		system.out.println("뭐라고 울어????");
            	}
            }
            ```
            
            - Driver.java에서 동물 클래스 타입의 참조 변수를 통해, 고양이 클래스의 객체가 가진 울어보세요() 메서드를 호출하고 있으므로 동물 클래스의 울어보세요()  메서드는 반드시 존재해야 함.
            - 동물 객체의 울어보세요() 메서드는 선언만 있고 몸체는 없는 형태여야함 → 추상 메서드
        - [동물.java](http://동물.java) (추상 메서드로 수정한 버전)
            
            ```java
            package abstractMethod02;
            
            public abstract class 동물 {
            	abstract void 울어보세요() { }
            }
            ```
            
            - 추상 클래스는 객체를 만들 수 없어서 동물 타입으로 객체 만들려 하면 오류 뜸.
            - 동물 객체가 어떻게 울지 걱정 안 해도 됨.
        

## 2. 생성자

- 객체 생성자 메서드(생성자)
    - 정의: 객체를 생성하는 메서드
    - 형태: 클래스명()
    - 특징
        - 반환값이 없고 클래스명과 같은 이름을 가짐
        - 개발자가 만들지 않아도, 인자 없는 기본 생성자가 자동으로 만들어짐
        - 인자가 있는 생성자를 하나라도 만든다면, 기본 생성자가 자동으로 만들어지진 않음

## 3. 클래스 생성 시의 실행 블록, static 블록

- static 블록
    - 정의: 클래스가 스태틱 영역에 배치될 때 실행되는 코드 블록(클래스 생성자 대신)
    - 특징
        - 클래스가 제일 처음 사용될 때 클래스 정보가 T메모리의 스태틱 영역에 로딩되며, 단 한 번 해당 클래스의 static 블록이 실행됨
            - 클래스가 제일 처음 사용되는 경우
                1. 클래스의 객체를 최초로 만들 때
                2. 클래스의  정적 메서드를 사용할 때
                3. 클래스의 정적 속성을 사용할 때

## 4. final 키워드

- 클래스에 붙은 final → 상속 금지
- 변수에 붙은 fina → 변경 불가한 상수 (다른 언어의 const와 같은 역할. 자바에서 const는 not used)
    - 정적 상수, 객체 상수: 선언 시 초기화 가능
    - 지역 상수: 선언 시 혹은 최초 한 번만 초기화 가능
- 메서드에 붙은 final → 오버라이딩 금지

## 5. instanceof 키워드

- 정의: 객체가 특정 클래스의 인스턴스인지 물어보는 연산자. 결과는 true 혹은 false임.
- 형태: 객체참조변수 instanceof 클래스명
    - ex) 흰둥이 instanceof 강아지
- 특징
    - 클래스의 상속 관계와 인터페이스의 구현 관계에서 쓰임
    - 객체 참조 변수의 타입이 아닌 실제 객체의 타입에 의해 처리함
        - 예시 1
            
            ```java
            동물 동물객체 = new 동물();
            조류 조류객체 = new 조류(); // 조류 클래스는 동물 클래스의 하위 클래스
            
            동물객체 instanceof 동물; // true
            조류객체 instanceof 동물; // true
            조류객체 instanceof 조류; // true
            ```
            
        - 예시 2
            
            ```java
            동물 동물객체 = new 동물();
            동물 조류객체 = new 조류(); // 조류 클래스는 동물 클래스의 하위 클래스
            
            동물객체 instanceof 동물; // true
            조류객체 instanceof 동물; // true
            조류객체 instanceof 조류; // true
            ```
            
            - 예시 1과 달리 예시 2는 조류객체의 타입이 동물이다. 허나, 실행결과엔 변함 없다.
                - 이유) 객체 참조 변수의 타입이 아닌 실제 객체의 타입에 의해 처리하기 때문

## 6. package 키워드

- 정의: 네임스페이스 만들어주는 키워드
- 네임스페이스
    - 두 개의 클래스 이름 같아 이름 충돌 발생 → 네임스페이스 나누어 클래스의 전체 이름 지정
        - ex) Customer, Customer → 마케팅사업부.Customer, 고객사업부.Customer
    - 홍길동의 스마트폰, 일지매의 스마트폰 → 소유자로 네임스페이스 나누어 두 스마트폰을 구분함. 이때, 소유자가 패키지임.

## 7. interface 키워드, implements 키워드

- 인터페이스
    - 특징: public 추상 메서드와 public 정적 상수만 가질 수 있음

## 8. this 키워드

- 정의: 객체가 자기 자신을 지칭하는 키워드
- 특징
    - 원래는 지역변수와 속성변수(객체변수, 정적변수)의 이름이 같은 경우 지역 변수가 우선함
        - this 키워드를 사용하면 객체 변수를 사용할 수 있음
        - 클래스명을 접두사로 사용하면 정적 변수를 사용할 수 있음

## 9. super 키워드

- 정의: 바로 위 상위 클래스의 인스턴스를 지칭하는 키워드
- 특징
    - 상위 클래스의 인스턴스 메서드 호출 가능해짐 ex) super.method()
    - 상위의 상위 클래스의 인스턴스에는 접근 불가능함 ex) super.super.method() // 에러


# Ⅴ. 객체 지향 설계 5원칙 - SOLID

- 참고 자료

[[Java] 객체지향 설계 5원칙 - SOLID란 무엇일까?](https://devlog-wjdrbs96.tistory.com/380)

[객체지향 개발 5대 원리: SOLID](https://www.nextree.co.kr/p6960/)

## 1. SRP(단일 책임 원칙)

- 정의: 어떤 클래스를 변경해야 하는 이유는 오직 하나뿐이어야한다.

## 2. OCP(개방 폐쇄 원칙)

- 정의: 소프트웨어 엔티티(클래스, 모듈, 함수 등)는 자신의 확장에 대해서는 열려 있어야 하지만 주변의 변화에 대해서는 닫혀 있어야 한다.

## 3. LSP(리스코프 치환 원칙)

- 정의: 하위 클래스의 인스턴스는 상위형 객체 참조 변수에 대입해 상위 클래스의 인스턴스 역할을 하는 데 문제가 없어야 한다.

## 4. ISP(인터페이스 분리 원칙)

- 정의: 클라이언트는 자신이 사용하지 않는 메서드에 의존 관계를 맺으면 안 된다.

## 5. DIP(의존 역전 원칙)

- 정의
    - 고차원 모듈은 저차원 모듈에 의존하면 안 된다. 이 두 모듈 모두 다른 추상화된 것에 의존해야 한다.
    - 추상화된 것은 구체적인 것에 의존하면 안 된다. 구체적인 것이 추상화된 것에 의존해야 한다.
    - 자주 변경되는 구체 클래스에 의존하지 마라.
    
# Ⅵ. 스프링이 사랑한 디자인 패턴

## 1. 어댑터 패턴

- 정의: 호출당하는 쪽의 메서드를 호출하는 쪽의 코드에 대응하도록 중간에 변환기를 통해 호출하는 패턴
    - 즉, 객체를 속성으로 만들어서 참조하는 패턴

## 2. 프록시 패턴

- 정의: 제어 흐름을 조정하기 위한 목적으로 중간에 대리자를 두는 패턴
- 특징
    - 대리자는 실제 서비스와 같은 이름의 메서드를 구현함. 이때 인터페이스를 사용함.
    - 대리자는 실제 서비스에 대한 참조 변수를 가짐(합성).
    - 대리자는 실제 서비스의 같은 이름을 가진 메서드를 호출하고 그 값을 클라에게 돌려줌.
    - 대리자는 실제 서비스의 메서드 호출 전후에 별도의 로직을 수행할 수 있음.

## 3. 데코레이션 패턴

- 정의: 메서드 호출의 반환값에 변화를 주기 위해 중간에 장식자를 두는 패턴
- 특징
    - 장식자는 실제 서비스와 같은 이름의 메서드를 구현함. 이때 인터페이스를 사용함.
    - 장식자는 실제 서비스에 대한 참조 변수를 가짐(합성).
    - 장식자는 실제 서비스의 같은 이름을 가진 메서드를 호출하고, 그 반환값에 장식을 더해 클라에게 돌려줌.
    - 장식자는 실제 서비스이 메서드 호출 전후에 별도의 로직을 수행할 수 있음.

## 4. 싱글턴 패턴

- 정의: 클래스의 인스턴스, 즉 객체를 하나만 만들어 사용하는 패턴
- 특징
    - private 생성자를 가짐.
    - 단일 객체 참조 변수를 정적 속성으로 가짐.
    - 단일 객체 참조 변수가 참조하는 단일 객체를 반환하는 getInstance() 정적 메서드를 가짐.
    - 단일 객체는 쓰기 가능한 속성을 갖지 않는 것이 정석임.
- 필수 요소
    - new를 실행할 수 없도록 생성자에 private 접근 제어자를 지정함.
    - 유일한 단일 객체를 반환할 수 있는 정적 메서드가 필요함.
    - 유일한 단일 객체를 참조할 정적 참조 변수가 필요함.
    

## 5. 템플릿 메서드 패턴

- 정의: 상위 클래스의 견본 메서드에서 하위 클래스가 오버라이딩한 메서드를 호출하는 패턴
- 구성 요소
    - 템플릿 메서드
        - 공통 로직을 수행, 로직 중에 하위 클래스에서 오버라이딩한 추상 메서드/훅 메서드를 호출
    - 템플릿 메서드에서 호출하는 추상 메서드
        - 하위 클래스가 반드시 오버라이딩해야 함.
    - 템플릿 메서드에서 호출하는 훅 메서드
        - 하위 클래스가 선택적으로 오버라이딩함.

## 6. 팩터리 메서드 패턴

- 정의: 오버라이드된 메서드가 객체를 반환하는 패턴

## 7. 전략 패턴

- 정의: 클라이언트가 전략을 생성해 전략을 실행할 컨텍스트에 주입하는 패턴
- 구성 요소
    - 전략 메서드를 가진 전략 개체
    - 전략 객체를 사용하는 컨텍스트(전략 객체의 사용자/소비자)
    - 전략 객체를 생성해 컨텍스트에 주입하는 클라이언트(제3자, 전략 객체의 공급자)
    

## 8. 템플릿 콜백 패턴

- 정의: 전략을 익명 내부 클래스로 구현한 전략 패턴
    - 전략 패턴의 변형. DI에서 사용하는 특별한 형태의 전략 패턴
- 특징
    - 전략 패턴과 동일한데, 전략을 익명 내부 클래스로 정의해서 사용함.

## 9. 스프링이 사랑한 다른 패턴들

- 프론트 컨트롤러 패턴: 스프링 MVC에서 활용
- MVC 패턴: 스프링 MVC에서 활용


# Ⅶ. 스프링 삼각형과 설정 정보

## 1. IoC/DI - 제어의 역전/의존성 주입

### 1) 프로그래밍에서 의존성이란?

- 의사 코드로 살펴보는 의존성
    
    <aside>
    🚗 운전자가 자동차를 생산한다.
    자동차는 내부적으로 타이어를 생산한다.
    
    </aside>
    
    - 의존성 → 자동차(전체)가 타이어(부분)에 의존함
- 의존성: A클래스가 B클래스의 객체를 생성해 사용하는 것.
    - A와 B는 의존 관계에 있다.
    - A는 B에 의존한다.
- 의존성: 다른 객체를 참조하는 것 ⇒ 객체 참조 변수

### 2) 의존성을 직접 해결

- 의존성 직접 해결 → 자동차 내부에서 타이어를 직접 생산
- 의사 코드
    
    <aside>
    🚗 운전자가 자동차를 생산해 사용한다.
    자동차가 내부적으로 타이어를 생산해 사용한다.
    
    </aside>
    
- 실제 코드 요약
    - Driver 클래스
        
        ```java
        Car car = new Car(); // 운전자가 자동차를 생산해 사용한다.
        ```
        
    - Car 클래스
        
        ```java
        Tire tire = new KoreaTire(); // 자동차가 내부적으로 타이어를 생산해 사용한다.
        ```
        
- 실제 코드
    - Tire.java
        
        ```java
        interface Tire {
        	String getBrand();
        }
        ```
        
    - KoreaTire.java
        
        ```java
        public class KoreaTire implements Tire {
        	public String getBrand() {
        		retrn "한국 타이어";
        	}
        }
        ```
        
    - AmericaTire.java
        
        ```java
        public class AmericaTire implements Tire {
        	public String getBrand() {
        		retrn "미국 타이어";
        	}
        }
        ```
        
    - [Car.java](http://Car.java) → Tire를 생산하고 사용함: Car는 Tire에 의존함
        
        ```java
        public class Car {
        	Tire tire;
        	public Car() { // 생성자
        		tire = new KoreaTire(); // Tire를 생산함 (의존성)
        	}
        	public String getTireBrand() { 
        		return "장착된 타이어: " + tire.getBrand(); // Tire를 사용함
        	}
        }
        ```
        
    - [Driver.java](http://Driver.java) → Car를 생산하고 사용함: Driver는 Car에 의존함
        
        ```java
        public class Driver {
        	public static void main(String[] args) {
        		Car car = new Car();
        		System.out.println(car.getTireBrand());
        	}
        }
        ```
        
    - [CarTest.java](http://CarTest.java) → Driver.java와 같은 역할
        
        ```java
        import static org.junit.Assert.*;
        import org.junit.Test;
        
        public class CarTest {
        	@Test
        	pulic void 자동차가_장착한_타이어브랜드_테스트() {
        		Car car = new Car();
        		assertEquals("장착된 타이어: 한국 타이어", car.getTireBrand());
        }
        ```
        
- 문제점 & 해결책
    - 문제점
        - 특정 자동차가 생산될 때 `“어떤 타이어를 생산해서 장착할까”`를 자동차 스스로가 결정함.
        - 운전자는`“어떤 자동차를 생산할지”`만 결정 할 수 있고, 선택한 자동차에 `“어떤 타이어를 장착할지”` 는 결정할 수 없음(유연성 ↓)
    - 해결책
        - `“어떤 타이어 장착할지”`는 자동차가 아니라 운전자가 결정해야 함.
        - 운전자가 타이어를 생산함. 자동차는 운전자가 생산한 타이어를 건네받아 장착만 함.
        - 즉, 의존성을 내부에서 직접 해결하는 게 아니라, 외부에서 주입 받아 해결해야 함.
        

### 3) 스프링 없이 의존성 주입 1 - 생성자를 통한 의존성 주입 (생성자의 인자 주입으로 의존성을 해결)

- 의존성 주입 → 자동차 외부에서 생산된 타이어를 자동차에 장착
- 생성자를 통한 의존성 주입 → 생성자의 인자를 통해 타이어 건네 받음.
- 주입의 장점
    - Car는 KoreaTire, AmericaTire…등 몰라도 됨. Car는 Tire만 알면 됨.
    - 따라서 새로운 타이어 브랜드(ChinaTire…) 얼마든지 생겨도, Car는 알 필요도 없기에 코드를 변경할 필요 없음.(확장성 ↑)
- 의사 코드
    
    <aside>
    🚗 운전자가 타이어를 생산한다.
    운전자가 자동차를 생산하면서 타이어를 장착한다.
    
    </aside>
    
    - 자동차는 외부(운전자)에서 생산한 타이어를 장착해 사용한다.
    - 단, 타이어 장착은 자동차를 생산하는 순간에만 이뤄질 수 있다. 추후 타이어 교체 불가.
- 실제 코드 요약
    - Driver 클래스
        
        ```java
        Tire tire = new KoreaTire(); // 운전자가 타이어를 생산한다.
        Car car = new Car(tire); // 운전자가 자동차를 생산하면서 타이어를 장착한다.
        ```
        
- 실제 코드
    - [Car.java](http://Car.java) → Tire를 주입 받아 사용함.
        
        ```java
        public class Car {
        	Tire tire;
        	public Car(Tire tire) { // 생성자
        		this.tire = tire; // 외부에서 생성된 tire를 건네받음
        	}
        	public String getTireBrand() { 
        		return "장착된 타이어: " + tire.getBrand(); // Tire를 사용함
        	}
        }
        ```
        
    - [Driver.java](http://Driver.java) → Tire를 생산함. Car를 생산하면서 생산해둔 Tire를 주입시킴. Car를 사용함.
        
        ```java
        public class Driver {
        	public static void main(String[] args) { 
        		Tire tire = new KoreaTire(); // Tire를 생산함
        		Car car = new Car(tire); // Car를 생산하면서 Tire 주입시킴.
        		System.out.println(car.getTireBrand()); // Car를 사용함
        	}
        }
        ```
        
- 문제점 & 해결책
    - 문제점
        - 자동차를 생산할 때 한 번 타이어를 장착하면 더 이상 타이어를 교체할 방법이 없음
    - 해결책
        - 운전자가 원할 때 자동차의 타이어 교체할 수 있어야 함.
        - 생성자가 아닌 속성을 통한 의존성 주입으로 해결해야 함.
        - 이를 통해 자동차 생산과 타이어 장착을 한 번에 하지 않고 따로 따로 할 수 있게 분리함.
        

### 4) 스프링 없이 의존성 주입 2 - 속성을 통한 의존성 주입

- 속성을 통한 의존성 주입 → 속성 접근자 메서드의 인자를 통해 타이어 건네 받음.
- 의사 코드
    
    <aside>
    🚗 운전자가 타이어를 생산한다.
    운전자가 자동차를 생산한다.
    운전자가 자동차에 타이어를 장착한다.
    
    </aside>
    
- 실제 코드 요약
    - Driver 클래스
        
        ```java
        Tire tire = new KoreaTire(); // 운전자가 타이어를 생산한다.
        Car car = new Car(); // 운전자가 자동차를 생산한다.
        car.setTire(tire); // 운전자가 자동차에 타이어를 장착한다.
        ```
        
- 실제 코드
    - [Car.java](http://Car.java) → Tire를 주입 받아 사용함.
        
        ```java
        public class Car {
        	Tire tire; 
        	
        	public Tire getTire() {
        		return tire;
        	}
        
        	public void setTire(Tire tire) { // 외부에서 생성된 tire를 건네받음
        		this.tire = tire; 
        	}
        
        	public String getTireBrand() { 
        		return "장착된 타이어: " + tire.getBrand(); // Tire를 사용함
        	}
        }
        ```
        
    - [Driver.java](http://Driver.java) → Tire를 생산함. Car를 생산함. Car에 생산해둔 Tire를 주입시킴. Car를 사용함.
        
        ```java
        public class Driver {
        	public static void main(String[] args) { 
        		Tire tire = new KoreaTire(); // Tire를 생산함
        		Car car = new Car(); // Car를 생산함
        		car.setTire(tire); // Car에 tire 주입시킴
        		System.out.println(car.getTireBrand()); // Car를 사용함
        	}
        }
        ```
        
- 문제점 & 해결책
    - 문제점
        - 타이어 종류 바꾸려면 운전자 코드가 바뀌어야 함.
    - 해결책
        - 종합쇼핑몰 같은 곳에서 타이어를 구매해오는 형태라면 타이어 바꿀 때 종합쇼핑몰에서 구매하는 타이어의 종류만 바꾸면 되므로 종합쇼핑몰의 코드만 바뀌면 됨.
        

### 5) 스프링을 통한 의존성 주입 - XML 파일 사용

- XML 파일 사용한  의존성 주입 → 종합 쇼핑몰(스프링 프레임워크)에서 생산된 타이어를 구매해 자동차에 장착
- 의사 코드
    
    <aside>
    🚗 운전자가 종합 쇼핑몰에서 타이어를 구매한다.
    운전자가 종합 쇼핑몰에서 자동차를 구매한다.
    운전자가 자동차에 타이어를 장착한다.
    
    </aside>
    
- 실제 코드 요약
    - Driver 클래스
        
        ```java
        Tire tire = context.getBean("tire", Tire.class); // 운전자가 타이어를 쇼핑몰에서 구매한다.
        Car car = context.getBean("car", Car.class); // 운전자가 자동차를 쇼핑몰에서 구매한다.
        car.setTire(tire); // 운전자가 자동차에 타이어를 장착한다.
        ```
        
- 실제 코드
    - [Car.java](http://Car.java) → Tire를 주입 받아 사용함. - 변동 없음
        
        ```java
        public class Car {
        	Tire tire; 
        	
        	public Tire getTire() {
        		return tire;
        	}
        
        	public void setTire(Tire tire) { // 외부에서 생성된 tire를 건네받음
        		this.tire = tire; 
        	}
        
        	public String getTireBrand() { 
        		return "장착된 타이어: " + tire.getBrand(); // Tire를 사용함
        	}
        }
        ```
        
    - [Driver.java](http://Driver.java) → 쇼핑몰에서 Tire와 Car를 구매함. Car에 Tire를 주입시킴. Car를 사용함.
        
        ```java
        import org.springframework.context.ApplicationContext;
        import org.springframework.context.support.ClassPathXmlApplicationContext;
        
        public class Driver {
        	public static void main(String[] args) { 
        		ApplicationContext context = new ClassPathXmlApplicationContext("expert002/expert002.xml");
        		// 종합쇼핑몰에 대한 정보
        		Tire tire = context.getBean("tire", Tire.class); // 종합쇼핑몰에서 Tire를 구매함
        		Car car = context.getBean("car", Car.class); // 종합쇼핑몰에서 Car를 구매함
        		car.setTire(tire); // Car에 tire 주입시킴
        		System.out.println(car.getTireBrand()); // Car를 사용함
        	}
        }
        ```
        
    - expert002.xml → Tire와 Car 상품을 쇼핑몰에 등록함
        
        ```java
        ...
        <bean id="tire" class="expert002.KoreaTire"></bean> // 아이디 속성, 클래스 속성 지정하여
        <bean id="car" class="expert002.Car"></bean> // 상품들을 등록함
        ...
        ```
        
- 문제점 & 해결책
    - 문제점
        - 운전자는 쇼핑몰에서 car와 tire를 사와서 car에 tire를 장착하는데,
        - 쇼핑몰에서 파는 tire는 한 종류(KoreaTire)밖에 없음.
    - 해결책
        - 쇼핑몰에서 tire를 장착한 car를 팔면 됨. 쇼핑몰 내엔 여러 tire 있으니 americaTire, koreaTire 등 여러 종류 타이어 장착 가능.
    

### 6) 스프링을 통한 의존성 주입 - 스프링 설정 파일(XML)에서 속성 주입

- XML 파일에서 속성 주입 → 종합 쇼핑몰에서 타이어가 장착되어 판매되는 차를 구매해 사용
- 의사 코드
    
    <aside>
    🚗 운전자가 종합 쇼핑몰에서 자동차를 구매 요청한다.
    종합 쇼핑몰은 자동차를 생산한다.
    종합 쇼핑몰은 타이어를 생산한다.
    종합 쇼핑몰은 자동차에 타이어를 장착한다.
    종합 쇼핑몰은 운전자에게 자동차를 전달한다.
    
    </aside>
    
- 실제 코드 요약
    - Driver 클래스
        
        ```java
        Car car = context.getBean("car", Car.class); // 운전자가 타이어 장착된 자동차를 쇼핑몰에서 구매한다.
        ```
        
    - XML
        
        ```java
        <bean id="koreaTire" class="expert002.KoreaTire"></bean> 
        <bean id="americaTire" class="expert002.AmericaTire"></bean>
        
        <bean id="car" class="expert002.Car"> 
        	<property name="tire" ref="koreaTire"></property> 
        // 기존에 Driver클래스에서 하던 타이어 구매하고 타이어를 자동차에 장착하던 일을 대신함
        </bean>
        ```
        
- 실제 코드
    - [Car.java](http://Car.java) → Tire를 주입 받아 사용함. - 변동 없음
        
        ```java
        public class Car {
        	Tire tire; 
        	
        	public Tire getTire() {
        		return tire;
        	}
        
        	public void setTire(Tire tire) { // 외부에서 생성된 tire를 건네받음
        		this.tire = tire; 
        	}
        
        	public String getTireBrand() { 
        		return "장착된 타이어: " + tire.getBrand(); // Tire를 사용함
        	}
        }
        ```
        
    - [Driver.java](http://Driver.java) → 쇼핑몰에서 Car를 구매함. Car를 사용함.
        
        ```java
        import org.springframework.context.ApplicationContext;
        import org.springframework.context.support.ClassPathXmlApplicationContext;
        
        public class Driver {
        	public static void main(String[] args) { 
        		ApplicationContext context = new ClassPathXmlApplicationContext("expert002/expert002.xml");
        		// 종합쇼핑몰에 대한 정보
        		// Tire tire = context.getBean("tire", Tire.class); // 종합쇼핑몰에서 Tire를 구매함
        		Car car = context.getBean("car", Car.class); // 종합쇼핑몰에서 Car를 구매함
        		// car.setTire(tire); // Car에 tire 주입시킴
        		System.out.println(car.getTireBrand()); // Car를 사용함
        	}
        }
        ```
        
    - expert002.xml → Tire와 Car 상품을 쇼핑몰에 등록함. Car에 Tire를 주입시킴
        
        ```java
        ...
        <bean id="koreaTire" class="expert002.KoreaTire"></bean> 
        <bean id="americaTire" class="expert002.AmericaTire"></bean>
        
        <bean id="car" class="expert002.Car">
        	<property name="tire" ref="koreaTire"></property>
        </bean>
        ...
        ```
        

### 7) 스프링을 통한 의존성 주입 - @Autowired를 통한 속성 주입

- @Autowired를 통한 속성 주입 →
- 의사 코드 (이전과 동일)
    
    <aside>
    🚗 운전자가 종합 쇼핑몰에서 자동차를 구매 요청한다.
    종합 쇼핑몰은 자동차를 생산한다.
    종합 쇼핑몰은 타이어를 생산한다.
    종합 쇼핑몰은 자동차에 타이어를 장착한다.
    종합 쇼핑몰은 운전자에게 자동차를 전달한다.
    
    </aside>
    
- 실제 코드 요약
    
    ```java
    
    ```
    
- 실제 코드

### 8) 스프링을 통한 의존성 주입 - @Resource를 통한 속성 주입

### 9) 스프링을 통한 의존성 주입 - @Autowired vs. @Resource vs. <property> 태그

- 사례 연구 1. XML 설정 - 한 개의 bin이 id 없이 tire 인터페이스를 구현한 경우
- 사례 연구 2. XML 설정 - 두 개의 bin이 id 없이 tire 인터페이스를 구현한 경우
- 사례 연구 3. XML 설정 - 두 개의 bin이 tire 인터페이스를 구현하고 하나가 일치하는 id를 가진 경우
- 사례 연구 4. XML 설정 - 두 개의 빈이 tire 인터페이스를 구현하고 일치하는 id가 없는 경우
- 사례 연구 5. XML 설정 - 일치하는 id가 하나 있지만 인터페이스를 구현하지 않은 경우
- 사례 연구 6. XML 설정 - 두 개의 빈이 tire 인터페이스를 구현하고 속성과 일치하는 id가 없지만 @Resource 어노테이션의 name 속성이 id와 일치하는 경우
- 사례 연구 7. 사례 연구 6과 같도록 @Autowired를 지정하려면 다음과 같이 설정한다.
- 사례 연구 8. 실무라면 필자는 다음과 같이 설정하겠다.

## 2. AOP - Aspect? 관점? 핵심 관심사? 횡단 관심사?

### 1) 스프링에서 AOP란?

- 정의)
    - 공통 관심 사항(cross-cutting concern)과 핵심 관심 사항(core concern) 분리한 후, 분리한 공통 관심 사항을 원하는 곳에 적용
        - 자바의 AOP 구현체: AspectJ, 스프링 AOP
    - 관점 지향 프로그래밍(Aspect-oriented Programming)
        - 어떤 로직을 기준으로 관점으로 나누어 보고, 그 관점을 기준으로 각각 모듈화하는 프로그래밍 기법
        - ex) 그림 - 공통 관심사를 모듈로 분리한 후, 후에 원하는 곳에 공통관심사를 적용함
            
            ![aop01.png](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%89%E1%85%A1%E1%86%B7%E1%84%80%E1%85%A1%E1%86%A8%E1%84%92%E1%85%A7%E1%86%BC%E1%84%80%E1%85%AA%20%E1%84%89%E1%85%A5%E1%86%AF%E1%84%8C%E1%85%A5%E1%86%BC%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%87%E1%85%A9%20ca451899398b4e7b9d0609898fcf49fd/aop01.png)
            
            - 여러 클래스, 메서드에 걸쳐서 나타나는 비슷한 코드들(노랑, 파랑, 빨강)이 각 관점의 관심사임
            - 관심사(노랑, 파랑, 빨강 코드)를 관점(X, Y, Z) 별로 모듈화함
            - 후에 이를 핵심적인 비즈니스 로직에서 재사용함
- 용어)
    - Concern
        - 관심사
        - 코드 = 공통 관심사(횡단 관심사) + 핵심 관심사
    - Aspect
        - 관점
        - 흩어진 공통 관심사를 묶어서 모듈화한 하나의 모듈
        - Aspect = Point Cut들 + Advice들
    - Target
        - Aspect가 가지고 있는 Advice가 적용되는 대상(클래스, 메서드)
    - Point Cut
        - Aspect 적용 위치 지정자
        - 스프링 → 공통 관심사를 적용할 타깃 클래스의 타깃 메서드 지정자
    - Join Point
        - Aspect 적용이 가능한(Point cut의 후보가 되는) 모든 지점
        - Join Point $\supset$ Point cut
    - Advice
        - 조언, 충고
        - Pointcut에 언제, 무엇을 적용할지 정의한 메서드
- 장점)
    - 핵심 관심사항과 공통 관심 사항을 분리함
        - 핵심 관심 사항을 깔끔하게 유지할 수 있음.
        - 원하는 적용 대상을 선택할 수 있음
        - 변경이 필요하면 공통 로직 하나만 변경하면 됨
            - 단일 책임 원칙 적용

### 2) AOP가 필요한 상황

- 예시
    - 상황) 모든 메서드의 호출 시간을 측정하고 싶음
    - 코드) 각 메서드에 시간 측정 코드 넣음
        
        ```java
        /**
             * 회원 가입
             */
            public Long join(Member member) { // import.
                long start = System.currentTimeMillis(); // 7.1
        
                try{
                    // 같은 이름이 있는 중복 회원 X
                    validateDuplicateMember(member); // extract method 사용했음(ctrl+alt+shift+t)
                    memberRepository.save(member);
                    return member.getId();
                } finally { // try finally문 쓰면 finally는 위의 로직 터져도 항상 들어옴 7.1
                    long finish = System.currentTimeMillis();
                    long timeMs = finish - start;
                    System.out.println("join = " + timeMs + "ms");
                }
            }
        ```
        
    - 문제)
        - 회원 가입, 회원 조회에 시간을 측정하는 기능은 핵심 관심 사항이 아님
        - 시간을 측정하는 로직은 공통 관심 사항임.
        - 시간을 측정하는 로직과 핵심 비즈니스 로직이 섞여 있어 유지 보수가 어려움
        - 시간을 측정하는 로직을 별도의 공통 로직으로 만들기 매우 어려움
        - 시간을 측정하는 로직을 변경할 때 모든 로직을 찾아가면서 변경해야 함
    - 해결) AOP(Aspect Oriented Programming) 사용
        - 공통 관심 사항(cross-cutting concern) vs 핵심 관심 사항(core concern) 분리

### 3) AOP 적용

1. AOP 패키지 만들기 `hello.hellospring.aop`
2. TimeTraceAop 클래스 만들기 
    - TimeTraceAop.java
        
        ```java
        package hello.hellospring.aop;
        
        public class TimeTraceAop {
        }
        ```
        
3. @Aspect 붙이기
    - 에러) @Aspect 임포트가 안됨
    - 해결)
        - aop 의존성 추가하기 위해 build gradle에 `implementation 'org.springframework.boot:spring-boot-starter-aop'` 추가
        - 추가하고 build.gradle을 수정한 후 reload버튼을 클릭
            - reload 버튼 위치
                
                build.gradle 수정 후 좌측에 Gradle 창 열면 코드 좌측 상단에 자동으로 함께 뜸
                
                ![Untitled](%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%89%E1%85%A1%E1%86%B7%E1%84%80%E1%85%A1%E1%86%A8%E1%84%92%E1%85%A7%E1%86%BC%E1%84%80%E1%85%AA%20%E1%84%89%E1%85%A5%E1%86%AF%E1%84%8C%E1%85%A5%E1%86%BC%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%87%E1%85%A9%20ca451899398b4e7b9d0609898fcf49fd/Untitled.png)
                
4. 코드 작성
    - main/java/hello.hellospring.aop/TimeTraceAop.java
        
        ```java
        package hello.hellospring.aop;
        
        import org.aspectj.lang.ProceedingJoinPoint; // import ProceedingJoinPoint
        import org.aspectj.lang.annotation.Aspect; // import Aspect.
        
        @Aspect // import Aspect. build.gradle 수정해야 import 제대로 됨
        public class TimeTraceAop {
        
            public Object execute(ProceedingJoinPoint joinPoint) throws Throwable { // import ProceedingJoinPoint
                long start = System.currentTimeMillis();
                System.out.println("START:" + joinPoint.toString());
                try{
                    // Object result = joinPoint.proceed();
                    // return result;
                    return joinPoint.proceed(); // refactor -> inline variable. 위 두 줄을 inline함
                } finally {
                    long finish = System.currentTimeMillis();
                    long timeMs = finish - start;
                    System.out.println("END = " + joinPoint.toString() + " " + timeMs + "ms");
                }
            }
        }
        ```
        
5. 스프링 빈으로 등록하기
    
    1) @Component 붙여서 컴포넌트 스캔
    
    - main/java/hello.hellospring.aop/TimeTraceAop.java
        
        ```java
        package hello.hellospring.aop;
        
        import org.aspectj.lang.ProceedingJoinPoint; // import ProceedingJoinPoint
        import org.aspectj.lang.annotation.Aspect; // import Aspect.
        import org.springframework.stereotype.Component; // import Component.
        
        @Aspect // import Aspect. build.gradle 수정해야 import 제대로 됨
        @Component // import Component. 스프링 빈 등록
        public class TimeTraceAop {
        
            public Object execute(ProceedingJoinPoint joinPoint) throws Throwable { // import ProceedingJoinPoint
                long start = System.currentTimeMillis();
                System.out.println("START:" + joinPoint.toString());
        				...
        ```
        
    
    2) 스프링 빈에 등록해서 쓰기
    
    - main/java/hello.hellospring/SpringConfig
        
        ```java
        package hello.hellospring;
        
        import hello.hellospring.aop.TimeTraceAop; // import TimeTraceAop 7.2
        import hello.hellospring.repository.MemberRepository; // import MemberRepository
        import hello.hellospring.repository.MemoryMemberRepository; // import MemoryMemberRepository
        import hello.hellospring.service.MemberService; // import MemberService
        import org.springframework.context.annotation.Bean; // import Bean
        import org.springframework.context.annotation.Configuration; // import Configuration
        
        @Configuration // import Configuration
        public class SpringConfig {
            @Bean // import Bean
            public MemberService memberService() { // import MemberService
                return new MemberService(memberRepository());
            }
        
            @Bean
            public MemberRepository memberRepository(){ // import MemberRepository
                return new MemoryMemberRepository(); // import MemoryMemberRepository
            }
        
            @Bean // 7.2 AOP 스프링 빈 등록
            public TimeTraceAop timeTraceAop() { // import TimeTraceAop 7.2
                return new TimeTraceAop();
            }
        }
        ```
        
6. 공통 관심사를 어디에 적용할지 타겟팅 @Around
    - main/java/hello.hellospring.aop/TimeTraceAop.java
        
        ```java
        package hello.hellospring.aop;
        
        import org.aspectj.lang.ProceedingJoinPoint; // import ProceedingJoinPoint
        import org.aspectj.lang.annotation.Around; // import Around
        import org.aspectj.lang.annotation.Aspect; // import Aspect.
        import org.springframework.stereotype.Component; // import Component.
        
        @Aspect // import Aspect. build.gradle 수정해야 import 제대로 됨
        @Component // import Component. 스프링 빈 등록
        public class TimeTraceAop {
            @Around("execution(* hello.hellospring..*(..))") // import Around. 여기선 hello.hellospring에 있는 거 다 적용함
            public Object execute(ProceedingJoinPoint joinPoint) throws Throwable { // import ProceedingJoinPoint
                long start = System.currentTimeMillis();
                System.out.println("START:" + joinPoint.toString());
                try{
                    // Object result = joinPoint.proceed();
                    // return result;
                    return joinPoint.proceed(); // refactor -> inline variable. 위 두 줄을 inline함
                } finally {
                    long finish = System.currentTimeMillis();
                    long timeMs = finish - start;
                    System.out.println("END = " + joinPoint.toString() + " " + timeMs + "ms");
                }
            }
        }
        ```
        
    - 적용 대상 변경
        - ex) service에만 적용
            
            `@Around("execution(* hello.hellospring.service..*(..))")`
            
7. 기존에 적어둔 시간 측정 코드 주석 처리하고 원래대로 되돌리기
    - main/java/hello.hellospring.service/MemberService.jav
        
        ```java
        public class MemberService {
            // private final MemberRepository memberRepository = new MemoryMemberRepository(); // 변경 전(3.4)
            private final MemberRepository memberRepository; // import. 리포지토리 생성. 변경 후(3.5)
        
            //@Autowired // import 4.1
            public MemberService(MemberRepository memberRepository) { // Alt + Insert로 Constructor(생성자)
                this.memberRepository = memberRepository; // memberRepository를 직접 생성하는게 아니라 외부에서 넣어줌
            }
        
            /**
             * 회원 가입
             */
            public Long join(Member member) { // import.
                // 같은 이름이 있는 중복 회원 X
                validateDuplicateMember(member); // extract method 사용했음(ctrl+alt+shift+t)
                memberRepository.save(member);
                return member.getId();
        // 7.1
        //        long start = System.currentTimeMillis(); // 7.1
        //
        //        try{
        //            // 같은 이름이 있는 중복 회원 X
        //            validateDuplicateMember(member); // extract method 사용했음(ctrl+alt+shift+t)
        //            memberRepository.save(member);
        //            return member.getId();
        //        } finally { // try finally문 쓰면 finally는 위의 로직 터져도 항상 들어옴 7.1
        //            long finish = System.currentTimeMillis();
        //            long timeMs = finish - start;
        //            System.out.println("join = " + timeMs + "ms");
        //        }
            }
        
            private void validateDuplicateMember(Member member) {
                memberRepository.findByName(member.getName()) // 결과가 optional임 -> optionalmember.ifPrest 가능
                        .ifPresent( m-> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                }); // m에 member가 들어오면(null이 아니면) 이미 존재하는 회원이라고 해줌.
                /* 위 코드는 아래를 줄인 버전임
                Optional<Member> result = memberRepository.findByName(member.getName());
                result.ifPresent(m->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
                */
            }
        
            /**
             * 전체 회원 조회
             */
            public List<Member> findMembers(){ // import List
                return memberRepository.findAll();
        // 7.1
        //        long start = System.currentTimeMillis(); // 7.1
        //        try{
        //            return memberRepository.findAll();
        //        }finally{ // 7.1
        //            long finish = System.currentTimeMillis();
        //            long timeMs = finish - start;
        //            System.out.println("join = " + timeMs + "ms");
        //        }
            }
        ```
        
8. 회원 목록 들어가서 시간 측정 결과 확인
    - 터미널 (전체 적용)
        
        ```java
        START:execution(String hello.hellospring.controller.MemberController.list(Model))
        START:execution(List hello.hellospring.service.MemberService.findMembers())
        START:execution(List hello.hellospring.repository.MemoryMemberRepository.findAll())
        END = execution(List hello.hellospring.repository.MemoryMemberRepository.findAll()) 4ms
        END = execution(List hello.hellospring.service.MemberService.findMembers()) 11ms
        END = execution(String hello.hellospring.controller.MemberController.list(Model)) 16ms
        ```
        
    - 터미널 (service만 적용)
        
        ```java
        START:execution(List hello.hellospring.service.MemberService.findMembers())
        END = execution(List hello.hellospring.service.MemberService.findMembers()) 9ms
        ```

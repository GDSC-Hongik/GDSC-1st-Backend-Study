# B. Lambda and interface (in Java 8)
## B1. 람다 도입 배경
1. 빅데이터의 중요성 증대
2. 병렬화 기술의 중요성 증대
3. 자바 8에서는 병렬화를 위한 컬렉션을 강화함.
4. 컬렉션을 더 효율적으로 사용하기 위해 스트림이 강화됨.
5. **이 스트림을 더 효율적으로 사용하기 위해 함수형 프로그래밍이 도입되었고, 함수형 프로그래밍을 위해 람다가 도입됨.**
7. 람다를 위해 인터페이스의 변화가 수반됨. 이를 함수형 인터페이스라고 부름


람다식은 위와 같은 배경으로 등장하였다. 나도 개인적으로 js 코드를 짜며 참 많이도 람다식을 사용하는데, 자바에서는 어떻게 되어있는지 제대로 공부해보자.

## B2. 람?다
**람다란 코드 블록이다.** 람다는 아래와 같은 구조를 갖는다.
```java
(인자 목록) -> { 로직 }
```
예시는 아래와 같다.
```java
public static void main(String[] args) {
  Runnable runnable = () -> {
    System.out.prinln("Lambdai Lambdai Lambdaidam");
  }
}

OR

// 한 줄 일떄
public static void main(String[] args) {
  Runnable runnable = () -> System.out.prinln("Lambdai Lambdai Lambdaidam");
}
```
Runnable interface는 내부적으로 하나의 추상 메서드 `run()`을 갖는다. 그래서 위와 같은 구현은 run()을 구현한 것이 된다. 어차피 메서드가 단 하나여서 컴파일러가 헷갈릴 일은 없다.

## B3. 함수형 인터페이스
직전에 예시로서 등장한 `Runnable` interface는 `run()`이라는 추상 메서드 하나만 가지고 있다고 하였다. <br> 

이렇게 추상메서드를 하나만 갖는 인터페이스를 **자바 8 부터 함수형 인터페이스라고 한다.** <br> **이러한 함수형 인터페이스를 람다식으로 변경할 수 있다!!**


### 직접 함수형 인터페이스 선언해보기!
```java
@FunctionalInterface
interface MyFunctionalInterface {
  public abstract int runSomething(int cnt);
}
```
위와 같은 인터페이스가 있다. 단 하나의 추상 메서드를 가지고 있다. <br> 메서드 위의 `@FunctionalInterface` 어노테이션은 해당 함수가 함수형 인터페이스의 조건에 맞는지 확인해주는 어노테이션이다. 필수는 아니지만, 당연히 달아주는게 좋을 것 같다.

<br>

실제로 이용해보자.

```java
public static void main(String[] args) {
  // 기본
  MyFunctionalInterface test = (int a) -> { return a*a; };

  // 다양한 예시들
  // interface의 정의상 첫 인자는 int일 수 밖에 없어서 가능한 표현
  MyFunctionalInterface test = (a) -> { return a*a; };
  MyFunctionalInterface test = a -> { return a*a; };
  // 이 세미클론은 return이 아닌, 원래 코드 블럭의 것.
  MyFunctionalInterface test = a -> a*a;
}
```


## B.4 메서드 호출 인자로 람다 사용
내가 js에서 자주 쓰는 형태. 굳이 변수에 담아둘 필요가 없다. <br>
int 변수를 인자로 받는 메서드라면 아래와 같은 이용이 가능하다.
```java

public static void intMethod(MyFunctionalInterface test) {
  // 메서드 내용물
}

...

intMethod(a -> a * a);
```

## B.5 메서드 반환값으로 사용
```java
public static MyFunctionalInterface todo() {
  return num -> num * num;
}

...

MyFunctionalInterface test = todo();
test.runSomthing(3);

```


## B.6 자바 8 API의 함수형 인터페이스
## B.7 컬렉션 스트림에서 람다 사용

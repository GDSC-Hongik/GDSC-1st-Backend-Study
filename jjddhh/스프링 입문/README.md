# 스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술

<br/>

## 프로젝트 환경설정

#### - build.gradle 에 있는 설정들의 의미

![Gradle 설정 정보](https://user-images.githubusercontent.com/92728780/192406327-eb5455e2-0f84-44a2-adbf-c8cd3872aba7.JPG)


```
항상 보던 설정들 이였지만, 

어렴풋이 의미를 유추하고만 있을 뿐 정확히 의미하는 바에 대해서는 알고 있지 못했습니다. 
```
[runtimeOnly와 implementation와 testImplementation의 차이](https://giron.tistory.com/101)

<br/>

## 스프링 웹 개발 기초

#### - jar 파일 이름 생성 기준
```
서버 배포 시에 jar 파일명을 명시해줘야 하는 상황이 있었습니다.

그때 jar 파일명 생성기준 및 변경법을 알지 못하여 애를 먹었던 적이 있었습니다.
```
인텔리제이에서 스프링부트 프로젝트를 배포할 때 빌드툴인 Gradle 을 사용해서 jar 파일을 생성하는 방식을 많이 사용한다.

이때 아무런 설정을 하지 않았다면, '프로젝트명-1.0-SNAPSHOT.jar' 형식의 이름으로 jar 파일이 생성된다.

[jar 파일명 생성 기준 및 파일명 변경법](https://scshim.tistory.com/236)

<br/>

## 회원 관리 예제 - 백엔드 개발

#### - Test 실행 순서
테스트 실행 순서는 보장되지 않는다. 따라서 테스트가 다른 테스트와 연관되도록 작성되서는 안된다.
```
ex) 1번 테스트에서 db에 data 설정 후에, 2번 테스트에서 설정한 data 를 기반으로 test 작성 => 잘못된 구조
```
위의 예시와 같이 초기 data 설정이 필요한 경우에는 @BeforeEach 등의 Annotation 을 사용하여 설정할 수 있다.

[JUnit5 사용법](https://gmlwjd9405.github.io/2019/11/26/junit5-guide-basic.html)

<br/>

## 스프링 빈과 의존관계

#### - 의존관계 주입 방법
1. 필드 주입
```java
public class MemberController{
    
    @Autowired private MemberSerivce memberSerivce;
    
    ...
}
```
문제점 - 스프링 시작시에 자동으로 memberService 에 di 가 일어나므로,

test 작성시와 같이 memberService 에 들어갈 객체를 바꿔야 하는 경우 문제가 생긴다.

<br/>

2. setter 주입
```java
public class MemberController{

    private MemberSerivce memberSerivce;

    @Autowired
    public void setMemberSerivce(MemberService memberSerivce) {
        this.memberSerivce = memberSerivce;
    }
    
    ...
}
```
문제점 - 실행 중간에 수정될 이유가 없는 memberService 가 
런타임에 setMemberService() 에 의해서 수정될 여지가 생긴다.

<br/>

3. 생성자 주입
```java
public class MemberController{

    private MemberSerivce memberSerivce;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberSerivce = memberService;
    }
    
    ...
}
```
위 두 개의 문제점이 전부 해결된다.

- 생성자 호출시에 파라미터를 통해 memberService 에 원하는 객체를 넣을 수 있다.
- 생성시에 memberService 에 값이 들어오므로 런타임에 수정될 여지가 없다.

> 생성자가 한 개만 있을 경우에는 @Autowired 생략가능.

<br/>

#### - 스프링 컨테이너에 bean 등록이 되어야 @Autowired 을 통한 di 가 일어난다.

<br/>

## 스프링 DB 접근 기술

#### - DDL (데이터 정의 언어)
데이터베이스를 정의하는 언어이며, 데이터를 생성, 수정, 삭제하는 등의 데이터의 전체의 골격을 결정하는 역할을 하는 언어이다.

[DDL, DML, DCL 이란?](https://cbw1030.tistory.com/71)

<br/>

#### - DB 접근 기술의 흐름

```
1. 순수 JDBC
- DataSource 를 사용하여 Connection 설정부터 예외 처리, 쿼리 작성까지 많은 작업들을 일일이 설정해줘야한다.

2. 스프링 JdbcTemplate
- 순수 JDBC 에 있던 Connection 설정이나 예외 처리와 같은 반복 작업들을 내부적으로 처리해준다. 
하지만 쿼리는 직접 작성해야 한다.

3. JPA
- JPA는 기존의 반복 코드는 물론이고, 기본적인 SQL도 JPA가 직접 만들어서 실행해준다. 
또한 SQL과 데이터 중심의 설계에서 객체 중심의 설계로 패러다임을 전환할 수 있다.

4. 스프링 데이터 JPA
- 리포지토리에 구현 클래스 없이 인터페이스 만으로 개발을 완료할 수 있다. 
그리고 반복 개발해온 기본 CRUD 기능도 스프링 데이터 JPA가 모두 제공한다.
```

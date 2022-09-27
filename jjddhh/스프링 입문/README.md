# 스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술

## 프로젝트 환경설정

#### - build.gradle 에 있는 설정들의 의미
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
인텔리제이에서 스프링부트 프로젝트를 배포할 때 빌드툴인 Gradle 을 사용해서 jar 파일을 생성하는 방식을 많이 사용합니다. 
이때 아무런 설정을 하지 않았다면, '프로젝트명-1.0-SNAPSHOT.jar' 형식의 이름으로 jar 파일이 생성될 것입니다.

[jar 파일명 생성 기준 및 파일명 변경법](https://scshim.tistory.com/236)

<br/>

## 회원 관리 예제 - 백엔드 개발

#### - Test 실행 순서
테스트 실행 순서는 보장되지 않는다. 따라서 테스트가 다른 테스트와 연관되도록 작성되서는 안된다.
```
ex) 1번 테스트에서 db에 data 설정 후에, 2번 테스트에서 설정한 data 를 기반으로 test 작성 => 잘못된 구조
```
위의 예제와 같이 초기 data 가 필요한 경우에는 @BeforeEach 등의 Annotation 을 사용할 수 있다.

[JUnit5 사용법](https://gmlwjd9405.github.io/2019/11/26/junit5-guide-basic.html)




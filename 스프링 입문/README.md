# 스프링 입문

## 강의 소개

---

- 간단한 스프링 앱을 만드는 것이 목표
- 스프링 기술보다는 어떻게 사용하는지
- 오래된, 마이너한, 실무에서 사용 X 다루지 않음
- 직접 코딩해보기!

## 프로젝트 생성

---

- start.spring.io에서 스프링 프로젝트 생성
- Maven vs Gradle?
    - 라이브러리, 빌드 관리해주는 툴
    - 요즘에는 Gradle을 많이 사용함
- SpringBoot Version
    - 뭐가 붙어있으면 정식 릴리즈 아님
- 메타데이터
    - Group - 그룹명
    - Artifact - 빌드 결과물 이름
    - Name- 이름
- Dependencies
    - 어떤 라이브러리를 쓸 것인지?
    - Spring Web
        - 스프링을 사용한 웹 개발
    - Thymeleaf
        - HTML 기반으로 뷰 템플릿을 만들어주는 엔진
            - [https://colinch4.github.io/2021-06-07/thymeleaf/](https://colinch4.github.io/2021-06-07/thymeleaf/)
        - 다양한 템플릿 엔진을 사용함
- 다운 후 인텔리제이에서 build.gradle 열기
- 폴더 구조
    - src 폴더는 main, test 폴더로 구분됨
    - 실제 파일은 main에 있고, 테스트 코드를 test에 작성
    - 기본적으로 나눠진 이유는 테스트 코드 작성이 중요하기 때문!
- build.gradle
    - 설정 파일. 스프링부트에서 기본적인 세팅을 제공한다.
    - 버전 설정 / 의존성 관리 정도로만 알아두면 됨
    - dependencies
        - 아까 추가했던 라이브러리 (spring web, thymeleaf가 추가되어 있음)
        - 이외에도 JUnit이라는 테스트 코드 관련 라이브러리가 자동으로 추가되어 있다
    - repositories
        - 이러한 라이브러리를 mavenCentral()이라는 곳에서 다운로드 받는 것
- main - HelloSpringApplication에서 main method 실행
    - Tomcat started on port:8080
    - [localhost:8080](http://localhost:8080) 접속 시 에러 페이지가 뜨는데, 이러면 정상적으로 실행된 것임
    - springboot에 내장된 tomcat 웹서버를 자체적으로 실행함
- Settings - Build, Execution, Deployment - Build Tools - Gradle
    - Build and run using과 Run tests를 모두 InteliJ로 변경
    - Gradle 통해서 실행하면 느릴 때가 있음
    - 변경 시 인텔리제이에서 자바 직접 실행
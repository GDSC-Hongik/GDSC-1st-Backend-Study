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
  
## 라이브러리 살펴보기

---

- build.gradle에는 3개밖에 없지만… external 라이브러리 보면 같이 엮여서 받은 것들이 굉장히 많다. tomcat, junit 등등…
- gradle이나 maven 같은 것들은 (이걸 빌드 관리 도구라고 한다) 특정 라이브러리와 의존 관계에 있는 다른 라이브러리를 자동으로 관리해준다.
    - 즉, A라는 라이브러리를 사용한다 했을 때 A를 구동하기 위해 필요한 다른 라이브러리 B, C, … 와, B, C, …를 구동하기 위해 필요한 또 다른 라이브러리를 **모두** 다운받아주는 것이다.
    - 빌드 관리 도구인 gradle과 maven의 차이에 대해서는 아래 링크를 참고하자.
        - [https://hyojun123.github.io/2019/04/18/gradleAndMaven/](https://hyojun123.github.io/2019/04/18/gradleAndMaven/)
        - 둘이 수행하는 역할과 어떤 것이 더 좋은지, 왜 좋은지에 대한 설명이 있다.
- IJ 좌하단 버튼 (혹은 Alt 더블 클릭)을 통해 우측에서 Gradle을 확인해보자.
    - Dependencies-complieClasspath를 확인하자.
    - tyhymeleaf에 필요한 라이브러리, springboot starter에 필요한 라이브러리… 가 트리 구조로 엮여있음을 확인할 수 있다.
    - (*) 은 위에서 이미 존재하는 라이브러리를 표시한 것이다.
    - springboot starter web을 확인하자.
        - 아까 웹서버 관련해서 언급했던 tomcat이 존재한다.
            - tomcat은 WAS(Web Application Server)이다.
            - WEB, WAS, tomcat에 대한 설명은 아래 링크를 참고하자.
                - [https://helloworld-88.tistory.com/71](https://helloworld-88.tistory.com/71)
            - 예전에는 tomcat에 자바 코드를 넣는 식…
            - 요즘에는 이렇게 소스 코드 라이브러리에 tomcat이 내장되어 있다.
            - 즉 소스 코드를 실행만 하더라도 알아서 웹서버가 실행됨 + 접속 가능
        - spring core나 logging 관련 라이브러리도 있음
            - log는 굉장히 중요…
            - 현업에서는 println 대신 log로 출력해야 함
                - [로깅을 System.out.println()으로 하면 안되는 이유](https://hudi.blog/do-not-use-system-out-println-for-logging/)
                    - 로그된 데이터가 파일로 저장되지 않는다
                    - 에러 발생 시 추적할 수 있는 최소한의 정보가 남지 않는다
                        - 문제 발생 날짜, 레벨, 위치 등 최소한의 정보가 기록되지 않는다
                    - 로그 출력 레벨을 사용할 수 없다
                        - 로그 출력 레벨은 ‘에러/장애 발생에 대해 문제를 진단할 수 있을 정도의 정보’만을 남기기 위해 존재한다.
                        - 이렇게 하지 않으면 문제 해결을 위한 중요한 정보를 얻기 힘드며, 민감한 정보를 로그로 남기게 된다.
                        - 대표적으로 Logback 라이브러리는 TRACE, DEBUG, … , FATAL 등의 레벨이 존재하며, 레벨에 따라 어떤 정보를 기록할 것인지가 달라진다.
                    - 성능저하의 원인이 될 수 있다
                        - `System.out.println()`의 구현을 보면, `newline()`을 호출하는 것을 알 수 있다. `newline()`의 구현에는 `synchronized` 키워드가 존재한다. 이 키워드는 무엇이고, 왜 필요한지에 대해 먼저 알아보자.
                        - 멀티 쓰레드 환경에서, 스레드 간 공유하는 데이터에 동시에 접근하는 경우 문제가 발생할 수 있다.
                        - 문제가 발생하는 영역을 임계 영역(critical session)이라 한다. 이를 해결하기 위해 ‘동기화’가 필요하다. 동기화는 한 쓰레드에서 작업을 마치기 전까지 다른 쓰레드에서 공유 데이터에 접근하는 것을 막는 것이다.
                        - 이 동기화를 가능하게 해주는 것이 `synchronized` 키워드이다.
                        - 따라서, `synchronized`를 사용하면 multi-thread에서 데이터의 thread-safe를 보장할 수 있다.
                        - 하지만, 동시에 다른 쓰레드의 오버헤드를 발생시킬 수 있다. 한 쓰레드가 작업을 끝낼 때까지의 대기시간이 생기기 때문이다.
                        - 좀 더 쉽게 설명하자면, 콘솔에 하나의 내용이 프린트 혹은 저장되기 전까지 다음 내용이 프린트될 수 없다는 것이다.
                        - 톰캣, 즉 WAS는 멀티 쓰레드 환경이다. 위에서 언급한 문제들이 발생할 수밖에 없다.
                        - [https://coding-start.tistory.com/68](https://coding-start.tistory.com/68)
                        - [https://tourspace.tistory.com/54](https://tourspace.tistory.com/54)
                        - [https://zbomoon.tistory.com/19](https://zbomoon.tistory.com/19)
                        - [https://velog.io/@jsj3282/10.-로그는-반드시-필요한-내용만-찍자](https://velog.io/@jsj3282/10.-%EB%A1%9C%EA%B7%B8%EB%8A%94-%EB%B0%98%EB%93%9C%EC%8B%9C-%ED%95%84%EC%9A%94%ED%95%9C-%EB%82%B4%EC%9A%A9%EB%A7%8C-%EC%B0%8D%EC%9E%90)
                - ref. 자바 성능 튜닝 이야기

              > 한 번 요청할 때 5,000명의 사용자 정보를 요청하고, 처리하는 과정에서 응답 시간이 약 20초에 달하는 사이트가 있었다. 원인을 파악해 본 결과 애플리케이션이 5,000명의 정보를 모두 System.out.println()으로 처리하도록 되어 있다. 이 한 줄을 지우자 응답 시간은 6초로 개선되었다.
              >
                - java로 알고리즘을 하는 경우에도 CPU 자원을 많이 먹는 등의 이슈로 println 대신 BufferedWriter를 사용한다고 한다.
                    - [https://code0xff.tistory.com/10](https://code0xff.tistory.com/10)
                    - [https://donggov.tistory.com/53](https://donggov.tistory.com/53)
            - 다시 본론으로 돌아오자면 logback, slf4j같은 로깅 관련 라이브러리가 있는 것을 확인할 수 있음
            - slf4j는 일종의 인터베이스, 실제로 많이 사용하는 구현체는 logback을 많이 사용.
                - 이 둘을 표준같은 느낌으로 사용중
                - 그러면 나머지 log4j2와 logback 중 어떤 것이 더 좋을까?
                - 멀티쓰레드 환경에서의 비동기 로거의 경우 log4j2가 더 빠르다고 한다.
                    - [log4j, Lockback, log4j2 프로젝트에 무엇을 적용하는게 좋을까?](https://junghyungil.tistory.com/160)
                - log4j2의 경우 어디서 많이 본 이름이다 싶었는데 무슨 보안 이슈 생겼다고 난리였던 그 라이브러리였다. 지금은 해결되었다고 하지만, 잘 알아보고 써야할 듯?
                    - [https://namu.wiki/w/Log4j 보안 취약점 사태](https://namu.wiki/w/Log4j%20%EB%B3%B4%EC%95%88%20%EC%B7%A8%EC%95%BD%EC%A0%90%20%EC%82%AC%ED%83%9C)
        - 테스트와 관련된 라이브러리도 있다.
            - junit - 테스트 관련 프레임워크. 4에서 5로 넘어가는 중
            - mockito: 목 라이브러리
            - assertj: 테스트 코드를 좀 더 편하게 작성하게 도와주는 라이브러리
            - spring-test: 스프링 통합 테스트

테스트 외 라이브러리는 아래에서 다시 한번 정리해보자.

스프링부트 라이브러리

- spring-boot-starter-web
- spring-boot-starter-tomcat 톰캣 (WAS)
- spring-webmvc: 스프링 웹 MVC
- spring-boot-starter-thymeleaf: 타임리프 템플릿 엔진
- spring-boot-starter (공통): 스프링 부트 + 스프링 코어 + 로깅
    - spring-boot
        - spring-core
    - spring-boot-starter-logging
        - logback, slf4j, log4j

## View 환경설정

---
# 로그

- 소프트웨어의 이벤트를 기록하는 것
- 소프트웨어의 동작상태를 파악하고 문제가 발생했을 때 이 동작 파악을 통해서 소프트웨어의 문제를 찾아내고 해결하기 위해 디자인

## 로그의 중요성

- 로그를 보면 어떤 동작을 하고 있는지 혹은 어느 부분에서 에러가 났는지 쉽게 파악할 수 있다.
- 어떤 함수 호출에서 문제가 있었는지, 에러 메서지는 무엇인지, 어떤 값이 들어갔는지 등

→ 최대한 상세히 해당 오류에 대해 로그를 남겨주는게 좋다

# Log4j2란

- Java 기반 로깅 라이브러리
- Log4j의 문제점을 개선하여 나온 업그레이드 버전

## 구성 - 3가지 컴포넌트

- Logger : 프로그램으로부터 로깅 정보를 받고 로그 파일을 작성하는 클래스 자체
- Appender : 전달된 로그를 어디에 출력할 것인지 결정
    - Console, File, Socket, Mail, Message, DB 등
- Layout : 로그를 어떤 형식으로 출력할 것인지 결정
    - Pattern, Json, Yaml 등

## 로그 레벨

![image](https://user-images.githubusercontent.com/78093844/201034014-d9861fc5-d0d7-4902-9411-6c9196b76865.png)

- FATAL : 매우 심각한 에러
- ERROR : 의도하지 않은 에러 - 요청 처리 중 문제 발생
- WARN : 에러는 아니지만 잠재적 가능성이 있음
- INFO : 요구사항에 따라 시스템 동작을 보여줄 때 - 로그인, 상태 변경 같은 정보
- DEBUG : INFO 레벨보다 상세한 메시지 - 개발 시 디버그 용도로 사용
- TRACE : DEBUG 레벨보다 상세한 메시지

## 장점

- thread safe - 멀티스레드 환경에서도 안전하게
- 여러 Appender 지원 - 출력을 콘솔, 원격, Email, DB, 파일등 다양하게
- 명확한 기준 레벨 - 계층적인 로그 설정과 처리 가능

# Log4j2의 성능이 좋은 이유

- 많은 스프링 프로젝트에서 로거로 Log4j, Logback, Log4j2를 사용하고 있다.
- Spring Boot가 기본적으로 사용하는 Logback은 log4j에 비해 향상된 필터링 정책 및 기능을 제공하고 로그 레벨 변경 등에 대해 서버 재시작할 필요 없이 자동 리로딩을 지원한다.

![async](https://user-images.githubusercontent.com/78093844/201023243-4683d7c5-9d0a-45ce-9f53-3bc544209389.png)
![sync](https://user-images.githubusercontent.com/78093844/201029491-1443f75e-b363-467d-8a34-68b6a5605f01.png)
출처 : [https://logging.apache.org/log4j/2.x/performance.html](https://logging.apache.org/log4j/2.x/performance.html)

- Log4j2는 Logback보다 더 뛰어난 성능을 보여준다.
- 그렇다면  Log4j2는 무슨 차이점 때문에 Logback보다 좋은 성능을 보여주는 것일까?

## 비동기 방식 로깅

log4j2가 Logback에 비해 더 나은 성능을 보여줄 수 있는 건 강력한 성능의 비동기 방식 로깅 덕분이다.

> Log4j2에는 LMAX Disruptor 라이브러리를 기반으로하는 차세대 **비동기 로거**가 포함되어 있습니다. 다중 스레드 시나리오에서 비동기 로거는 Log4j 1.x 및 Logback보다 18 배 **더 높은 처리량과 몇 배 더 낮은 지연 시간을 제공**합니다. Log4j2는 특히 **다중 스레드** 응용 프로그램에서 Log4j 1.x, Logback 및 java.util.logging을 훨씬 능가합니다.
>

비동기 방식 로거는 Multi Thread 환경에서 다른 로깅 프레임워크보다 처리량이 훨씬 많고, 대기 시간이 훨씬 짧다. 따라서 스레드의 수가 증가할 수록 더 큰 차이를 보여준다.

```java
public void log() {
	for (int i = 0; i < 10; i++) {
		foo.bar();
		log.info("foo invoked");
	}
}
```

별도의 설정없이 로그 메시지를 남기면 동기적인 방식으로 파일 쓰기 작업이 진행된다. 즉, 로깅 메서드를 호출하는 시점(로그 발생)에 매번 파일 쓰기 작업이 수행된다. 파일 쓰기와 같은 I/O는 인메모리 작업이 아니라서 상대적으로 딜레이 및 부하가 크다.

반면 비동기 로깅을 사용하면 **로그 발생**과 **로그 쓰기**를 분리시킨다. 로깅 메서드가 호출되면(로그 발생), Thread A는 인메모리 Queue 혹은 Buffer에 로그 정보를 집어넣는다. Thread B는 Queue 혹은 Buffer에 쌓인 로그 데이터를 꺼내서 파일 쓰기만 수행한다.

로깅 메서드를 호출하는 시점(로그 발생)에 파일 I/O 작업이 바로 수행되지 않기 때문에, 애플리케이션의 성능이 빨라진다.

+ 약간의 단점

Queue 혹은 Buffer의 용량이 80%를 도달하면, WARN 레벨 미만의 로그는 큐에서 제거되는 등 로그가 유실 될 수 있다. 또한 Queue 혹은 Buffer에 로그가 쌓인 상태에서 프로세스가 종료되면, 해당 로그는 기록되지 않는다.

속도가 저하될 수 있어 Method Name 및 Line Number를 출력하지 않는다.

# 적용

- Log4j2와 로깅 설정을 위한 jackson-data-format-yml 의존성 추가

```yaml
# build.gradle
	implementation: 'org.springframework.boot:spring-boot-starter-log4j2'
	implementation group: 'com.fasterxml.jackson.dataformat', name" 'jackson-dataformat-yaml'

```

- 기본으로 내장된 spring-boot-starter-logging를 제외

```yaml
configurations {
	all {
		exclude group: 'org.springframework.boot', module: 'spring-boot-starter-logging'
	}
}
```

# SLF4J란

- Simple Logging Facade for Java
- 로깅 추상화 라이브러리
    - 로깅에 대한 추상 레이어를 제공하는 interface의 모음
- 로깅을 직접하지 않고 구현체(실제 로깅 라이브러리)를 찾아서 사용할 수 있도록 도와준다.

![slf4j](https://user-images.githubusercontent.com/78093844/201013755-997b66b7-756b-409f-9355-ce2dd5483831.png계)

- 사진을 보면 application(초록 박스)이 모두 SLF4J API(하늘색 박스)를 사용하고 있다.
- SLF4J를 사용하면 로깅 라이브러리가 무엇이든 하나의 통일된 방법으로 로그를 남길 수 있다.
- 따라서 라이브러리를 Logback에서 Log4j2로 변경하더라도 application의 코드를 변경할 필요가 없다.

→ 코드를 일정하게 유지하면서 구현체의 전환을 통해 다른 로깅 프레임워크로의 전환을 쉽고 간단하게 할 수 있다.
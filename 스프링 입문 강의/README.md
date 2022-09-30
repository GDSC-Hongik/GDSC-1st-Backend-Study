#기본

src폴더 안에 main, test 폴더로 나눠있음 / 요즘 test 코드가 매우 중요한 추세

main의 java파일에는 설정 들이 들어가 있고 나머지는 전부 resources

build.gradle이 중요 -> springboot 로 인해 직접 안쳐도 되게끔 나옴

repositories – mavenCentral() : 라이브러리를 다운 받는 곳을 넣어둔 것 다른 주소 넣어도됨

main-java-HelloSpringApplication 에서 puvlic class 어쩌구 run 하면 tomcat 있는 곳에 port 번호 뜸 (TomCat이란 웹서버를 내장하고 있대 )

##<라이브러리 살펴보기> - 한번 듣고 있구나 정도만

springboot 를 쓰면 spring core까지 spring관련된거 전부 땡겨온다고 생각하면 됨

실무->log : 오류 잡을 때 사용 / 강의 -> system println out 어쩌고

테스트라이브러리 -> junit이 기본

##<view 환경 설정>
src-main-resources-static-index.html생성  welcome 페이지

thymeleaf템플릿 엔진 : 정적 파일이 아니라, 루프를 넣거나 해서 바꿀 수 있음

hello.hellospring 아래에 controller 패키지(폴더) 만듬

그 안에 java class로 HelloController 만든 뒤에 @Controller 해줘야함

@GetMappint(“hello”) : 웹어플리케이션에서 /hello라고 들어오면 이 매서드를 호출해줌

return “hello”; : resources/templates/hello.html을 찾아서 랜더링 하라는 뜻 즉, 이 화면을 실행시켜라

##<빌드하고 실행하기>

지금까지는 Intellij에서 실행을한거고 진짜 빌드를 한대

1.gradlew build

2. cd build/libs

3. java -jar hello-spring-0.0.1-SNAPSHOT.jar

./gradlew clean  build 폴더없어짐

#[스프링 웹 개발 기초]

<정적 컨텐츠> -> 파일을 그대로 보내준다.

-스프링 부트는 정적 컨텐츠를 기본으로 제공한다. /static에 그냥 html파일 넣으면 “ localhost:/넣은파일이름 “ 에 그대로 나타남

- 대신 컨트롤러가 있으면 컨트롤러가 동적으로 바꿔주는듯

##<MVC와 템플릿엔진> 템플릿엔진을 MVC로 쪼개서 view를 템플릿엔진으로 html을 프로그래밍 한 랜더링 한 것을 클라이언트에게 전달해줌

MVC: Model,View,Controller  과거에는 View와 Controller가 합쳐져 있었음

view-> 화면 관련/ Controller -> 서버 뒷단에 관련된 로직, 내용 / Model ->여기에 담아서 화면에 표현

##<API> -> 객체를 반환하는 것 (JSON으로 대부분 해줌)

@ResponseBody -> http에 그대로 데이터를 던져버리는데 이때 데이터가 객체라면, 데이터를 Json 형식으로 데이터를 만들어서 보내줌

#[회원관리예제 -백엔드 개발]

<비즈니스 요구사항 정리> -서비스 예시 ) 회원은 중복 가입이 안된다.
-Alt Enter -> import

##<회원 리포지토리 테스트 케이스 작성>
Test순서는 보장이 안됨 -> 따라서 순서 상관 없이 매서드 별로 따로 동작하게 설계해야함
따라서 Test가 끝나면 매서드별로 데이터를 clear 해주는 코드 필요
: @AfterEach , public void afterEach(){ repository.clear해주는 함수 }
\*TDD (테스트 주도 개발) : 테스트 먼저 만들고 구역 클래스를 만들어서 돌려보는 것

<회원 서비스 개발>
result.ifPresent : 이전에는 if null 이면 으로 했지만 Optional 덕분에 ifPresent 같은 매서드 사용 가능해짐 \*메서드 추출  추출할 부분 드래그 + CTRL + ALT + SHIFT + T

<회원 서비스 테스트>
*Ctrl + shift + t  create test
*static import  alt + enter 2번눌러서 선택
*변수 추출하기 ctrl + alt + v
*shift + F10  이전에 실행했던 것 다시 실행해줌 ( run 등 ) -테스트는 한글로 적어도 됨 -> build될 때 포함되지 않으므로
-Illegalstateexception : 메소드를 호출하기 위한 상태가 아닐 때
-try catch문으로 예외를 테스트해볼 수도 있지만 , assertThrows로 더 많이 사용
assertThrows(예외상황, 로직)
-Dependency Injection(의존관계주입) : memberservice 직접 new하지 않고 외부에서 넣어주는 것

[스프링 빈과 의존관계]
<컴포넌트 스캔과 자동 의존관계 설정>

- @Controller 를 보고 스프링이 뜰 때 객체를 생성해서 들고 있음  “스프링 컨테이너에서 빈이 관리된다.”
- 스프링 컨테이너에 등록해놓고 공용으로 쓰는게 더 좋음
  -@Controller , @Service , @Repository : Controller를 통해서 외부 요청 받고, Service에서 비즈니스 로직 만들고, Repository에서 데이터 저장하는 정형화된 패턴
- 스프링 빈 등록 2가지 방법

1.  컴포넌트 스캔 방식 -> 우리가 한 @ 가 컴포넌트 스캔 방식 / autowired : 서로 연결해줌
2.  자바코드로 직접 스프링 빈 등록 -> 그 다음 강의
    Q. 아무데나 @Component가 있어도 되는가?  기본은 안됨. 제일 기본 클래스에 있는 패키지를 포함한 하위에서만 가능

- 스프링 컨테이너에 스프링 빈 등록 시 유일하게 하나만 등록

<자바 코드로 직접 스프링 빈 등록하기>

- DI는 생성자 주입 방식이 요즘 가장 편함

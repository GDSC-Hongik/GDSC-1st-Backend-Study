## ioc / di

[더 자바 코드를 조작하는 다양한 방법 (백기선)](https://inf.run/tEyE) 님의 강의중 나만의 di 프레임워크 만들기 참고했습니다.

### 컴포넌트를 어떻게 찾을까?

```java

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface Component {

	/**
	 * The value may indicate a suggestion for a logical component name,
	 * to be turned into a Spring bean in case of an autodetected component.
	 * @return the suggested component name, if any (or empty String otherwise)
	 */
	String value() default "";

}
```

![java byte code](https://user-images.githubusercontent.com/13329304/195346975-a246aeee-8cf0-414d-81eb-0584436a79fe.jpg)

어노테이션 인터페이스 선언...

컴포넌트를 찾는 방식?

- 어노테이션을 달면은 리플렉션 써서 얼마든지 어노테이션에 대한 정보를 받아올 수 있음

- 어노테이션 단 클래스는 어떻게 찾을 껀데?

위 관점에서 시작해야함.

- nest js 같은경우 모듈에 일일히 집어넣을 요소를 입력.
  ![nest js module](https://user-images.githubusercontent.com/13329304/195347003-2a2e9de4-a128-43a3-a50b-bea714108797.jpg)

왜 ? 입력을 안하면은 클래스가 있는지 없는지를 모름

하지만 자바는 가능 컴파일 이후에 바이트 코드라는것을 만듬. 바이트 코드를 메모리에 올리면서 실행되는것인데

java 코드를 class 코드로 만든 바이트 코드 정보에서 어노테이션 정보를 얻을 수 가 있음

이정보에서 @component 어노테이션이 달린 클래스들의 목록만 가지고 있으면

해당 클래스에 메소드에서 빈을 등록하는지 , autowired해서 di해줄게 있는지등 의존성 주입이 가능해지게 되는 것임.

-> 여기서 의존성 주입을 받고싶으면 해당 클래스도 빈으로 등록 되어야 한다는 말이 생긴것임 이이유임.
( 주관적인 해석 )

### 바이트 코드를 어떻게 해석...?

스프링은 asm 이라는 걸 이용해서 ( 버저닝 때문에 힘들어서 스프링 코어 밑으로 패키징 함 )

- ClassPathScanningCandidateComponentProvider
  ![ClassPathScanningCandidateComponentProvider](https://user-images.githubusercontent.com/13329304/195346996-87b57294-9638-42f2-84aa-c30b72bf3869.jpg)
- SimpleMetadataReader
  ![simpleMetadataReader](https://user-images.githubusercontent.com/13329304/195346989-b5104487-d647-4d0b-b014-e4f856453247.jpg)

팩토리에서 받아오는 거임
비지터이용해서 어노테이션 정보받아옴...
-> 클래스 로더가 있는데 클래스 로더에 따라서 등록되는 정보가 바뀌나...? 암튼 여기까지

### 컴포넌트 스캔 ok 의존성 주입 할거는 어떻게 찾음?

-> 리플렉션 -> 클래스의 메타데이타에 접근할수 있는 api

어노테이션이 달린 클래스에 메타데이터를 보면

```java

@Component
public class Test {
    @Autowired
    private int a;

    public Test() {
    }
}
```

빈에 등록된 컴포넌트 중에서 의존성 주입이 필요한 부분에 끼어넣는 식임.

### 어노테이션

```java

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface Component {

	/**
	 * The value may indicate a suggestion for a logical component name,
	 * to be turned into a Spring bean in case of an autodetected component.
	 * @return the suggested component name, if any (or empty String otherwise)
	 */
	String value() default "";

}
```

- target

어노테이션을 달수있는 위치를 지정. 클래스냐 ( 타입 ) , 메소드냐 필드냐 등등 정할 수 있음

- Retention

이 어노테이션을 얼마나 유지 시킬건지?

- inherit

어노테이션 상속할껀지

```java
  public enum RetentionPolicy {
   /**
    * Annotations are to be discarded by the compiler.
    */
   SOURCE,

   /**
    * Annotations are to be recorded in the class file by the compiler
    * but need not be retained by the VM at run time.  This is the default
    * behavior.
    */
   CLASS,

   /**
    * Annotations are to be recorded in the class file by the compiler and
    * retained by the VM at run time, so they may be read reflectively.
    *
    * @see java.lang.reflect.AnnotatedElement
    */
   RUNTIME
}

```

```java


@TestAnno
public class Test {

}

```

![source anno](https://user-images.githubusercontent.com/13329304/195346994-6cb20ba8-4d3f-4563-a34e-dcf9f98d23e1.jpg)
이러면 바이트 코드에 어노테이션 정보가 없음

### di 프레임 워크 만들기

```java
public class ContainerService {


//사용 예시
    public  void bookservice (){
      // GetObject 해서 클래스의 타입을 명시를 해줘야함
      // 스프링이 잘하는 점은 바이트 코드를 통해서 이러한 클래스들의 목록을 미리 조사했기 때문임.
      // 그래서 di 가 편해지는거임
        BookService bookService = ContainerService.getObject(BookService.class);
    }


    public static <T> T getObject(Class<T> classType) {
        T instance = createInstance(classType);
        // 리플랙션 사용해서 클래스 의 메타데이터 정보를 빼올 수 있음
        Arrays.stream(classType.getDeclaredFields()).forEach(field -> {
            if(field.getAnnotation(Inject.class) != null){
              // 이부분에서 흔히 말하는 scope ( 싱글톤 , 요청별 등 ) 설정 가능
              // 빈에 등록된 인스턴스들을 받아와서 주입 하면됨
                Object fieldInstance = createInstance(field.getType());
                field.setAccessible(true);
                try{

                  // 필드에 해당 인스턴스를 셋 해주는거임
                  // 주의할점은 런타임에 실행된다는 점?

                    field.set(instance , fieldInstance);
                }catch (IllegalAccessException e){
                    throw  new RuntimeException(e);
                }
            }
        });
        return instance;
    }
    @NotNull
    public static <T> T createInstance(Class<T> classType)
       {
           try{
               return classType.getConstructor(null).newInstance();
           }catch (  InstantiationException | IllegalAccessException| InvocationTargetException| NoSuchMethodException e){
                throw new RuntimeException(e);
           }
    }
}



```

## proxy

![1200px-Proxy_pattern_diagram svg](https://user-images.githubusercontent.com/13329304/199503839-f17411f2-35bf-4b46-908c-74306aefa11e.png)
프록시 기본은 인터페이스를 통해서
공통의 인터페이스를 상속받아서
원본 클래스 앞뒤로 메소드 사이에 어떤 동작을 넣으면 됨.

- 공통으로 바라볼 인터페이스

```java
public interface Bookservice {
    public void rent(Book book);

}
```

- 실 구현체

```java
@Service
public class BookServiceImpl implements  Bookservice{
    @Autowired
    public BookServiceImpl() {
    }
    public void rent(Book book){
        System.out.println("rent : " + book.getTitle());
    }
}
```

- 프록시로 감쌈

```java
@Service
public class BookServiceProxy implements Bookservice{
    private final Bookservice bookService ;
    @Autowired
    public BookServiceProxy(@Qualifier("bookServiceImpl") Bookservice bookService) {
        this.bookService = bookService;
    }
    @Override
    public void rent(Book book) {
        //추가하고싶은 로직 추가
        System.out.println("추가하고싶은 로직 추가 위 " );
        // 원래 기본 로직
        bookService.rent(book);
        // 추가하고 싶은 로직 추가
        System.out.println("추가하고싶은 로직 추가 아래 " );
    }
}
```

- 클라이언트

```java
 Bookservice bookServiceProxy = cx.getBean("bookServiceProxy", Bookservice.class);
        bookServiceProxy.rent(new Book("테스트 북"));

```

- 단점은 컴파일 타임에 이루어진다는 것임!

## 다이나믹 프록시

- 컴파일 타임 이후에 동적으로 프록시를 생성할려면?

```java
Bookservice bookservice = (Bookservice) Proxy.newProxyInstance(
            Bookservice.class.getClassLoader(), new Class[]{Bookservice.class},
            new InvocationHandler() {
                Bookservice bookservice= new BookServiceImpl();
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if(method.getName().equals("rent")){
                        //추가할부분 위에

                        Object invoke = method.invoke(bookservice,args);
                        //추가할부분 밑에
                        return invoke;
                    }
                    return method.invoke(bookservice,args);
                }
            }
        );

bookservice.rent(new Book("테스트 북"));
```

- 이방법도 한계가있음 인터페이스만 지원을함 프록시생성을 위해서

## 클래스일경우? cglib

- 바이트 코드를 조작하는 cglib 를 통해서 클래스를 프록시로 만들수 있음 enhancer은 스프링이래핑한 cglib

```java
  // 만약 클래스라면...?
        MethodInterceptor handler = new MethodInterceptor() {
            BookServiceClass bookServiceClass = new BookServiceClass();
            @Override
            public Object intercept(Object o, Method method, Object[] objects,
                MethodProxy methodProxy) throws Throwable {
                System.out.println("rent : ");
                return method.invoke(bookServiceClass, objects);
            }
        };

        // Enhancer ( CGLIB 꺼임 )
        BookServiceClass cglibProxy = (BookServiceClass) Enhancer.create(BookServiceClass.class, handler);
        cglibProxy.rent(new Book("asdfadsf"));

        System.out.println(cglibProxy.getClass());


```

![무제](https://user-images.githubusercontent.com/13329304/199504425-87f8c908-e931-4a79-b8e8-037e27bfe0e4.jpg)

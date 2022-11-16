# CQS / CQRS

### Command vs Query

- Command(명령)
    
    : 결과를 반환하지 않고, 시스템의 상태를 변경한다.
       함수 간 사용순서를 신중하게 짜야하고 부주의하게 실행하면 전혀 예상못한 결과가 나올 수 있음
    
- Query(질의)
    
    : 결과값을 반환하고, 시스템의 상태를 변화시키지 않는다.
    
       → 즉, side-effect가 없다
    

### CQS (Command Query Seperation)

: 결과값을 반환하는 즉, 조회의 기능을 하는 Query와 상태를 변경 시키는 Command를 분리하는 것

CQS 적용 전

```jsx
public boolean hasNext() {
    return this.count-- > 0;
}
```

CQS 적용 후

```jsx
public boolean hasNext() {
    return this.count > 0;
}

public void next() {
    this.count--;
}
```

- CQS를 사용하지 않는 경우
    1. 코드 역할/ 책임 모호
    2. 코드가독성 떨어짐
    3. 시스템이 복잡해질 수록 유지보수성이 떨어짐
        
         → 커맨드와 쿼리는 코드 변경 빈도, 다루는 데이터 등의 서로 다르다.
        
- CQS의 예외 예시 : stack의 pop()
    
    → 스택의 맨 위의 값을 빼서(command) 반환(query)하는 일까지 병행
    
- CQRS ( Command Query Responsibility Segregation )
    
    CQRS는 CQS 원리에 기원 → 처음엔 CQS의 확장으로 얘기됨
    
    하지만 CQS는 명령과 조회를 **연산 수준**에서 분리하는 반면, CQRS는 개체(object)나 시스템 수준에서 분리
    

---

이게 웹 애플리케이션에서 얻는 이점이 있다기 보다는, 개발 전반에 기본개념으로 깔고 가시는 것이 좋습니다.

**이 메서드를 호출 했을 때, 내부에서 변경(사이드 이펙트)가 일어나는 메서드인지, 아니면 내부에서 변경이 전혀 일어나지 않는 메서드인지 명확히 분리하는 것이지요.**

그렇게 되면 데이터 변경 관련 이슈가 발생했을 때, 변경이 일어나는 메서드만 찾아보면 됩니다.

정말 크리티컬한 이슈들은 대부분 데이터를 변경하는 곳에서 발생하지요.

변경 메서드도 변경에만 집중하면 되기 때문에 유지보수가 더 좋아집니다.

제가 권장하는 방법은 insert는 id만 반환하고(아무것도 없으면 조회가 안되니), update는 아무것도 반환하지 않고, 조회는 내부의 변경이 없는 메서드로 설계하면 좋습니다

[https://justhackem.wordpress.com/2016/09/17/what-is-cqrs/](https://justhackem.wordpress.com/2016/09/17/what-is-cqrs/)

[https://dundung.tistory.com/183](https://dundung.tistory.com/183)
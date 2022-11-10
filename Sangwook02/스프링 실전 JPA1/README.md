# 연관 관계 핵심

 
## 1. 방향
    
DB에서는 방향의 개념이 없지만, 객체는 방향이 있어야한다.
 
방향이 있어야 다른 객체를 조회할 수 있기 때문이다.

다른 객체를 조회하기 위해 참조를 위한 필드가 class에 존재한다.

아래의 경우를 살펴보자.
```java
public class OrderItem {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
}
```

OrderItem에는 Item 객체를 참조하기 위한 필드가 있지만, 
Item class에는 OrderItem 객체를 참조하기 위한 
필드가 존재하지 않는다.

따라서, 이는 단방향이다.

```java
public class Order {
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();
}
```
Order class를 살펴보면 OrderItem을 참조하기 위한 필드가 존재한다.

따라서, OrderItem과 Order는 양방향이라고 할 수 있다.

## 2. 관계의 주인

연관 관계에 있는 두 객체 A와 B가 양방향 관계에 있다면 A와 B중에 주인이 있어야한다.

양방향 관계는 사실 두 개의 단방향 관계이므로 하나의 양방향 관계로 보이려면 이들 중 주인을 지정해줘야 하는 것이다.

연관 관계에서 주인인 객체만 읽기, 저장, 수정, 삭제를 모두 할 수 있고, 주인이 아닌 객체는 읽기만을 할 수 있다.

관계의 주인은 mappedBy 속성을 이용하여 지정할 수 있다.

아래와 같이 관계와 함께 mappedBy를 지정하면 된다.
```java
public class Member {
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
```

이렇게 되면, Order와 Member중에 Order가 주인이 된다.
## 3. 몇 대 몇

+ 다대일
    + 단방향
    + 양방향
+ 일대다
    + 단방향
+ 일대일
    + 단방향
    + 양방향

### 일대다 양방향을 사용하지 않는 이유
일대다 관계에서는 항상 '다'쪽에 외래키가 있기 때문에 '일'이 주인이 될 수 없다.

### 다대다를 사용하지 않는 이유
테이블의 primary key가 사라져버린다.

이를 해결하기 위해 N:M의 관계를 1:N과 1:M으로 분리하여
중간에서 연결의 역할을 하는 테이블을 추가해준다.

https://goodteacher.tistory.com/466
// 2. 도메인 분석 설계 - 3) 엔티티 클래스 개발1
// 2.3 회원 개체의 주소 속성
// 2. 도메인 분석 설계 - 4) 엔티티 클래스 개발2
// 2.4 값 타입 변경 불가능하게 설계 -> 생성할 때만 값이 세팅됨

package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable // JPA의 내장타입임을 명시. 어딘가에 내장될 수 있음을 명시.
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    // 2.4
    protected Address() {
    }

    // 2.4
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}

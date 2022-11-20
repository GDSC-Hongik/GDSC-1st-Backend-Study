// 2. 도메인 분석 설계 - 3) 엔티티 클래스 개발1 - 회원 테이블의 주소 속성

package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable // JPA의 내장타입임을 명시. 어딘가에 내장될 수 있음을 명시.
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;
}

// 2. 도메인 분석 설계 - 3) 엔티티 클래스 개발1
// 개체명: 상품 - 타입: 책
// 속성: 공통속성 + 추가속성(author, isbn)

package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@Getter @Setter
public class Book extends Item {
    private String author;
    private String isbn;
}

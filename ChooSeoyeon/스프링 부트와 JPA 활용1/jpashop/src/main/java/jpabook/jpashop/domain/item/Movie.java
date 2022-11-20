// 2. 도메인 분석 설계 - 3) 엔티티 클래스 개발1
// 개체명: 상품 - 타입: 영화
// 속성: 공통속성 + 추가속성(director, actor)

package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M") // 이거 안쓰면 기본으로 Movie로 들어감
@Getter @Setter
public class Movie extends Item {
    private String director;
    private String actor;
}

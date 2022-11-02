package GDSC.Hongik.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // JPA가 관리하는 Entity라는 뜻
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    // PK, IDENTITY - 디비가 직접 생성
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

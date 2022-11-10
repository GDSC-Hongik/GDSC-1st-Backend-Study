// 3. 회원 관리 예제 - 백엔드 개발 - 2) 회원 도메인과 리포지토리 만들기
// 회원 도메인 객체

package hello.hellospring.domain;

public class Member {
    private Long id; // 데이터 구분 위해 "시스템이" 저장하는 값
    private String name; // 회원가입시 "고객이" 적는 값

    //getter, setter
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

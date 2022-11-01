// 4.스프링 빈과 의존관계 - 1) 컴포넌트 스캔과 자동 의존관계 설정

package hello.hellospring.controller;

import hello.hellospring.service.MemberService; // import(MemberService)
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // import(@Contorller)

@Controller // import(@Contorller)
public class MemberController {
    // private final MemberService memberService = new MemberService(); // import(MemberService). 수정 전(MemberService를 new로 생성)
    private final MemberService memberService; // 스프링 컨테이너에 등록

    // 생성자(Alt+Insert)로 연결해줌
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}

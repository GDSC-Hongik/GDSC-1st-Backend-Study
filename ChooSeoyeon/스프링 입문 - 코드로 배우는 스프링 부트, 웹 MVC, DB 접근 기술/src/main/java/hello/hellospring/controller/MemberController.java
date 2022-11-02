// 4. 스프링 빈과 의존관계 - 1) 컴포넌트 스캔과 자동 의존관계 설정
// 5. 회원 관리 예제_웹 MVC 개발 - 2) 회원 웹 기능-등록
// 5. 회원 관리 예제_웹 MVC 개발 - 3) 회원 웹 기능-조회
// 7. AOP - 2) AOP 적용 -> 프록시 확인

package hello.hellospring.controller;

import hello.hellospring.domain.Member; // import Member 5.2
import hello.hellospring.service.MemberService; // import(MemberService) 4.1
import org.springframework.beans.factory.annotation.Autowired; // import Autowired 4.1
import org.springframework.stereotype.Controller; // import(@Contorller) 4.1
import org.springframework.ui.Model; // import Model 5.3
import org.springframework.web.bind.annotation.GetMapping; // import GetMapping 5.1
import org.springframework.web.bind.annotation.PostMapping; // import PostMapping 5.2

import java.util.List;

@Controller // import(@Contorller) 4.1
public class MemberController {
    // private final MemberService memberService = new MemberService(); // import(MemberService) 4.1. 수정 전(MemberService를 new로 생성)
    private final MemberService memberService; // 스프링 컨테이너에 등록

    // 생성자(Alt+Insert)로 연결해줌
    @Autowired // import Autowired 4.1
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        System.out.println("memberService = "+ memberService.getClass()); // 7.2
    }

    // 5.2
    @GetMapping("/members/new") // import GetMapping 5.1
    public String createForm() {
        return "members/createMemberForm";
    }

    // 5.2
    @PostMapping("/members/new") // import PostMapping 5.2
    public String create(MemberForm form) { //  form으로 name 들어옴 (스프링이 setname으로 값 넣어놨음)
        Member member = new Member(); // import Member 5.2 - 주의) 우리가 만든 hello.hellospring.domain의 Member import하기
        member.setName(form.getName());
        // System.out.println("member = " + member.getName());
        memberService.join(member);
        return "redirect:/"; // 회원가입 끝났으니 홈 화면으로 보냄
    }

    //5.3
    @GetMapping("/members")
    public String list(Model model) { // import Model 5.3
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); // members 리스트 자체를 model에 담아서 화면에 넘길 것임
        return "members/memberList";
    }
}
package GDSC.Hongik.hellospring.controller;

import GDSC.Hongik.hellospring.domain.Member;
import GDSC.Hongik.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// Controller 어노테이션이 있으면 스프링이 실행될 때
// 스프링 컨테이너에 해당 어노테이션이 있는 객체를 생성하여 삽입하고, 관리한다.
// 이것을 스프링 빈에서 스프링 컨트롤러가 관리된다고 한다.
@Controller
public class MemberController {

    // private final MemberService memberService = new MemberService();
    // MS를 new로 만들어서 쓸 수도 있지만 스프링 컨테이너로부터 받아서 써야 함
    // new로 하면 다른 컨트롤러들이 mS를 가져다 쓸 수가 없음
    // 그렇다고 여러 개 생성할 필요도 없음. 서비스는 하나만 있어야 함.
    // 그래서 이러한 서비스도 등록해서 (하나만 등록된다) 써먹으면 된다.

    private MemberService memberService; // Alt+Insert로 생성자 제너레이트

    // 컨트롤러 어노테이션 있으면 스프링 실행될 때 자동으로 생성되어 컨테이너에 삽입된다.
    // 이때 생성자가 실행되는데, 생성자에 Autowired(자동 연결) 어노테이션이 있으면 컨테이너의 MemberService와 연결시켜준다.
    // 하지만 MemberService는 스프링에 등록되어 있지 않음 (어떠한 어노테이션도 없음)
    // 그래서 @Service 어노테이션을 추가해줘야 함.
    // 그럼 Repository도 있지 않을까? 맞다. 구현체에 추가해주자.
    // 그래서 Controller-Service-Repository 구조가 정형화된 패턴이라는 것을 알 수 있다.

    @Autowired // DI
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        // member 리스트에 findMembers를 호출한 반환값을 할당한다.
        // findMembers는 멤버 레포지터리에서 모든 멤버들을 찾아서 반환한다.
        model.addAttribute("members", members);
        // key-value 해시맵 형태로 Model 객체에 담아서 뷰로 데이터를 전달.
        // 여기서는 멤버 리스트를 전달해야 하므로 member 리스트를 넣어주었음
        return "members/memberList";
    }
}

package jpabook.jpashop.web;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.lang.reflect.Member;

public class MemberController {
    package jpabook.jpashop.web;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;
import java.util.List;

    @Controller
    @RequiredArgsConstructor
    public class MemberController {
        private final MemberService memberService;

        @GetMapping(value = "/members/new")
        public String createForm(Model model) {
            model.addAttribute("memberForm", new MemberForm());
            return "members/createMemberForm";
        }

        @PostMapping(value = "/members/new")
        public String create(@Valid MemberForm form, BindingResult result) {
            if (result.hasErrors()) {
                return "members/createMemberForm";
            }
            Address address = new Address(form.getCity(), form.getStreet(),
                    form.getZipcode());
            Member member = new Member();
            member.setName(form.getName());

            member.setAddress(address);
            memberService.join(member);
            return "redirect:/";
        }
    }
}

package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) { //model에 데이터 담아서 컨트롤러에서 뷰로 넘긴다
        model.addAttribute("data", "hello!!");
        return "hello"; //화면 이름
    }

}

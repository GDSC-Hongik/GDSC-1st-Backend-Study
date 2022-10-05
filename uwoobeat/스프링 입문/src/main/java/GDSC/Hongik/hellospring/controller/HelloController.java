package GDSC.Hongik.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") // /hello로 접속하면 해당 메서드 호출
    public String hello(Model model) {
        model.addAttribute("data", "Spring");
        // data 속성으로 넘긴 값인 hello!가 hello.html ${data}로 전달됨
        return "hello";
    }
}

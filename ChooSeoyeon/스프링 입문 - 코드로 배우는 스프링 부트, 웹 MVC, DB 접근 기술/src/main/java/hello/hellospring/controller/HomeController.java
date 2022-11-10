// 5. 회원 관리 예제_웹 MVC 개발 - 1) 회원 웹 기능-홈 화면 추가

package hello.hellospring.controller;

import org.springframework.stereotype.Controller; // import Controller 5.1
import org.springframework.web.bind.annotation.GetMapping; // import GetMapping 5.1

@Controller // import Controller 5.1
public class HomeController {
    // 5.1
    @GetMapping("/") // import GetMapping 5.1
    public String home() {
        return "home";
    }
}
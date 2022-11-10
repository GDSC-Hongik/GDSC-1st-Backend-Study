package GDSC.Hongik.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 브라우저에서 요청이 오면 내장 톰캣 서버는 스프링 컨테이너에서 관련 컨트롤러를 찾는다.
    // 만약 있다면 해당 함수 로직에 따라 처리하고, 없으면 정적 컨텐츠 이미지를 그대로 반환했다.
    // index.html 역시 비슷한 방식으로 무시되고, homeController가 실행된다.
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
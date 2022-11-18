// 1. 프로젝트 환경설정 - 2) View 환경 설정 - thymeleaf 템플릿엔진 동작 확인

package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController { // 1.2
    @GetMapping("hello") // hello란 url로 오면 이 컨트롤러 호출
    public String hello(Model model) { // model에 데이터 실어 뷰에 넘김
        model.addAttribute("data", "hello!!!"); // addAttribute(Object attributeValue). data는 hello!!!
        return "hello"; // hello.html과 렌더링
    }
}

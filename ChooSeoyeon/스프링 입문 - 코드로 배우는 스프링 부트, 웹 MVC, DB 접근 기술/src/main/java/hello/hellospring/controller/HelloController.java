// 1.프로젝트 환경 설정 - 3) View 환경 설정 (타임리프 템플릿 엔진으로 동적페이지 사용해보기) -> /hello&hello.html
// 2. 스프링 웹 개발 기초 - 2) MVC와 템플릿 엔진 -> /hello-mvc&hello-template
// 2. 스프링 웹 개발 기초 - 3) API -> /hello-string, /hello-api

package hello.hellospring.controller; //1.3

import org.springframework.stereotype.Controller; //1.3
import org.springframework.ui.Model; //1.3
import org.springframework.web.bind.annotation.GetMapping; //1.3
import org.springframework.web.bind.annotation.RequestParam; //2.2
import org.springframework.web.bind.annotation.ResponseBody; //2.3

@Controller
public class HelloController {
    @GetMapping("hello") // 1.3 -> url: hello
    public String hello(Model model) {
        model.addAttribute("data", "hello!!"); // 직접 받음
        return "hello"; // hello.html파일에 렌더링
    }

    @GetMapping("hello-mvc") // 2.2 -> url: hello-mvc
    public String helloMvc(@RequestParam("name") String name, Model model){ // 외부(웹)에서 url parameter를 받음. model에 담으면 view에서 렌더링할 때 씀.
        model.addAttribute("name", name); // name이란 거엔 name값(url parameter로 받아온 값) 들어간다는 모델
        return "hello-template"; // hello-template파일에 렌더링
    }

    @GetMapping("hello-string") // 2.3 -> url: hello-string
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; //hello choochoo. 렌더링되는 html 파일명이 아닌 반환할 데이터 그 자체를 씀
    }

    @GetMapping("hello-api") // 2.3 -> url: hello-api
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello(); // 객체 생성
        hello.setName(name); // 객체의 name 변수에 parameter로 받은 name값 넣어줌
        return hello; // 렌더링되는 html 파일명이 아닌 반환할 객체를 씀
    }

    static class Hello { // 2.3
        private String name;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}






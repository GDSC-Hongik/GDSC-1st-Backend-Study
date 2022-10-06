package GDSC.Hongik.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello") // /hello로 접속하면 해당 메서드 호출
    public String hello(Model model) {
        model.addAttribute("data", "Spring");
        // data 속성으로 넘긴 값인 hello!가 hello.html ${data}로 전달됨
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
        // 원본 hello-template가 아님
        // model에 저장된 name:spring을 꺼내와서 viewResolver가 렌더링 후 브라우저에 표시
    }

    // API - 렌더링된 html을 보내는 것이 아니라, 데이터를 직접 전송
    @GetMapping("hello-string")
    @ResponseBody // HTTP에서 응답 바디에 이 데이터(리턴값)를 직접 넣겠다는 의미
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // View와 관계없이 해당 문자열을 그대로 전달
    }

    @GetMapping("hello-api")
    @ResponseBody
    // viewResolver 대신 HttpMessageConvert 동작
    // 문자의 경우 StringHttpMessageConverter 동작... (StringConverter)
    // 객체의 경우 MappingJackson2HttpMessageConverter 동작 (JsonConverter)
        // Jackson이라는 objectMapper를 사용하여 객체-JSON 직렬화/역직렬화
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); // 이런 식으로 스태틱 클래스를 만들어서 쓰면 편하다
        hello.setName(name);
        return hello;
        // 객체를 반환하면 알아서 JSON으로 변환됨
        // 예전에는 XML을 사용했고 HTML이 해당 방식을 사용했음
        // XML은 무겁고, 열고-닫고가 있기 때문에 잘 안쓴다...
        // 최근에는 JSON만 사용함
    }

    static class Hello {
        public String name;
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

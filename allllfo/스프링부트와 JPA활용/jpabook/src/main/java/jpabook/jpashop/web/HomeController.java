package jpabook.jpashop.web;

import org.springframework.web.bind.annotation.RequestMapping;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class HomeController {
    @RequestMapping("/")
    public String home() {
        log.info("home controller");
        return "home";
    }
}

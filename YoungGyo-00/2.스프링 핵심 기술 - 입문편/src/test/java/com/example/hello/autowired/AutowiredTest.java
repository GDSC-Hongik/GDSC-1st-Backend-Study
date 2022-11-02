package com.example.hello.autowired;

import com.example.hello.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        @Autowired(required = false)
        public void setNoBean1(Member member) {
            System.out.println(member);
        }

        @Autowired
        public void setNoBean2(@Nullable Member member) {
            System.out.println(member);
        }

        @Autowired(required = false)
        public void setNoBean3(Optional<Member> member) {
            System.out.println(member);
        }
    }
}

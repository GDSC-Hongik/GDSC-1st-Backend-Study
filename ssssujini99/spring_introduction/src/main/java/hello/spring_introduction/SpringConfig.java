package hello.spring_introduction;

import hello.spring_introduction.repository.*;
import hello.spring_introduction.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    // 스프링 데이터 JPA가 SpringDataJpaMemberRepository를 스프링 빈으로 자동으로 등록해준다

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

}

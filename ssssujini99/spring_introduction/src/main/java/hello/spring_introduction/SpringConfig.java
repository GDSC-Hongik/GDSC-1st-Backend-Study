package hello.spring_introduction;

import hello.spring_introduction.repository.MemberRepository;
import hello.spring_introduction.repository.MemoryMemberRepository;
import hello.spring_introduction.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}

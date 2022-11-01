// 4.스프링 빈과 의존관계 - 2) 자바코드로 직접 스프링 빈 등록

package hello.hellospring;

import hello.hellospring.repository.MemberRepository; // import MemberRepository
import hello.hellospring.repository.MemoryMemberRepository; // import MemoryMemberRepository
import hello.hellospring.service.MemberService; // import MemberService
import org.springframework.context.annotation.Bean; // import Bean
import org.springframework.context.annotation.Configuration; // import Configuration

@Configuration // import Configuration
public class SpringConfig {
    @Bean // import Bean
    public MemberService memberService() { // import MemberService
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){ // import MemberRepository
        return new MemoryMemberRepository(); // import MemoryMemberRepository
    }
}

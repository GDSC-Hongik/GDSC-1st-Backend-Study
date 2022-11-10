package com.example.gdsc;

import com.example.gdsc.repository.JdbcTemplateMemberRepository;
import com.example.gdsc.repository.JpaMemberRepository;
import com.example.gdsc.repository.MemberRepository;
import com.example.gdsc.repository.MemoryMemberRepository;
import com.example.gdsc.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    //private DataSource dataSource;
    private final EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        return new JpaMemberRepository(em);
    }
}

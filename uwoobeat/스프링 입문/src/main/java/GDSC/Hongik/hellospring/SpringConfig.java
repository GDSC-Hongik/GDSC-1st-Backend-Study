package GDSC.Hongik.hellospring;


import GDSC.Hongik.hellospring.repository.JdbcMemberRepository;
import GDSC.Hongik.hellospring.repository.JpaMemberRepository;
import GDSC.Hongik.hellospring.repository.MemberRepository;
import GDSC.Hongik.hellospring.repository.MemoryMemberRepository;
import GDSC.Hongik.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;
    private EntityManager em;

    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

//
//    // DI for DataSource
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        // 상황에 따라 구현 클래스를 변경해야 하면 컴포넌트 스캔을 사용하지 않고,
        // 설정을 통해 직접 스프링 빈으로 등록한다고 했다.
        // 아직 DB가 확정되지 않은 상황이고, 메모리 레포지터리 구현체를 다른 구현체로 변경할 예정이므로,
        // 직접 등록하는 방식을 채용한다.
        // return new MemoryMemberRepository();를 다른 생성자로 바꿔주기만 하면 된다.
        // return new MemoryMemberRepository();
        // return new JdbcMemberRepository(dataSource);
        // return new JpaMemberRepository(em);
    }
}

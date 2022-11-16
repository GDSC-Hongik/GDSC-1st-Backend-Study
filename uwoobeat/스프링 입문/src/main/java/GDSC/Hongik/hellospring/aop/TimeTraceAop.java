package GDSC.Hongik.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    // 어디에 적용할 것인지 타겟팅 가능
    @Around("execution(* GDSC.Hongik.hellospring..*(..))") // 이 패키지 아래에 전부 적용
    public Object excute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }

    /*
    AOP의 구현에는 프록시 패턴이 사용된다.
    스프링 컨테이너에 빈을 등록할 때 프록시라고 하는 가짜 서비스를 등록한다.
    이 프록시로 AOP 관련 코드를 실행하고, proceed()가 실행하면 그때 진짜 서비스가 실행된다.
    */
}

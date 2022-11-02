// 7. AOP - 2) AOP 적용

package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint; // import ProceedingJoinPoint
import org.aspectj.lang.annotation.Around; // import Around
import org.aspectj.lang.annotation.Aspect; // import Aspect.
import org.springframework.stereotype.Component; // import Component.

@Aspect // import Aspect. build.gradle 수정해야 import 제대로 됨
@Component // import Component. 스프링 빈 등록
public class TimeTraceAop {
    //@Around("execution(* hello.hellospring.service..*(..))")
    @Around("execution(* hello.hellospring..*(..))") // import Around. 여기선 hello.hellospring에 있는 거 다 적용함
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable { // import ProceedingJoinPoint
        long start = System.currentTimeMillis();
        System.out.println("START:" + joinPoint.toString());
        try{
            // Object result = joinPoint.proceed();
            // return result;
            return joinPoint.proceed(); // refactor -> inline variable. 위 두 줄을 inline함
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END = " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
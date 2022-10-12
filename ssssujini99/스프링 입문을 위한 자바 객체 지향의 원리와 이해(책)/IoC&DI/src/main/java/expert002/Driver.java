package expert002;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Driver {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("expert002/expert002.xml");

        Car car = context.getBean("car", Car.class); // 빈에서 등록된 걸 가져옴 // (구매)

        Tire tire = context.getBean("tire", Tire.class); // 빈에서 등록된 걸 가져옴 // (구매)

        car.setTire(tire); // 스프링을 통한 의존성 주입 (xml 파일 이용) + 속성을 통한 주입

        System.out.println(car.getTireBrand());
    }
}

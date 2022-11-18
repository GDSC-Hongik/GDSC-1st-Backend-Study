// 1. 프로젝트 환경설정 - 1) 프로젝트 생성

package jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {
		// 1.1 lombok 확인
//		Hello hello = new Hello();
//		hello.setData("hello");
//		String data = hello.getData();
//		System.out.println("data = "+ data);

		SpringApplication.run(JpashopApplication.class, args);
	}

}

package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/*
스프링부트 어플리케이션의 시작을 담당하는 클래스파일.
SpringBootApplication : 어노테이션을 통해 스프링의 모든 설정이 관리됨.
여기서 cofigure 메서드 오버라이드 하는 이유 : war 파일형식의 애플리케이션 배포 시 초기화하는 방식을 제어할 수 있다곤 하는데 여드가면 현재로썬 에러가 난다. 
*/
@SpringBootApplication
public class SbTest1Application {

	public static void main(String[] args) {
		SpringApplication.run(SbTest1Application.class, args);
	}

}

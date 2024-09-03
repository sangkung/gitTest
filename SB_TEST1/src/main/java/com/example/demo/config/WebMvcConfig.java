package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 스프링 설정 클래스임을 나타냄
@EnableWebMvc  // 기본적인 웹MVC 구성을 활성화할 수 있음
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	LoggingInterceptor loggingInterceptor;


	// 설정한 나의 loggingInterceptor 인터셉터 클래스를 등록시키는 것.
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(loggingInterceptor) // 인터셉터 등록
				.order(1)                           // 인터셉터 호출 순서 지정. 낮을수록 먼저 호출
				.addPathPatterns("/com/example/demo/controller/*")				// 인터셉터를 적용할 URL 패턴 지정한다.
				.excludePathPatterns("/", "/login.do", "/logout.do"); // 인터셉터에서 제외할 패턴을 지정한다.
	}	
	
	/*
	 * 정적파일에 대한 호출이 컨트롤러 호출보다 먼저 요청된다. 정적파일은 브라우저가 서버에 요청하여 얻는 것인데 인터셉터랑 무관하다고 한다.
	 * 'No mapping for GET /js/제이쿼리파일.js' 를 찾지 못하는 에러 발생하여 스크립트가 안먹힌다. 
	 * 해당 메서드를 통해 'No mapping for GET /js/제이쿼리파일.js' 에러를 피할 수 있었다.
	 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**", "/img/**")
                .addResourceLocations("classpath:/static/js/", "classpath:/static/img/");
    }
	
    
    
}

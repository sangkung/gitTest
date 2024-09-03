package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/*
 * @Component
 * 기능        : @SpringBootApplication 가 붙은 클래스에서 자동으로 @ComponentScan 이 수행되며 @Component 가 붙은 클래스들을 찾아 빈으로 등록한다. 
                이렇게 등록된 빈은 이후의 스프링 컨텍스트에서 사용할 수 있다. 즉, 빈으로 등록 시킴 등록된 빈을 스프링에서 관리하여 WebMvcConfigurer 를 구현한 클래스에서 등록되는 메서드를 실행.
 * 인터셉터 등록 : WebMvcConfigurer 을 구현한 클래스에서 해당 빈객체(LoggingInterceptor)를 @Autowired 로 주입받아 스프링이 내부적으로 addInterceptors 메서드를 호출해 등록한다.
 * <주의> - 인터셉터 클래스를 빈으로 등록(@Component)하지 않으면 톰켓 실행 안댐. 
 * */
@Component
public class LoggingInterceptor implements HandlerInterceptor {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler )throws Exception {
		

		//--------------
		// 잘못된 url 요청 
		//--------------
		
		
		//--------------
		// 세션체크 
		//--------------
		HttpSession session = request.getSession(false);
		if( session == null || session.getAttribute("userInfo") == null ) {
			logger.debug("__________________________________[인터셉터 preHandle]__________________________________");
			// 로그아웃 고!
			logger.debug("세션이 만료되었습니다.");
			// 로그아웃으로 rediect 
			response.sendRedirect("/logout.do"); 
			return false;
		}
		
		return true; // 리턴값이 false 면 컨트롤러 실행 안댐.
	}
	
	
	@Override
	public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView )throws Exception {
		
		// 요청 후처리 로직 구현
		
		
	}
	
	
	@Override
	public void afterCompletion( HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex )throws Exception {
		
		// view 모든 작업이 완료된 후 response 로 유저에게 보내기 전에 실행된다.
		
	}
	
}

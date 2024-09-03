package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/test")
public class TestController {

	/**
	 * 타임리프 화면 테스트
	 * @param request
	 * @return
	 */
	@RequestMapping("/test1.do")
	public String testView( HttpServletRequest request ) {
		return "test/test1";
	}
	
	
	/**
	 * 타임리프 화면 파라미터 넘기기
	 * @param request
	 * @return
	 * Model 과 ModelAndView 객체 차이 
	   → 뷰 이름 직접 설정 
	   → 모델보다 엔뷰가 세밀한 제어를 할 시 많이 씀.
	 */
	@RequestMapping("/test2.do")
	public String getData( HttpServletRequest request, Model model ) {
		// Data 세팅
		String xxx = "하이!";
		String yyy = "<h3>하이!</h3>";
		model.addAttribute("output1", xxx);
		model.addAttribute("output2", yyy);
		return "test/test2";
	}
	
	
	/**
	 * @PathVariable url 주소에 임의의 숫자를 붙여 접속 시 처리할 메서드  
	 * @param request
	 * @param num
	 * @return
	 * url 접속 시 박히는{num} 값이 파라미터 num 에 꽂히게 된다.
	   단, 두 네이밍이 같을 경우. 다르면 에러가 발생 
	   다른 방법도 있다. PathVariable(name="num") int urlSuffixNum 요로케~
	 */
	@RequestMapping("/test3.do/{num}")
	public ModelAndView pv( HttpServletRequest request, @PathVariable int num ) {
	
		ModelAndView mav = new ModelAndView();
		try {
		
			// 뷰 네임이 test1,2 가 이미 있기 때문에 해당 url 로 접속 시 test1,2.html 을 보여주지 않기 위한 조치.
			if( !(num == 3 || num == 4) ) { throw new Exception(); }
			
		}catch( Exception e ) {
			System.out.println("해당 URL 주소로 접근할 수 없습니다." + e.getMessage() );
			mav.setViewName("error/errorPage");
			return mav;
		}
		String viewNm = "test/test" + num;
		
		mav.addObject("num", num);
		mav.setViewName(viewNm);
		
		return mav;		
	}
	
	
	
	
	
	
	
}

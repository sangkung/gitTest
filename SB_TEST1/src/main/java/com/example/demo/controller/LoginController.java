package com.example.demo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.HomeDTO;
import com.example.demo.dto.LoginDto;
import com.example.demo.service.LoginService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	LoginService loginService;

	/**
	 * 로그인 view 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/")
	public ModelAndView loginView( HttpServletRequest request ) {

		ModelAndView mav = new ModelAndView();
		
		// 0 : 없는거   1 : 있는거 
		int userCookieYNCode = 0;
		
		// 쿠키객체안에 아이디 값이 있는지 확인. 
		Cookie[] cookies = request.getCookies();
		//logger.debug("_____쿠키s: " + cookies);
		if( cookies != null ) {
			for( Cookie c : cookies ) {
				String cookieNm = c.getName();
				if( cookieNm.equals("userId") ) {
					String cookieVal = c.getValue();
					//logger.debug("_____쿠키값: " + cookieVal);
					// 쿠키값이 있다면 
					if( cookieVal != null ) {
						userCookieYNCode = 1;
						mav.addObject("userId",cookieVal);
						break;
					}
				}
			}
		}
		
		HttpSession session = request.getSession(false);
		String sessionYn = "N";
		if( session != null ) {
			sessionYn = "Y";
		}
//		if( userCookieYNCode == 1 ) { logger.debug("쿠키존재 여부: 있음");  }
//		else { logger.debug("쿠키존재 여부: 없음"); }
		
		
		mav.addObject("userCookieYNCode",userCookieYNCode);
		mav.addObject("sessionYn",sessionYn);
		mav.setViewName("login/login");
		
		return mav;
	}
	 
	

	
	/**
	 * 로그인 처리
	 * @param request
	 * @param loginDto
	 * @return
	 */
	@RequestMapping(value="/login.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Map<String,Object> login( HttpServletRequest request, HttpServletResponse response, LoginDto loginDto )throws Exception {
		

		logger.debug("_________서버에서 받아온 데이터_________" );
		logger.debug("_________에러코드: " + loginDto.getUid() );
		logger.debug("_________에러메시지: " + loginDto.getUpwd() );
		
		Map<String,Object> resultMap = this.loginService.userInfo(request, response, loginDto);
		return resultMap;
	}
	
	
	
	/**
	 * 로그아웃 처리
	 * @return
	 */
	@RequestMapping(value="/logout.do")
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login/login");
		
//		Map<String,Object> to_userInfo = (Map<String, Object>) request.getSession().getAttribute("userInfo");
//		System.out.println( "세션날리기 전 : " + to_userInfo.get("USER_NM") );
		
		// 세션 날리기
		HttpSession session = request.getSession();
		session.removeAttribute("userInfo");
		session.invalidate(); // 세션정보 무효화
		
		try {
			response.sendRedirect("/"); // url 호출
		} catch (IOException e) {
			logger.debug( "로그아웃 후 로그인화면으로 이동하는 메서드 호출 시 예외발생!"  + e.getMessage() );
			e.printStackTrace();
		}
		
		return;
	}
	
	
	/**
	 * 세션아웃 처리( 보류 ) 
	 * 세션아웃 됬다는걸 로그인화면 갈때 msg 어케 던지지..?
	 * @return
	 */
	@RequestMapping(value="/sessionOut.do")
	public ModelAndView sessionOut(HttpServletRequest request, HttpServletResponse response) {
	
		ModelAndView mav = new ModelAndView();
		String msg = "세션이 만료되었습니다.";
		mav.setViewName("login/login");
		mav.addObject("msg", msg);
		
		return mav;
	}
	
	
}

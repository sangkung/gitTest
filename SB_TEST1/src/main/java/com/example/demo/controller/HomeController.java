package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.HomeDTO;
import com.example.demo.service.HomeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	HomeService homeService;
	
	/**
	 * 홈으로 이동
	 * @return
	 */
	@RequestMapping(value="/home.do")
	public ModelAndView goHome( HttpServletRequest request, HomeDTO homeDTO ) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = (HttpSession)request.getSession();
		Map<String,Object> userInfo = (Map<String, Object>) session.getAttribute("userInfo");                 // 유저정보
		List<Map<String,Object>> menuList = (List<Map<String,Object>>) session.getAttribute("menuList");      // 메뉴정보
		List<Map<String,Object>> brcInfoList = (List<Map<String,Object>>) session.getAttribute("brcInfoList");// 점소정보
		
		String sessionYN = "N";
		if( userInfo != null ) {
			sessionYN = "Y";
		}
		
		mav.addObject("userInfo", userInfo);
		mav.addObject("menuList", menuList);
		mav.addObject("brcInfoList", brcInfoList);
		mav.addObject("sessionYN", sessionYN);
		mav.setViewName("common/base");
		
		return mav;
	}
	
	
	/**
	 * 홈으로 이동
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/menu.do")
	public ModelAndView menu( HttpServletRequest request, HomeDTO homeDTO ) throws Exception {
		
		ModelAndView mav = new ModelAndView();
//		Map<String,Object> userInfoMap = new HashMap<>();
//		
//		userInfoMap.put("userNm", homeDTO.getUserNm());
//		userInfoMap.put("authTc", homeDTO.getAuthTc());
//		userInfoMap.put("ingCondTc", homeDTO.getIngCondTc());
//		userInfoMap.put("ascnC", homeDTO.getAscnC());
//		userInfoMap.put("brC", homeDTO.getBrC());
		

		HttpSession session = request.getSession( false );
		Map<String,Object> userInfoMap = (Map<String, Object>) session.getAttribute("userInfo");
		
		if( userInfoMap != null ) {
			logger.debug( "____________세션-유저정보 " );
			logger.debug( (String) userInfoMap.get("USER_NM") );
		}
		else {
			logger.debug("세션에 저장된 'userInfo' 값이 null 입니다.");
			throw new Exception();  
		}
		
		int errorCode;
		String errorMsg;
		
		try {
			// 쓸일있으면 매개변수 교체, 현재는 메뉴가져올 때 뭐없이 가져옴.
			List<Map<String,Object>> menuList = this.homeService.menuList( userInfoMap );
			
			errorCode = 0;
			errorMsg = "메뉴를 가져왔습니다.";
			mav.addObject("menuList", menuList);
			
		}catch(Exception e) {
			errorCode = -1;
			errorMsg = "예기치못한 상황에 메뉴를 가져오지 못하였습니다.\n" + e.getMessage();
		}
		
		mav.addObject("errorCode", errorCode);
		mav.addObject("errorMsg", errorMsg);
		mav.addObject("userInfoMap", userInfoMap);
		mav.setViewName("menus/menu");
		
		return mav;
	}
	
	/**
	 * 파일업로드 테스트
	 * @param request
	 * @return
	 */
	@RequestMapping( value="/fileUp.do" )
	public ModelAndView fileUpLoad( HttpServletRequest request ) {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("test/fileUpLoad");
		
		return mav;
	}
	
	
	/**
	 * 파일다운로드 테스트
	 * @param request
	 * @return
	 */
	@RequestMapping( value="/fileDown.do" )
	public ModelAndView fileDownLoad( HttpServletRequest request ) {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("test/fileDownLoad");
		
		return mav;
	}
	
}

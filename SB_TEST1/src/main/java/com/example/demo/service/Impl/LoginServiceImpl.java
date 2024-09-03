package com.example.demo.service.Impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.LoginDto;
import com.example.demo.mapper.LoginMapper;
import com.example.demo.service.HomeService;
import com.example.demo.service.LoginService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Service
public class LoginServiceImpl implements LoginService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	LoginMapper loginMapper;
	
	@Autowired
	HomeService homeService;
	
	
	@Override
	public Map<String,Object> userInfo( HttpServletRequest request, HttpServletResponse response, LoginDto loginDto )throws Exception{
		
		int resultCode = 0;    // 에러코드
		String resultMsg = ""; // 에러메시지
		String userId = loginDto.getUid(); // 유저id
		boolean is_check_userid = loginDto.isUid_save(); // 유저id 저장유무
		
		Map<String,Object> userInfo = new HashMap<String,Object>();
		
		try {

			String userCheck = this.loginMapper.userCheck(loginDto);
		
			if( "Y".equals(userCheck) ) {  

				userInfo = this.loginMapper.userInfo(loginDto);
				
				// 로그인 성공 또는 실패 시
				if( userInfo != null ) {

					// -----------------------------------------------------------
					// 세션처리
					// -----------------------------------------------------------
					resultMsg = "로그인 성공";
					HttpSession session = request.getSession();
					session.setAttribute("userInfo", userInfo);
					session.setMaxInactiveInterval(24*60*60); // 세션 1일 지속 
					
					//  현재시간 포맷 
					SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss"); //단순포맷
					Calendar calender = Calendar.getInstance();    //현재날짜
					String currentTime = fmt.format(calender.getTime() );
					
					userInfo.put("currentTime", currentTime); // 사용자 최초 접속 시간
					userInfo.put("resultCode", resultCode);
					userInfo.put("resultMsg", resultMsg);
					
					logger.debug("세션에 유저정보를 저장하였습니다.");

					// -----------------------------------------------------------
					// 메뉴 가져오기
					// -----------------------------------------------------------
					List<Map<String,Object>> menuList = this.homeService.menuList( userInfo );
					// 메뉴를 모든화면에 뿌리기 위해 세션에 넣기
					if( menuList != null ) {
						session.setAttribute("menuList", menuList);
						logger.debug("세션에 메뉴정보를 저장하였습니다.");
					}
					
					// -----------------------------------------------------------
					// 화면에서 조회 할 때 사용할 조합정보
					// -----------------------------------------------------------
					List<Map<String, Object>> brcInfoList = loginMapper.selectBrcInfoList();
					// 조합정보를 모든화면에 뿌리기 위해 세션에 넣기
					if( brcInfoList != null ) {
						session.setAttribute("brcInfoList", brcInfoList);
						logger.debug("세션에 조합정보를 저장하였습니다.");
					}
					
					
				}
				
			}
			else {
				resultCode = -1;
				resultMsg = "로그인 정보가 맞지 않습니다.";
				userInfo.put("resultCode", resultCode);
				userInfo.put("resultMsg", resultMsg);
				logger.debug("로그인 실패");
			}
			
		}catch( Exception e ) {
			
			resultCode = -1;
			resultMsg = "로그인 중 예외발생! " + e.getMessage();
			userInfo.put("resultCode", resultCode);
			userInfo.put("resultMsg", resultMsg);
			
			return userInfo;
		
		}
			
			
			
		// 쿠키저장은 로그인 로직과 관계없이 유저에게 받은 아이디값을 저장할 뿐이다.
		// 아이디저장 시 쿠키에 담기
		if( is_check_userid ) {
			Cookie cookie = new Cookie("userId",userId);
			cookie.setMaxAge(30*24*60*60);
			response.addCookie(cookie);
			userInfo.put("cookieYn", 1); // 로그인에 실패할 경우에도 아이디체크에 대한 유무를 던져줘야지 로그인창에서 에로하지 않는다.
			logger.debug("유저아이디값을 [userId]쿠기명에 저장하였습니다.");
		}else {
			Cookie cookie = new Cookie("userId",null);  // 쿠키값 null 지정.
			cookie.setMaxAge(0);        				// 유효시간 0 세팅.
			response.addCookie(cookie); 				// 응답에 추가하여 만료시킨 쿠키값을 적용시킨다.
			userInfo.put("cookieYn", 0);
			logger.debug("유저아이디값을 [userId]쿠기명에서 제거하였습니다.");
		}
		
		
		
		return userInfo; // 별탈 없다면 로그인 정보 리턴.
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Override
//	public Map<String,Object> userInfo( HttpServletRequest request, HttpServletResponse response, LoginDto loginDto )throws Exception{
//		
//		Map<String,Object> userInfo = this.loginMapper.userInfo(loginDto);
//		Map<String,Object> errorInfo = new HashMap<>(); // 로그인 실패 시 에러정보를 담을 맵
//		
//		int resultCode = 0;    // 에러코드
//		String resultMsg = ""; // 에러메시지
//		String userId = loginDto.getUid(); // 유저id
//		boolean is_check_userid = loginDto.isUid_save(); // 유저id 저장유무
//		
//		try {
//			
//			// 로그인 성공 또는 실패 시
//			if( userInfo != null ) {
//				
//				resultMsg = "로그인 성공";
//				HttpSession session = request.getSession();
//				session.setAttribute("userInfo", userInfo);
//				session.setMaxInactiveInterval(24*60*60); // 세션 1일 지속 
//				
//				userInfo.put("resultCode", resultCode);
//				userInfo.put("resultMsg", resultMsg);
//				
//			}
//			else {
//				resultCode = -1;
//				resultMsg = "로그인 정보가 맞지 않습니다.";
//				errorInfo.put("resultCode", resultCode);
//				errorInfo.put("resultMsg", resultMsg);
//				
//				return errorInfo;
//			}
//		
//		}catch( Exception e ) {
//
//			resultCode = -1;
//			resultMsg = "로그인 중 예외발생! " + e.getMessage();
//			errorInfo.put("resultCode", resultCode);
//			errorInfo.put("resultMsg", resultMsg);
//			
//			return errorInfo;
//		}
//		
//		// 아이디저장 시 쿠키에 담기
//		if( is_check_userid ) {
//			Cookie cookie = new Cookie("userId",userId);
//			cookie.setMaxAge(30*24*60*60);
//			response.addCookie(cookie);
//		}else {
//			Cookie cookie = new Cookie("userId","");
//			cookie.setMaxAge(0);
//			response.addCookie(cookie);
//		}
//		
//			
//		return userInfo; // 별탈 없다면 로그인 정보 리턴.
//	}
	
	
	
}

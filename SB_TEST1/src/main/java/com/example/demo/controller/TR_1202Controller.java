package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.TR_1202Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping( value="/tr_1202" )
public class TR_1202Controller {

	@Autowired
	TR_1202Service tr_1202Service;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	/**
	 * 금리구간별 현황 view
	 * @return
	 */
	@RequestMapping(value="/TR_1202.do")
	public ModelAndView goTr_1201( HttpServletRequest request ) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = (HttpSession)request.getSession();
		Map<String,Object> userInfo = (Map<String, Object>) session.getAttribute("userInfo");                 // 유저정보
		List<Map<String,Object>> menuList = (List<Map<String,Object>>) session.getAttribute("menuList");      // 메뉴정보
		List<Map<String,Object>> brcInfoList = (List<Map<String,Object>>) session.getAttribute("brcInfoList");// 점소정보
		
		
		mav.addObject("userInfo", userInfo);
		mav.addObject("menuList", menuList);
		mav.addObject("brcInfoList", brcInfoList);
		mav.setViewName("menus/tr_1202");
		
		return mav;
	}
	
	
	/**
	 * 금리구간별 현황 조회
	 * @return
	 */
	@RequestMapping(value="/login.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<Map<String,Object>> selectTr_1202( HttpServletRequest request ){
		
		List<Map<String,Object>> selectTr_1202List = this.tr_1202Service.selectTr_1202List();
		
		return selectTr_1202List;
	}
	
}

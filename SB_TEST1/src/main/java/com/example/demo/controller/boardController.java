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

@Controller
public class boardController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	HomeService homeService;
	
	/**
	 * 홈으로 이동
	 * @return
	 */
	@RequestMapping(value="/board.do")
	public ModelAndView goHome( HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("menus/board");
		
		return mav;
	}
	
}

package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.LoginDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface LoginService {

	public Map<String,Object> userInfo( HttpServletRequest request, HttpServletResponse response, LoginDto loginDto )throws Exception;
	
}

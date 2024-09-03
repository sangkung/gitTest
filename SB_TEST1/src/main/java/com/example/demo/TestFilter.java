package com.example.demo;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class TestFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		System.out.println("필터 시작");
	}

		
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) {
		
		System.out.println("필터 중");
	}
	
	@Override
	public void destroy() {

		System.out.println("필터 삭제");
		
	}
	
}

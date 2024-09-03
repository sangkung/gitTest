package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javassist.NotFoundException;

@ControllerAdvice
public class ErrorController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(NotFoundException.class)
	public String handleNotFoundException( Model model ) {
		
		model.addAttribute("status", HttpStatus.NOT_FOUND.value() );
		model.addAttribute("error", "not found");
		model.addAttribute("msg", "요청하신 페이지를 찾을 수 없습니다.");
		
		return "error/errorPage";
	}
	
}

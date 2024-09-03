package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCode {

	private final  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
// * 현재시간 포맷 테스트 		
//		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss"); //단순포맷
//		Calendar calender = Calendar.getInstance();    //현재날짜
//		
//		System.out.println( fmt.format(calender.getTime() ) );
		

		// * 마이바티스 리턴자료형이 MAP 인 컬럼에 NULL 값이 있는 경우 확인 
		Map<String,Object> map = new HashMap<>();
		
		map.put("apple",null);
		map.put("grape","포도");

		System.out.println("결과");
		System.out.println(map);
	}

}

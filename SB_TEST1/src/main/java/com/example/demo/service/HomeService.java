package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.dto.HomeDTO;

public interface HomeService {

	public List<Map<String,Object>> menuList( Map<String,Object> userInfo );
	
}

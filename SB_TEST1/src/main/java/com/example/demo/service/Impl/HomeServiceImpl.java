package com.example.demo.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.HomeDTO;
import com.example.demo.mapper.HomeMapper;
import com.example.demo.service.HomeService;

@Service
public class HomeServiceImpl implements HomeService {

	@Autowired
	HomeMapper homeMapper;
	
	@Override
	public List<Map<String,Object>> menuList( Map<String,Object> userInfo ){
		
		List<Map<String,Object>> menuList = this.homeMapper.menuList( userInfo );
	
		return menuList;
	}
	
}

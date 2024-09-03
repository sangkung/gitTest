package com.example.demo.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.TR_1202Mapper;
import com.example.demo.service.TR_1202Service;

@Service
public class TR_1202ServiceImpl implements TR_1202Service {

	@Autowired
	TR_1202Mapper tr_1202Mapper;
	
	public List<Map<String,Object>> selectTr_1202List( ){
		
		List<Map<String,Object>> selectTr_1202List = this.tr_1202Mapper.selectTr_1202List();
		
		return selectTr_1202List;
	}
	
}

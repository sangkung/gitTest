package com.example.demo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.HomeDTO;

@Mapper
public interface HomeMapper {

	List<Map<String,Object>> menuList( Map<String,Object> userInfo );
	
}

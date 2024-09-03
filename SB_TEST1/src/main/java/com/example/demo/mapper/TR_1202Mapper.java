package com.example.demo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TR_1202Mapper {

	List<Map<String,Object>> selectTr_1202List( );
	
}

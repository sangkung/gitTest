package com.example.demo.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.example.demo.dto.LoginDto;

@Mapper
public interface LoginMapper {

	String userCheck( LoginDto loginDto );
	
	Map<String,Object> userInfo( LoginDto loginDto );
	
	List<Map<String, Object>> selectBrcInfoList();
}

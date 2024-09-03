package com.example.demo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DB_connect_check {
	
	//private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/ORCL"; //"jdbc:oracle:thin:@localhost:1521:ORCL";
		String userName = "almadm";
		String password = "almadm"; 

		try {
			
			Connection connection = DriverManager.getConnection(jdbcUrl, userName, password);
			
			if( connection != null ) {
				System.out.println("DB연결 여부 : Y");
				//System.out.println("스키마명: " + connection.getSchema() );
				//System.out.println( + connection.getNetworkTimeout() );
				
				Statement statement = connection.createStatement();
				ResultSet resutlSet = statement.executeQuery("SELECT USER_NM FROM TSY_USER_MNG WHERE USID = '1111'");
				
				while( resutlSet.next() ) {
					String userNm = resutlSet.getString("USER_NM");
					System.out.println( "사용자 이름 :" + userNm );
				}
				
				
				connection.close();
				
			}else {
				System.out.println("DB연결 여부 : N");
			}
			
			  
		}catch( SQLException e ) {
			
			e.printStackTrace();
		}
		
		
	}

}

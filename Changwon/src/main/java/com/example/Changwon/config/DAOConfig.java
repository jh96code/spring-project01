package com.example.Changwon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.Changwon.DAO.BoardDao;
import com.example.Changwon.DAO.SpotsDAO;
import com.example.Changwon.DAO.cslistDao;
import com.example.Changwon.DAO.goodsDao;
import com.example.Changwon.DAO.memberDAO;

@Configuration
public class DAOConfig {
	
	@Bean
	public memberDAO memberdao() {
		return new memberDAO();
	}
	
	@Bean
	public BoardDao boarddao() {
		return new BoardDao();
	}
	
	@Bean
	public goodsDao goodsdao() {
		return new goodsDao();
	}
	
	@Bean 
	public SpotsDAO spotsdao() {
		return new SpotsDAO();
	}
	
	@Bean 
	public cslistDao cslistdao() {
		return new cslistDao();
	}

}

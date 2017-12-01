package com.olib.rss.server.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olib.rss.core.util.OlibJsonUtil;
import com.olib.rss.server.dao.BookMarkDao;
import com.olib.rss.server.model.BookMark;

@Service
public class BookMarkService {

	@Autowired
	private BookMarkDao dao;
	
	public Integer save(BookMark bookMark){
		return dao.save(bookMark).getId();
	}
	
	public void delete(int id){
		dao.delete(id);
	}
	
	public Integer save(String jsonData){
		try{
			String url = OlibJsonUtil.getJsonData(jsonData, "url").toString();
			String name = OlibJsonUtil.getJsonData(jsonData, "name").toString();
			return dao.save(new BookMark(name,url)).getId();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<BookMark> load(){
		return dao.findAll();
	}
	
	public BookMark getBookMarkById(int id){
		return dao.findOne(id);
	}
}

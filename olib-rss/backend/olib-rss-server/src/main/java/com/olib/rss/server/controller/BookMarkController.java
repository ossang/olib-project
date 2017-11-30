package com.olib.rss.server.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olib.rss.core.model.FeedItem;
import com.olib.rss.core.service.RssService;
import com.olib.rss.core.util.OlibJsonUtil;
import com.olib.rss.server.model.BookMark;
import com.olib.rss.server.service.BookMarkService;

@RestController
@RequestMapping(value="/api")
public class BookMarkController {
	
	@Autowired
	private BookMarkService bookMarkService;

	@Autowired
	private RssService rssService;
	
	@RequestMapping(value="/bookmark",method=RequestMethod.GET)
	public List<BookMark> loadBookmark(){
		return bookMarkService.load();
	}
	
	@RequestMapping(value="/bookmark",method=RequestMethod.POST)
	public Integer saveBookmark(
			@RequestBody String jsonData){
		
		boolean isValidUrl = rssService.isValidUrl(
				OlibJsonUtil.getJsonData(jsonData, "url").toString());
		
		if(!isValidUrl){
			return null;
		}
		
		return bookMarkService.save(jsonData);
	}
	
	@RequestMapping(value="/bookmark",method=RequestMethod.PUT)
	public Integer updateBookmark(
			@RequestBody String jsonData){
		
		try{
			BookMark bookmark = new ObjectMapper().readValue(jsonData, BookMark.class);
			boolean isValidUrl = rssService.isValidUrl(bookmark.getUrl());
			
			if(!isValidUrl){
				return null;
			}
			
			return bookMarkService.save(bookmark);
			
		}catch(Exception e){
			return null;
		}
	}
	
	@RequestMapping(value="/bookmark/{id}",method=RequestMethod.DELETE)
	public boolean deleteBookmark(
			@PathVariable(value = "id") int id){
		
		bookMarkService.delete(id);
		return true;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public List<FeedItem> getRss(
			@PathVariable(value = "id") int id){
		
		String url = bookMarkService.getBookMarkById(id).getUrl();
		return rssService.getFeedItems(url);
	}
	
	
	
}

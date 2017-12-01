package com.olib.rss.server.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olib.rss.core.model.FeedItem;
import com.olib.rss.core.service.RssService;
import com.olib.rss.server.service.BookMarkService;

@RestController
@RequestMapping(value="/api")
public class RssController {
	
	@Autowired
	private RssService rssService;
	
	@Autowired
	private BookMarkService bookmarkService;
	
	@RequestMapping(value="/rss/{bookmarkId}",method=RequestMethod.GET)
	public List<FeedItem> getRss(
			@PathVariable(value = "bookmarkId") int bookmarkId){
		
		return rssService.getFeedItems(
				bookmarkService.getBookMarkById(bookmarkId).getUrl());
	}
}

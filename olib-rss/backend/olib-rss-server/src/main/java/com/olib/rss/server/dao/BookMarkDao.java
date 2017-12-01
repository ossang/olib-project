package com.olib.rss.server.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olib.rss.server.model.BookMark;

@Repository
public interface BookMarkDao extends JpaRepository<BookMark, Integer> {

}

package com.mainproject.event.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mainproject.event.vo.EventVO;

public interface EventDAO {
	
	void insertEvent(EventVO eventVO);
	
	void deleteEvent(Long eventId);
	
    List<EventVO> getAllEvents();
    
	List<EventVO> selectAllEvents();
	
	EventVO getEventByTitle(String eventTitle);
	
	void deleteEvent(int eventId);
	
	EventVO getEventById(int eventId);
	
	void updateEvent(EventVO event);
	 
	 
	
    
} 
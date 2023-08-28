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
	
	EventVO getEventByEventNum(int eventNum);

	void updateEventByTitle(String eventTitle, EventVO updatedEvent);

	void updateEventByEventNum(int eventNum, EventVO updatedEvent);
	
	void markEventAsDeleted(int eventId);

	

	

	

	
	
	 


	 
} 
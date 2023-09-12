package com.mainproject.event.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mainproject.event.vo.EventVO;
import com.mainproject.paging.Criteria;
import com.mainproject.paging.PagingVO;
import com.sun.jdi.event.Event;

public interface EventDAO {
	
	void insertEvent(EventVO eventVO);
	void deleteEvent(Long eventId);
	void deleteEvent(int eventId);
	void updateEvent(EventVO event);
	void updateEventByTitle(String eventTitle, EventVO updatedEvent);
	void updateEventByEventNum(int eventNum, EventVO updatedEvent);
	void markEventAsDeleted(int eventId);
	int getLastEventOrderForUser(int userNum); 
	List<EventVO> selectAllEventsForUserNum(String userNum);
	List<EventVO> listEventsForUserNum(String userNum);
	List<EventVO> listEventsForUserNum(int userNum); 
	List<EventVO> selectEventsForUserNum(int userNum); 
	List<EventVO> getAllEvents();
	List<EventVO> selectAllEvents();
	EventVO getEventByTitle(String eventTitle);
	EventVO getEventById(int eventId);
	EventVO getEventByEventNum(int eventNum);

	
  
	

	

	
	
	 


	 
} 
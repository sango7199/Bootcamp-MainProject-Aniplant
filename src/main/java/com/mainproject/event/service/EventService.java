package com.mainproject.event.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mainproject.event.vo.EventVO;
import com.mainproject.paging.Criteria;


  
public interface EventService {
	
    void createEvent(EventVO eventVO);
    List<EventVO> listEvents();
	EventVO getEventByTitle(String eventTitle);
	public EventVO getEventByEventNum(int eventNum) throws DataAccessException;
	EventVO getEventById(int eventId);
	void deleteEvent(int eventId); 
	void updateEvent(EventVO event);
	void updateEventByTitle(String title, EventVO existingEvent);
	void updateEventByIdAndTitle(int eventId, String eventTitle, EventVO updatedEvent);
	void updateEventByEventNum(int event_num, EventVO event);
	void markEventAsDeleted(int eventId);
	List<EventVO> listEventsWithPaging(int page, int perPageNum);
	int getTotalEventCount();
	List<EventVO> listEventsForUserNum(int userNum);
	List<EventVO> listEventsForUserNum(String userNum);
	int getLastEventOrderForUser(int userNum);
	
	
	  

	

	

	

	

	

	   

	
	

	

	

	   

	  
	 
    
}
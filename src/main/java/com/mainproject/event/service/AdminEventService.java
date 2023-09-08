package com.mainproject.event.service;

import java.util.List;

import com.mainproject.event.vo.EventVO;
import com.mainproject.paging.Criteria;

public interface AdminEventService {
    
	void createEvent(EventVO eventVO);
	EventVO getEventByTitle(String eventTitle); 
	EventVO getEventById(int eventId); 
	void deleteEvent(int eventId); 
	void updateEvent(EventVO event);
	void updateEventByTitle(String title, EventVO existingEvent);
	void updateEventByIdAndTitle(int eventId, String eventTitle, EventVO updatedEvent);
	void updateEventByEventNum(int event_num, EventVO event);
	EventVO getEventByEventNum(int eventNum);
	void markEventAsDeleted(int eventId);
	List<EventVO> listEventsForUserNum(int userNum);
	int getLastEventOrderForUser(int userNum);
	List<EventVO> getAllEvents();
	 
 
	
	
	 
	 
	

	
	
}
  
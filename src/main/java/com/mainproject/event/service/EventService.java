package com.mainproject.event.service;

import java.util.List;

import com.mainproject.event.vo.EventVO;
  
public interface EventService {
	
    void createEvent(EventVO eventVO);
    
    List<EventVO> listEvents();
    
	EventVO getEventByTitle(String eventTitle);
	
	EventVO getEventById(int eventId);
	
	void deleteEvent(int eventId); 
	
	void updateEvent(EventVO event);

	void updateEventByTitle(String title, EventVO existingEvent);

	void updateEventByIdAndTitle(int eventId, String eventTitle, EventVO updatedEvent);

	void updateEventByEventNum(int event_num, EventVO event);

	EventVO getEventByEventNum(int eventNum);
	
	void markEventAsDeleted(int eventId);

	List<EventVO> listEventsWithPaging(int page, int perPageNum);

	int getTotalEventCount();

	List<EventVO> listEventsForUserNum(int userNum);

	List<EventVO> listEventsForUserNum(String userNum);

	int getLastEventOrderForUser(int userNum);

	

	

	

	

	

	   

	
	

	

	

	   

	  
	 
    
}
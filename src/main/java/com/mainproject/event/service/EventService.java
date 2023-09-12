package com.mainproject.event.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mainproject.event.vo.EventVO;
import com.mainproject.paging.Criteria;
  
public interface EventService {
	
    void createEvent(EventVO eventVO);
<<<<<<< HEAD
    void deleteEvent(int eventId); 
=======
    List<EventVO> listEvents();
	EventVO getEventByTitle(String eventTitle);
	public EventVO getEventByEventNum(int eventNum) throws DataAccessException;
	EventVO getEventById(int eventId);
	void deleteEvent(int eventId); 
>>>>>>> eefbfab78df598cca1ee75837f4ebc4b14501974
	void updateEvent(EventVO event);
	void updateEventByTitle(String title, EventVO existingEvent);
	void updateEventByIdAndTitle(int eventId, String eventTitle, EventVO updatedEvent);
	void updateEventByEventNum(int event_num, EventVO event);
<<<<<<< HEAD
	void markEventAsDeleted(int eventId); 
	int getLastEventOrderForUser(int userNum);
=======
	void markEventAsDeleted(int eventId);
	List<EventVO> listEventsWithPaging(int page, int perPageNum);
	int getTotalEventCount();
>>>>>>> eefbfab78df598cca1ee75837f4ebc4b14501974
	List<EventVO> listEventsForUserNum(int userNum);
	List<EventVO> listEventsForUserNum(String userNum);
	List<EventVO> listEvents();
	EventVO getEventByTitle(String eventTitle);
	EventVO getEventById(int eventId);
	EventVO getEventByEventNum(int eventNum);
	  

	

	

	

	

	

	   

	
	

	

	

	   

	  
	 
    
}
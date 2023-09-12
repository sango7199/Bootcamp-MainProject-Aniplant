package com.mainproject.event.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mainproject.event.vo.EventVO;
import com.mainproject.paging.Criteria;
import com.mainproject.paging.PagingVO;
import com.sun.jdi.event.Event;

public interface EventDAO {
	
	void insertEvent(EventVO eventVO);
	public void registerEvent(EventVO eventVO) throws DataAccessException;
	void deleteEvent(Long eventId);
	void deleteEvent(int eventId);
	void updateEvent(EventVO event);
<<<<<<< HEAD
=======
	EventVO getEventByEventNum(int eventNum) throws DataAccessException;
>>>>>>> eefbfab78df598cca1ee75837f4ebc4b14501974
	void updateEventByTitle(String eventTitle, EventVO updatedEvent);
	void updateEventByEventNum(int eventNum, EventVO updatedEvent);
	void markEventAsDeleted(int eventId);
	int getLastEventOrderForUser(int userNum); 
	List<EventVO> selectAllEventsForUserNum(String userNum);
	List<EventVO> listEventsForUserNum(String userNum);
<<<<<<< HEAD
	List<EventVO> listEventsForUserNum(int userNum); 
	List<EventVO> selectEventsForUserNum(int userNum); 
	List<EventVO> getAllEvents();
	List<EventVO> selectAllEvents();
	EventVO getEventByTitle(String eventTitle);
	EventVO getEventById(int eventId);
	EventVO getEventByEventNum(int eventNum);

=======
	List<EventVO> selectEventsForUserNum(int userNum);
	int getLastEventOrderForUser(int userNum);
	List<EventVO> listEventsForUserNum(int userNum);  
	List<Event> listEventsForUserNum(int userNum, Criteria criteria);
    int countEventsForUserNum(int userNum);
	List<EventVO> listEventsForUserNumWithPaging(int userNum, PagingVO pagingVO);
	public void deleteEvent2(EventVO eventVO) throws DataAccessException;
	public void updateEvent2(EventVO eventVO) throws DataAccessException;
>>>>>>> eefbfab78df598cca1ee75837f4ebc4b14501974
	
  
	

	

	
	
	 


	 
} 
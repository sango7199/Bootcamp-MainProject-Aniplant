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
	List<EventVO> selectAllEventsForUserNum(String userNum);
	List<EventVO> listEventsForUserNum(String userNum);
	List<EventVO> selectEventsForUserNum(int userNum);
	int getLastEventOrderForUser(int userNum);
	List<EventVO> listEventsForUserNum(int userNum);  
	List<Event> listEventsForUserNum(int userNum, Criteria criteria);
    int countEventsForUserNum(int userNum);
	List<EventVO> listEventsForUserNumWithPaging(int userNum, PagingVO pagingVO);  

	
  
	

	

	
	
	 


	 
} 
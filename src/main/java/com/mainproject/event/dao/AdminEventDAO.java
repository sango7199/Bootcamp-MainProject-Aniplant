package com.mainproject.event.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mainproject.event.vo.EventVO;
import com.mainproject.paging.Criteria;
import com.mainproject.paging.PagingVO;
import com.sun.jdi.event.Event;

public interface AdminEventDAO {

	List<EventVO> selectEventsForUserNum(int userNum);
	void insertEvent(EventVO eventVO);
	int getLastEventOrderForUser(int userNum);
	EventVO getEventByTitle(String eventTitle);
	EventVO getEventById(int eventId);
	EventVO getEventByEventNum(int eventNum);
	void updateEvent(EventVO existingEvent);
	void updateEventByEventNum(int eventNum, EventVO updatedEvent);
	void updateEventByTitle(String eventTitle, EventVO updatedEvent);
	void markEventAsDeleted(int eventNum);
	void deleteEvent(Long eventId);
	List<EventVO> getAllEvents();
	List<EventVO> selectAllEvents();
	List<EventVO> selectAllEventsForUserNum(String userNum);
	List<Event> listEventsForUserNum(int userNum, Criteria criteria);
	List<EventVO> listEventsForUserNum(String userNum);
	List<EventVO> listEventsForUserNum(int userNum);
	void deleteEvent(int eventId); 
	
	List<EventVO> getEventsForPage(@Param("startRow") int startRow, @Param("itemsPerPage") int itemsPerPage);
    int getTotalEventCount();
	
	

	

	

	

} 
 
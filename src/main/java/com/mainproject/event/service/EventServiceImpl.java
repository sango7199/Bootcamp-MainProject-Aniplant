package com.mainproject.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mainproject.event.dao.EventDAO;
import com.mainproject.event.vo.EventVO;

@Service("eventService")
public class EventServiceImpl implements EventService { 
	 
	@Autowired
    private EventDAO eventDAO;

    @Override
    public void createEvent(EventVO eventVO) {
        if (eventVO != null && eventVO.getTitle() != null && !eventVO.getTitle().isEmpty()) {
            eventDAO.insertEvent(eventVO);
        } else {
            throw new IllegalArgumentException("Event title cannot be null or empty.");
        } 
    } 
 
    @Override
    public List<EventVO> listEvents() {
        return eventDAO.getAllEvents();
    }
 
	@Override
	public EventVO getEventByTitle(String eventTitle) {
		
		return eventDAO.getEventByTitle(eventTitle);
		
}  
	
	@Override
    public void deleteEvent(int eventId) {
        eventDAO.deleteEvent(eventId);
    } 
	
	@Override
    public EventVO getEventById(int eventId) {
        return eventDAO.getEventById(eventId); // 구현 필요
    }
	
	
	 
	@Override
	public void updateEventByEventNum(int eventNum, EventVO updatedEvent) {
	    EventVO existingEvent = eventDAO.getEventByEventNum(eventNum);
	    if (existingEvent != null) {
	        // 필요한 속성들 업데이트
	    	existingEvent.setTitle(updatedEvent.getTitle());
	        existingEvent.setStarted_at(updatedEvent.getStarted_at());
	        existingEvent.setEnded_at(updatedEvent.getEnded_at());
	        existingEvent.setLocation(updatedEvent.getLocation());
	        existingEvent.setType(updatedEvent.getType());

	        eventDAO.updateEvent(existingEvent);
	    } else {
	        throw new IllegalArgumentException("이벤트를 찾을 수 없거나 이벤트 번호가 일치하지 않습니다.");
	    }
	}
	@Override
	public void updateEvent(EventVO event) {
		 eventDAO.updateEvent(event);
		
	}

	@Override
	public void updateEventByIdAndTitle(int eventId, String eventTitle, EventVO updatedEvent) {
	
		
	}

	@Override
	public void updateEventByTitle(String title, EventVO existingEvent) {
		
		
	}

	@Override
	public EventVO getEventByEventNum(int eventNum) {
	    return eventDAO.getEventByEventNum(eventNum);
	}
	 
 
	   
	    
	}
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
    public void updateEvent(EventVO event) {
        eventDAO.updateEvent(event);
    } 
	    
	}  
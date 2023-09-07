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
    public EventVO getEventById(int eventId) {
        return eventDAO.getEventById(eventId);
    }
	
	 
	@Override
	public void updateEventByEventNum(int eventNum, EventVO updatedEvent) {
	    EventVO existingEvent = eventDAO.getEventByEventNum(eventNum);
	    if (existingEvent != null) {
	        // �ʿ��� �Ӽ��� ������Ʈ
	    	existingEvent.setTitle(updatedEvent.getTitle());
	        existingEvent.setStarted_at(updatedEvent.getStarted_at());
	        existingEvent.setEnded_at(updatedEvent.getEnded_at());
	        existingEvent.setLocation(updatedEvent.getLocation());
	        existingEvent.setType(updatedEvent.getType());

	        eventDAO.updateEvent(existingEvent);
	    } else {
	        throw new IllegalArgumentException("�̺�Ʈ�� ã�� �� ���ų� �̺�Ʈ ��ȣ�� ��ġ���� �ʽ��ϴ�.");
	    }
	}
	@Override
	@Transactional
	public void updateEvent(EventVO event) {
		 eventDAO.updateEvent(event);
		 
	} 


	@Override
	public EventVO getEventByEventNum(int eventNum) {
	    return eventDAO.getEventByEventNum(eventNum);
	}
	    
	  
	@Override
	@Transactional
	public void markEventAsDeleted(int eventNum) {
	    EventVO existingEvent = eventDAO.getEventByEventNum(eventNum); 
	    if (existingEvent != null) {
	        existingEvent.setIs_deleted(true); // ���� ���� ó��
	        eventDAO.updateEvent(existingEvent); // ������Ʈ
	    } else {
	        throw new IllegalArgumentException("�̺�Ʈ�� ã�� �� ���ų� �̺�Ʈ ��ȣ�� ��ġ���� �ʽ��ϴ�.");
	    }
	} 

	@Override
	public void deleteEvent(int eventId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEventByTitle(String title, EventVO existingEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEventByIdAndTitle(int eventId, String eventTitle, EventVO updatedEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EventVO> listEventsWithPaging(int page, int perPageNum) {
		// TODO Auto-generated method stub
		return null;
	}
 
	@Override
	public int getTotalEventCount() {
		// TODO Auto-generated method stub
		return 0;
	} 
	   
	  
	   
	  
	    
	}
package com.mainproject.event.dao;

import com.mainproject.event.vo.EventVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;  

@Repository
public class EventDAOImpl implements EventDAO {
    
	@Autowired      
    private SqlSession sqlSession;
  
    @Override
    public void insertEvent(EventVO eventVO) {
        sqlSession.insert("mapper.event.insertEvent", eventVO);
    }
      
	public List<EventVO> selectAllEvents() {
        return sqlSession.selectList("mapper.event.selectAllEvents");
    } 
	@Override
	public List<EventVO> getAllEvents() {
		  return selectAllEvents(); 
			
	}  
	
	@Override
    public EventVO getEventByTitle(String eventTitle) {
        return sqlSession.selectOne("mapper.event.getEventByTitle", eventTitle);
    }
		
	@Override
	    public void deleteEvent(int eventId) {
	        sqlSession.delete("mapper.event.deleteEvent", eventId);
	    }

	
	@Override
    public EventVO getEventById(int eventId) {
        return sqlSession.selectOne("mapper.event.getEventById", eventId); 
    }

	
	 @Override
	 @Transactional  
	 public void updateEvent(EventVO event) {
	      sqlSession.update("mapper.event.updateEvent", event);
	         
	 } 
	 
 
	 @Override
	 public void updateEventByEventNum(int eventNum, EventVO updatedEvent) {
	   
	     EventVO existingEvent = getEventByEventNum(eventNum);

	     if (existingEvent != null) {
	         
	    	 existingEvent.setTitle(updatedEvent.getTitle());
	         existingEvent.setStarted_at(updatedEvent.getStarted_at());
	         existingEvent.setEnded_at(updatedEvent.getEnded_at());
	         existingEvent.setLocation(updatedEvent.getLocation());
	         existingEvent.setType(updatedEvent.getType());

	        
	         updateEvent(existingEvent);
	     } else {
	         throw new IllegalArgumentException("Event not found.");
	     }
	 }
	
	@Override
	public void updateEventByTitle(String eventTitle, EventVO updatedEvent) {
			
	}
	
	@Override
	public EventVO getEventByEventNum(int eventNum) {
	    return sqlSession.selectOne("mapper.event.getEventByEventNum", eventNum);
	} 
	 
	@Transactional 
	@Override 
	public void markEventAsDeleted(int eventNum) {
	    sqlSession.update("mapper.event.markEventAsDeleted", eventNum); 
	}

	
	 
	@Override
	public void deleteEvent(Long eventId) {
		// TODO Auto-generated method stub
		
	}

 
	
	
 
	 
}      
  
	 

package com.mainproject.event.dao;

import com.mainproject.event.vo.EventVO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
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
	public void deleteEvent(Long eventId) {
		
		
	}

	@Override
    public EventVO getEventById(int eventId) {
        return sqlSession.selectOne("mapper.event.getEventById", eventId); // 구현 필요
    }

	
	 @Override
	    public void updateEvent(EventVO event) {
	        sqlSession.update("mapper.event.updateEvent", event);
	    }
	  

	 
}
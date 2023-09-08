package com.mainproject.event.dao;

import com.mainproject.event.vo.EventVO;
import com.mainproject.paging.Criteria;
import com.mainproject.paging.PagingVO;
import com.mainproject.user.vo.CustomUserDetails;
import com.sun.jdi.event.Event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;



@Repository
public class EventDAOImpl implements EventDAO {
      
	@Autowired       
    private SqlSession sqlSession;
	private int lastEventOrder;   
  
	@Override 
	public void insertEvent(EventVO eventVO) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	        int userNum = ((CustomUserDetails) userDetails).getUsernum();  // 현재 로그인한 사용자의 번호
	        String username = ((CustomUserDetails) userDetails).getUsername();
	        
	        int lastEventOrder = getLastEventOrderForUser(userNum); // 마지막 event_order 가져오기
	        
	        eventVO.setCreated_user_num(userNum); // 생성자 정보 설정
	        eventVO.setEvent_order(lastEventOrder + 1); // 새로운 일정의 event_order 설정
	        eventVO.setEvent_user_name(username);   
	    } else { 
	        // 사용자가 인증되지 않은 경우 또는 UserDetails가 아닌 경우에 대한 처리
	        // 예: 기본 생성자 정보를 설정하거나 예외를 던지는 등의 처리
	    }  
 
	    sqlSession.insert("mapper.event.insertEvent", eventVO);
	}  
	
	@Override 
	public void registerEvent(EventVO eventVO) throws DataAccessException {
		sqlSession.insert("mapper.event.registerEvent", eventVO);
	}
	
	@Override
	public List<EventVO> selectEventsForUserNum(int userNum) {
	    return sqlSession.selectList("mapper.event.selectEventsForUserNum", userNum);
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
	         existingEvent.setContent(updatedEvent.getContent());

	        
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

	@Override
	public List<EventVO> getAllEvents() {
		// TODO Auto-generated method stub
		return null;
	}
 
	@Override
	public List<EventVO> selectAllEvents() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<EventVO> selectAllEventsForUserNum(String userNum) {
		// TODO Auto-generated method stub
		return null;
	}
	


	@Override
    public List<Event> listEventsForUserNum(int userNum, Criteria criteria) {
		return null;
        // 구현: MyBatis 쿼리를 이용하여 페이징 처리 및 검색 처리
    }
	 
	

	@Override
	public int getLastEventOrderForUser(int userNum) {
	    Integer result = sqlSession.selectOne("mapper.event.getLastEventOrderForUser", userNum);
	    return (result != null) ? result : 0;
	}

	@Override
	public List<EventVO> listEventsForUserNum(String userNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EventVO> listEventsForUserNum(int userNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countEventsForUserNum(int userNum) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<EventVO> listEventsForUserNumWithPaging(int userNum, PagingVO pagingVO) {
		// TODO Auto-generated method stub
		return null; 
	}
	
	 
	 


 
 
	 
	
 
	 
}      
  
	 

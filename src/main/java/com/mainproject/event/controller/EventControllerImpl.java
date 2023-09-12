package com.mainproject.event.controller;
 
import com.mainproject.event.service.EventService;
import com.mainproject.event.vo.EventVO;
import com.mainproject.user.vo.CustomUserDetails;

import java.security.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
@Controller
@RequestMapping("/event") 
public class EventControllerImpl implements EventController {
   
    @Autowired 
    private EventService eventService;
       
 
    @GetMapping("/createEventForm")
    public String showCreateEventForm(Model model) {
        model.addAttribute("event", new EventVO());
        return "event/createEventForm";  
    }     
   
    @PostMapping("/createEvent")
    public void createEvent(@ModelAttribute("event") EventVO eventVO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        int userNum = ((CustomUserDetails) userDetails).getUsernum(); // 현재 로그인한 사용자의 번호
        String username = ((CustomUserDetails) userDetails).getUsername();

        int lastEventOrder = eventService.getLastEventOrderForUser(userNum);

        // 새로운 일정의 event_order 설정  
        eventVO.setEvent_order(lastEventOrder + 1);
        eventVO.setCreated_user_num(userNum); // 생성자 정보 설정
        eventVO.setEvent_user_name(username); 

        eventService.createEvent(eventVO);
        // 리다이렉트나 뷰 이름 등을 처리하는 로직이 있을 수 있습니다.
    } 
     
    
    @GetMapping("/listEvents.do")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView listMyEvents() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        int userNum = ((CustomUserDetails) userDetails).getUsernum();
               
        ModelAndView modelAndView = new ModelAndView("event/listEvents");
        modelAndView.addObject("eventsList", eventService.listEventsForUserNum(userNum));
        return modelAndView; 
    }   
    
    
      
    @GetMapping("/viewEvent") 
    public ModelAndView viewEvent(@RequestParam("eventTitle") String eventTitle) {
        ModelAndView modelAndView = new ModelAndView("event/viewEvent");
        EventVO event = eventService.getEventByTitle(eventTitle);
        modelAndView.addObject("event", event);
        modelAndView.addObject("eventNum", event.getEvent_num()); // 
        return modelAndView;
    } 
    
    @GetMapping("/deleteEvent")
    @Transactional 
    public String deleteEvent(@RequestParam("eventNum") int eventNum) {
        try {
            eventService.markEventAsDeleted(eventNum);
        } catch (IllegalArgumentException e) {
            
            e.printStackTrace();
        }
        return "redirect:/event/listEvents.do";     
    } 
    
    @GetMapping("/editEventForm")
    public String showEditEventForm(@RequestParam("eventNum") int eventNum, Model model) {
        EventVO event = eventService.getEventByEventNum(eventNum);
        if (event != null) {
            model.addAttribute("event", event); 
            return "event/editEventForm";
        } else {
            return null;  
        }
    }
    
    @PostMapping("/updateEvent") 
    public String updateEvent(@ModelAttribute EventVO event, @RequestParam("event_num") int eventNum, RedirectAttributes redirectAttributes) {
        try {
            eventService.updateEventByEventNum(eventNum, event);

         
            redirectAttributes.addAttribute("eventTitle", event.getTitle());
            return "redirect:/event/viewEvent";
        } catch (IllegalArgumentException e) { 
           
            e.printStackTrace();
            return "redirect:/event/listEvents.do"; 
        }
    }

    
	@Override
	public ModelAndView listEvents() {
		return null;
	}  
    
    
    
     
    
    
    
    
    
    
}  
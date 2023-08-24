package com.mainproject.event.controller;
 
import com.mainproject.event.service.EventService;
import com.mainproject.event.vo.EventVO;


import java.security.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
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
        eventService.createEvent(eventVO); 
            
    }   
    
    @GetMapping("/listEvents.do")  
    public ModelAndView listEvents() {
        ModelAndView modelAndView = new ModelAndView("event/listEvents"); 
        modelAndView.addObject("eventsList", eventService.listEvents());
        return modelAndView;
        
    }  
      
    @GetMapping("/viewEvent") 
    public ModelAndView viewEvent(@RequestParam("eventTitle") String eventTitle) {
        ModelAndView modelAndView = new ModelAndView("event/viewEvent");
        EventVO event = eventService.getEventByTitle(eventTitle);
        modelAndView.addObject("event", event);
        modelAndView.addObject("eventNum", event.getEvent_num()); // event_num을 추가로 전달
        return modelAndView;
    }
    
    @GetMapping("/deleteEvent")
    public String deleteEvent(@RequestParam("eventId") int eventId) {
        eventService.deleteEvent(eventId);
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

            // 업데이트된 이벤트의 뷰 페이지로 리디렉션
            redirectAttributes.addAttribute("eventTitle", event.getTitle());
            return "redirect:/event/viewEvent";
        } catch (IllegalArgumentException e) { 
            // 이벤트를 찾을 수 없거나 업데이트 실패 시, 에러 메시지를 콘솔에 출력
            e.printStackTrace();
            return "redirect:/event/listEvents.do"; // 에러 페이지로 리디렉션할 수도 있습니다.
        }
    } 
}  
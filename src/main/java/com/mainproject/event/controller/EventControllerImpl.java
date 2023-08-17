
package com.mainproject.event.controller;
 
import com.mainproject.event.service.EventService;
import com.mainproject.event.vo.EventVO;

import java.security.Timestamp;
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
    
    @GetMapping("/listEvents")
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
        return modelAndView;
    } 
    
    
    @GetMapping("/deleteEvent")
    public String deleteEvent(@RequestParam("eventId") int eventId) {
        eventService.deleteEvent(eventId);
        return "redirect:/event/listEvents";   
    } 
    
    @GetMapping("/editEventForm/{eventId}")
    public String showEditEventForm(@PathVariable int eventId, Model model) {
        EventVO event = eventService.getEventById(eventId);
        if (event != null) {
            model.addAttribute("event", event);
            return "event/editEventForm";
        } else {
            return "redirect:/event/listEvents";
        }
    }

     
   
    @PostMapping("/updateEvent")
    public String updateEvent(@ModelAttribute EventVO event, RedirectAttributes redirectAttributes) {
        EventVO existingEvent = eventService.getEventById(event.getEvent_num());
        if (existingEvent != null) {
            existingEvent.setTitle(event.getTitle());
            existingEvent.setStarted_at(event.getStarted_at());
            existingEvent.setEnded_at(event.getEnded_at());
            existingEvent.setLocation(event.getLocation());
            existingEvent.setType(event.getType());
            // 이벤트의 다른 속성도 필요에 따라 업데이트
            
            eventService.updateEvent(existingEvent);
            redirectAttributes.addAttribute("eventTitle", existingEvent.getTitle());
            return "redirect:/event/viewEvent";
        } else {
            // 이벤트를 찾을 수 없는 경우 처리할 내용
            return "redirect:/event/listEvents";
        }
    } 
}
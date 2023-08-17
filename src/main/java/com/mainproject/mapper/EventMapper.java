package com.mainproject.mapper;

import com.mainproject.event.vo.EventVO;

import java.util.List;

public interface EventMapper {
    void saveEvent(EventVO eventVO);
    List<EventVO> getAllEvents();
} 
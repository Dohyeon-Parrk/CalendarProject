package com.sparta.calendarproject.controller;

import com.sparta.calendarproject.dto.CalendarDto;
import com.sparta.calendarproject.model.CalendarModel;
import com.sparta.calendarproject.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    // 일정 생성
    @PostMapping("/calendar")
    public void createCalendar(@RequestBody CalendarModel calendarModel){
        calendarService.createCalendar(calendarModel);
    }

    // 전체 일정 조회
    @GetMapping("/lists")
    public List<CalendarModel> getAllCalendars(@RequestParam(required = false) String author,
                                               @RequestParam(required = false) String updateDate){

        return calendarService.getAllCalendars(author,updateDate);
    }

    // 단건 일정 조회
    @GetMapping("/lists/{id}")
    public CalendarModel getCalendarById(@PathVariable Long id){
        return calendarService.getCalendarById(id);
    }

    // 수정
    @PutMapping("/{id}")
    public String updateCalendar(@PathVariable Long id, @RequestBody CalendarDto calendarDto){
        try {
            calendarService.updateCalendar(id, calendarDto);
            return "수정되었습니다.";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

    }

    // 삭제
    @DeleteMapping("/{id}")
    public String deleteCalendar(@PathVariable Long id, @RequestParam String password){
        try{
            calendarService.deleteCalendar(id, password);
            return "삭제되었습니다.";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}

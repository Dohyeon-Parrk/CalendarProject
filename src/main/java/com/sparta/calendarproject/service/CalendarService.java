package com.sparta.calendarproject.service;

import com.sparta.calendarproject.dto.CalendarDto;
import com.sparta.calendarproject.model.CalendarModel;
import com.sparta.calendarproject.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.sql.Date;
import java.util.List;


@Service
public class CalendarService {

    @Autowired
    private CalendarRepository calendarRepository;

    // 일정 생성
    public void createCalendar(CalendarModel calendarModel) {

        LocalDateTime now = LocalDateTime.now();

        calendarModel.setCreateDate(now);
        calendarModel.setUpdateDate(now);

        calendarRepository.createCalendar(calendarModel);
    }

    // 전체 일정 조회
    public List<CalendarModel> getAllCalendars(String author, String updateDate){
        return calendarRepository.getAllCalendars(author, updateDate);
    }

    // 단건 조회
    public CalendarModel getCalendarById(Long id){
        return calendarRepository.getCalendarById(id);
    }

    // 수정
    public void updateCalendar(Long id, CalendarDto calendarDto){

        LocalDateTime now = LocalDateTime.now();
        // model 에서 createDate, updateDate 를 Date 타입으로 했을때 년월일만 입력됨.
//        Date currentDate = Date.valueOf(now.toLocalDate());

        CalendarModel existingCalendar = null;

        try {
            existingCalendar = calendarRepository.getCalendarById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("해당 일정을 찾을 수 없습니다.", e);
        }

        if(!existingCalendar.getPassword().equals(calendarDto.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        existingCalendar.setTodolist(calendarDto.getTodolist());
        existingCalendar.setAuthor(calendarDto.getAuthor());
        existingCalendar.setUpdateDate(now);

        calendarRepository.updateCalendar(existingCalendar);
    }

    // 삭제
    public void deleteCalendar(Long id, String password){
        CalendarModel existingCalendar = null;

        try{
            existingCalendar = calendarRepository.getCalendarById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("해당 일정을 찾을 수 없습니다.", e);
        }

        if(!existingCalendar.getPassword().equals(password)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        calendarRepository.deleteCalendar(id);
    }
}

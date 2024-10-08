package com.sparta.calendarproject.repository;

import com.sparta.calendarproject.model.CalendarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CalendarRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 일정 생성
    public int createCalendar(CalendarModel calendarModel) {
        String sql = "insert into calendar (author, todolist, password, createDate, updateDate) values(?,?,?,?,?)";
        return jdbcTemplate.update(sql, calendarModel.getAuthor(), calendarModel.getTodolist()
                , calendarModel.getPassword(), calendarModel.getCreateDate(), calendarModel.getUpdateDate());
    }

    // 전체 일정 조회
    public List<CalendarModel> getAllCalendars(String author, String updateDate) {
        StringBuilder sql = new StringBuilder("select * from calendar where 1=1");
        List<Object> params = new ArrayList<Object>();

        if(author != null && !author.isEmpty()){
            sql.append(" and author = ?");
            params.add(author);
        }

        if(updateDate != null && !updateDate.isEmpty()){
            sql.append(" and date(updateDate) = ?");
            params.add(Date.valueOf(updateDate));
        }

        sql.append(" order by updateDate desc");

        return jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(CalendarModel.class));
    }

    // 단건 일정 조회
    public CalendarModel getCalendarById(Long id) {
        String sql = "select * from calendar where id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(CalendarModel.class), id);
    }

    // 수정
    public void updateCalendar(CalendarModel calendarModel) {
        String sql = "update calendar set todolist = ?, author = ?, updateDate = ? where id = ?";
        jdbcTemplate.update(sql, calendarModel.getTodolist(), calendarModel.getAuthor(), calendarModel.getUpdateDate(), calendarModel.getId());
    }

    // 삭제
    public int deleteCalendar(Long id) {
        String sql = "delete from calendar where id = ?";
        return jdbcTemplate.update(sql, id);
    }
}

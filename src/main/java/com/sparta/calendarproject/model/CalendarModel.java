package com.sparta.calendarproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalendarModel {

    private Long id;

    private String author;
    private String todolist;
    private String password;
//    private Date createDate;
//    private Date updateDate;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}

package org.example;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Comment {
    private User user;
    private String text;
    private String title;
    private LocalDateTime realTime;
    private String time;


    public Comment(String title, String text){
        this.text = text;
        this.title = title;
        this.realTime = LocalDateTime.now();
    }
    public String getText(){
        return text;
    }
    public User getUser(){
        return user;
    }
    public String getTitle(){
        return title;
    }
    public String getTime(){
        DateTimeFormatter Time = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
        return realTime.format(Time);
    }
    public LocalDateTime getRealTime(){
        return realTime;
    }
    public void setTime(String time){
        this.time = time;
    }

}

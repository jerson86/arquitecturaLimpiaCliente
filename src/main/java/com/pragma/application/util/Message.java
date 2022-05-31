package com.pragma.application.util;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class Message {
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String title;
    private String message;

    public Message(HttpStatus status, String title, String message){
        timestamp = LocalDateTime.now();
        this.status =  status;
        this.title = title;
        this.message = message;
    }
}

package com.brunoporfidio.organizable.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
public class Message {
    
    private String Message;
    
//    private Message(String user_not_found){
//    }
//    
//    public static ResponseEntity<String> getResponseEnity(String message, HttpStatus httpStatus){
//        return new ResponseEntity<String>("Message : " + message, httpStatus);
//    }
    
}

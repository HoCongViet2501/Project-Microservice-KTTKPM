package com.se.gateway.security.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private Date timeStamp;
    
    private String message;
    
    private String error;
    
}

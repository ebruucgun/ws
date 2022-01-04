package com.mailCampaign.ws.error;

import lombok.Data;

import java.util.Date;

@Data
public class ApiError {

    private int status;

    private String message;

    private String path;

    private long timeamp = new Date().getTime();


    public ApiError(int status, String message, String path){
        this.status = status;
        this.message = message;
        this.path = path;
    }
}

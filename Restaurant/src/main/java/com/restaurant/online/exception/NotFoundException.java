package com.restaurant.online.exception;

public class NotFoundException extends RuntimeException{
    //It can be utilized as
    //itm not found
    //order not founnd
    //Executive not Found

    public NotFoundException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public NotFoundException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public NotFoundException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public NotFoundException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

}
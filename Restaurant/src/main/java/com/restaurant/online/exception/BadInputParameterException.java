package com.restaurant.online.exception;

public class BadInputParameterException extends RuntimeException{
    //It can be utilized as
    //itm not found
    //order not founnd
    //Executive not Found

    public BadInputParameterException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public BadInputParameterException(String message, Throwable cause, boolean enableSuppression,
                             boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public BadInputParameterException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public BadInputParameterException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public BadInputParameterException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

}


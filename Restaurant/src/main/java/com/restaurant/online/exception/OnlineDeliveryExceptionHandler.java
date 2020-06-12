package com.restaurant.online.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OnlineDeliveryExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<OnlineDeliveryErrorResponse> handleException(NotFoundException e)
    {
        OnlineDeliveryErrorResponse errorResponse=new OnlineDeliveryErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<OnlineDeliveryErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<OnlineDeliveryErrorResponse> handleException(DeliveryExecutiveNotAvailableException e)
    {
        OnlineDeliveryErrorResponse errorResponse=new OnlineDeliveryErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<OnlineDeliveryErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<OnlineDeliveryErrorResponse> handleException(BadInputParameterException e)
    {
        OnlineDeliveryErrorResponse errorResponse=new OnlineDeliveryErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<OnlineDeliveryErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
    }



    //Generic Exception

    @ExceptionHandler
    public ResponseEntity<OnlineDeliveryErrorResponse> handleException(Exception e)
    {
        System.out.println(e.getStackTrace());
        OnlineDeliveryErrorResponse errorResponse=new OnlineDeliveryErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<OnlineDeliveryErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
    }

}

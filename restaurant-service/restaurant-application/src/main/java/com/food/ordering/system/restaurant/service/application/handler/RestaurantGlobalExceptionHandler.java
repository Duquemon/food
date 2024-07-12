package com.food.ordering.system.restaurant.service.application.handler;

import com.food.ordering.system.application.handler.ErrorDTO;
import com.food.ordering.system.application.handler.GlobalExceptionHandler;
import com.food.ordering.system.restaurant.service.domain.exception.RestaurantDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class RestaurantGlobalExceptionHandler extends GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {RestaurantDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(RestaurantDomainException exception) {
        log.error(exception.getMessage(), exception);
        return ErrorDTO.builder().code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(exception.getMessage()).build();
    }
}
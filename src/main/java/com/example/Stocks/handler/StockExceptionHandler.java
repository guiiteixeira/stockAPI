package com.example.Stocks.handler;

import com.example.Stocks.exception.StockException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StockExceptionHandler {

    @ExceptionHandler
    public String handleStockException(StockException stockException){
        return stockException.getMessage();
    }
}

package com.nikitahohulia.Cinema.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotFoundController {
    @ExceptionHandler(value = Exception.class)
    protected String handleException(Exception ex, Model theModel) {

        theModel.addAttribute("error", ex.getMessage());
        return "error";
    }

}


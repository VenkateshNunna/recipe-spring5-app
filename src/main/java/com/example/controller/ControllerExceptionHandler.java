package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.example.exceptions.NotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ModelAndView HandleNotFoundException(Exception e) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("404error");
		modelAndView.addObject("exception", e);
		return modelAndView;

	}

}

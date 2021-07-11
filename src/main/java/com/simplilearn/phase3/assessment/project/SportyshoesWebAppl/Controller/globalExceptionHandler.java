package com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class globalExceptionHandler 
{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception ex){
		//String errorStr = ExceptionUtils.getStackTrace(ex);
		logger.error("Exception Occured::"+ ex);
		return "errorHandler";
	}
}

package com.fintech.gams.controller;

import javax.servlet.http.HttpServletRequest;

import com.fintech.gams.bean.GenericController;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class NotFoundController extends GenericController implements ErrorController
{
	private static final String ERROR_PATH = "/error";

	@RequestMapping(ERROR_PATH)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ModelAndView handleError(HttpServletRequest request)
	{
		return new ModelAndView("404");
	}

	@Override
	public String getErrorPath()
	{
		return ERROR_PATH;
	}
}

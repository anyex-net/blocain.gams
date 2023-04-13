package com.fintech.gams.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.exception.BusinessException;

import springfox.documentation.annotations.ApiIgnore;

/**
 * IndexController
 * <p>File: IndexController.java </p>
 * <p>Title: IndexController </p>
 * <p>Description: IndexController </p>
 * <p>Copyright: Copyright (c) 2019-05-21</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Controller
@ApiIgnore
public class IndexController extends GenericController
{
    @GetMapping("/")
    public String index() throws BusinessException
    {
        return "redirect:swagger-ui.html";
    }
}
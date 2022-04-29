package com.nazzd.complex.seed.config.web;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

public class WebDataBinderControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(false);
        binder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

}

package com.wipro.date.controller;

import com.wipro.date.domain.DateForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WebController {


    @GetMapping("/")
    public String showForm(DateForm form) {
        return "form";
    }

}
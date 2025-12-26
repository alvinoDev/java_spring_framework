package com.alvinodev.screenmatchpro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SerieController {

    @GetMapping("/series")
    public String showMessage(){
        return "Este mensaje es desde showMessage";
    }
}

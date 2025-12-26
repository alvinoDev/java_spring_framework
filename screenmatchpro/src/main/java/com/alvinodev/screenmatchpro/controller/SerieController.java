package com.alvinodev.screenmatchpro.controller;

import com.alvinodev.screenmatchpro.model.Serie;
import com.alvinodev.screenmatchpro.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SerieController {
    @Autowired
    private SerieRepository repository;

    @GetMapping("/series")
    public List<Serie> getAllSeries(){
        return repository.findAll();
    }
}

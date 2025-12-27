package com.alvinodev.screenmatchpro.controller;

import com.alvinodev.screenmatchpro.dto.SerieDTO;
import com.alvinodev.screenmatchpro.repository.SerieRepository;
import com.alvinodev.screenmatchpro.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SerieController {
    @Autowired
    private SerieService service;

    @GetMapping("/series")
    public List<SerieDTO> getAllSeries(){
        return service.getAllSeries();
    }

    @GetMapping("/series/top5")
    public List<SerieDTO> getTop5() {
        return service.getTop5();
    }
}

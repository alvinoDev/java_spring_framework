package com.alvinodev.screenmatchpro.controller;

import com.alvinodev.screenmatchpro.dto.SerieDTO;
import com.alvinodev.screenmatchpro.repository.SerieRepository;
import com.alvinodev.screenmatchpro.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/series")
public class SerieController {
    @Autowired
    private SerieService service;

    @GetMapping()
    public List<SerieDTO> getAllSeries(){
        return service.getAllSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> getTop5() {
        return service.getTop5();
    }

    @GetMapping("/lanzamientos")
    public List<SerieDTO> getLanzamientosRecientes() {
        return service.getLanzamientosRecientes();
    }

    @GetMapping("/{id}")
    public SerieDTO getSerie(@PathVariable Long id){
        return service.getSerie(id);
    }
}

package com.alvinodev.screenmatchpro.controller;

import com.alvinodev.screenmatchpro.dto.EpisodioDTO;
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

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDTO> getAllSessons(@PathVariable Long id){
        return service.getAllSessons(id);
    }

    @GetMapping("/{id}/temporadas/{numTemporadas}")
    public List<EpisodioDTO> getNumbersSessons(@PathVariable Long id, @PathVariable Long numTemporadas){
        return service.getNumbersSessons(id, numTemporadas);
    }

    @GetMapping("/categoria/{nombreGenero}")
    public List<SerieDTO> getSerieCategoria(@PathVariable String nombreGenero){
        return service.getSerieCategoria(nombreGenero);
    }

    @GetMapping("/{id}/temporadas/top")
    public List<EpisodioDTO> getTopEpisodios(@PathVariable Long id){
        return service.getTopEpisodios(id);
    }
}

package com.alvinodev.screenmatchpro.service;

import com.alvinodev.screenmatchpro.dto.EpisodioDTO;
import com.alvinodev.screenmatchpro.dto.SerieDTO;
import com.alvinodev.screenmatchpro.model.Serie;
import com.alvinodev.screenmatchpro.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {
    @Autowired
    private SerieRepository repository;

    public List<SerieDTO> getAllSeries(){
        return convierteDatos(repository.findAll());
    }

    public List<SerieDTO> getTop5() {
        return convierteDatos(repository.findTop5ByOrderByEvaluacionDesc());
    }

    public List<SerieDTO> getLanzamientosRecientes() {
        return convierteDatos(repository.lanzamientosMasRecientes());
    }

    public SerieDTO getSerie(Long id) {
        Optional<Serie> serie = repository.findById(id);
        if(serie.isPresent()) {
            Serie s = serie.get();
            return new SerieDTO(
                    s.getId(),
                    s.getTitulo(),
                    s.getTotalTemporadas(),
                    s.getEvaluacion(),
                    s.getPoster(),
                    s.getGenero(),
                    s.getActores(),
                    s.getSinopsis()
            );
        }
        return null;
    }


    public List<EpisodioDTO> getAllSessons(Long id) {
        Optional<Serie> serie = repository.findById(id);
        if(serie.isPresent()) {
            Serie s = serie.get();
            return s.getEpisodios().stream().map(e -> new EpisodioDTO(e.getTemporada(), e.getTitulo(), e.getNumeroEpisodio())).collect(Collectors.toList());
        }
        return null;
    }

    public List<EpisodioDTO> getNumbersSessons(Long id, Long numTemporadas) {
        return repository.getNumbersSessons(id, numTemporadas).stream()
                .map(e -> new EpisodioDTO(e.getTemporada(), e.getTitulo(), e.getNumeroEpisodio())).collect(Collectors.toList());
    }

    public List<SerieDTO> convierteDatos(List<Serie> serie) {
        return serie.stream()
                .map(s -> new SerieDTO(
                        s.getId(),
                        s.getTitulo(),
                        s.getTotalTemporadas(),
                        s.getEvaluacion(),
                        s.getPoster(),
                        s.getGenero(),
                        s.getActores(),
                        s.getSinopsis()
                )).collect(Collectors.toList());
    }
}

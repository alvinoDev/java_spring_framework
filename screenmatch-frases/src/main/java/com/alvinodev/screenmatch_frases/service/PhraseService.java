package com.alvinodev.screenmatch_frases.service;

import com.alvinodev.screenmatch_frases.dto.PhraseDTO;
import com.alvinodev.screenmatch_frases.model.Phrase;
import com.alvinodev.screenmatch_frases.repository.PhraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhraseService {
    @Autowired
    private PhraseRepository repository;
    public PhraseDTO getRandomPhrase() {
        Phrase phrase = repository.getRandomPhrase();
        return new PhraseDTO(phrase.getTitulo(), phrase.getFrase(), phrase.getPersonaje(), phrase.getPoster());
    }
}

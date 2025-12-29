package com.alvinodev.screenmatch_frases.controller;

import com.alvinodev.screenmatch_frases.dto.PhraseDTO;
import com.alvinodev.screenmatch_frases.service.PhraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class phraseController {
    @Autowired
    PhraseService service;

    @GetMapping("/series/phrases")
    public PhraseDTO getRandomPhrase() {
        return service.getRandomPhrase();
    }
}

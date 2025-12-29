package com.alvinodev.screenmatch_frases.repository;

import com.alvinodev.screenmatch_frases.dto.PhraseDTO;
import com.alvinodev.screenmatch_frases.model.Phrase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PhraseRepository extends JpaRepository<Phrase, Long> {

    // @Query("SELECT f FROM Phrase f ORDER BY FUNCTION('RANDOM') LIMIT 1")
    @Query("SELECT f FROM Phrase f ORDER BY RAND() LIMIT 1")
    public Phrase getRandomPhrase();
}

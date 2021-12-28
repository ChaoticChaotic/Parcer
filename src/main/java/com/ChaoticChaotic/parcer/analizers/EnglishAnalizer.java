package com.ChaoticChaotic.parcer.analizers;

import org.springframework.stereotype.Component;

@Component
public class EnglishAnalizer extends UniqueWordsAnalizer {

    public EnglishAnalizer() {
        super();
    }

    public void parse(String rawString) {
        String[] parsedString = rawString.toUpperCase()
                .replaceAll("[^a-zA-Z ]", "")
                .split("\\s+");
        setParsedString(parsedString);
    }
}


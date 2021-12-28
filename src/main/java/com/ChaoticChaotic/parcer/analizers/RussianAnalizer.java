package com.ChaoticChaotic.parcer.analizers;

import org.springframework.stereotype.Component;

@Component
public class RussianAnalizer extends UniqueWordsAnalizer {


    public RussianAnalizer() {
        super();
    }

    public void parse(String rawString) {
        String[] parsedString = rawString.replaceAll("[^а-яА-Я ]", "")
                .toUpperCase()
                .split("\\s+");
        setParsedString(parsedString);
    }

}

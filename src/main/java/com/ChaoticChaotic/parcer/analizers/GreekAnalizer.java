package com.ChaoticChaotic.parcer.analizers;

import org.springframework.stereotype.Component;

@Component
public class GreekAnalizer extends UniqueWordsAnalizer {

    public GreekAnalizer() {
        super();
    }

    public void parse(String rawString) {
        String[] parsedString = rawString.replaceAll("^[^\\u1F00 through \\u1FFF]+$", "")
                .toUpperCase()
                .split("\\s+");
        setParsedString(parsedString);
    }
}

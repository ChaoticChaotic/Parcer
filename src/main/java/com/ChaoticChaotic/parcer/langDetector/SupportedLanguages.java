package com.ChaoticChaotic.parcer.langDetector;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public enum SupportedLanguages {

    RUSSIAN("ru"),
    ENGLISH("en"),
    GREEK("el");

    private String value;
}

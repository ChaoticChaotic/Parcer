package com.ChaoticChaotic.parcer.langDetector;



public enum SupportedLanguages {

    RUSSIAN("ru"),
    ENGLISH("en"),
    GREEK("el");

    private String value;

    SupportedLanguages(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}

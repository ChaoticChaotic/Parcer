package com.ChaoticChaotic.parcer.service;


import com.ChaoticChaotic.parcer.analizers.EnglishAnalizer;
import com.ChaoticChaotic.parcer.analizers.GreekAnalizer;
import com.ChaoticChaotic.parcer.analizers.RussianAnalizer;
import com.ChaoticChaotic.parcer.langDetector.LangDetector;
import com.ChaoticChaotic.parcer.langDetector.SupportedLanguages;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class ParsingSet {

    String url;
    Logger LOGGER = Logger.getLogger(ParcerService.class);
    @Autowired
    RussianAnalizer russianAnalizer;
    @Autowired
    EnglishAnalizer englishAnalizer;
    @Autowired
    GreekAnalizer greekAnalizer;
    @Autowired
    LangDetector langDetector;

    public void setParsing() throws IllegalArgumentException {
        String rawString = langDetector.getRawString();
        LOGGER.info("Старт парсинга и подсчета статистики уникальных слов");
        if (langDetector.getLang().equals(SupportedLanguages.RUSSIAN.getValue())) {
            russianAnalizer.setLang(SupportedLanguages.RUSSIAN);
            russianAnalizer.setUrl(url);
            russianAnalizer.parse(rawString);
            russianAnalizer.sorting();
        }
        else if (langDetector.getLang().equals(SupportedLanguages.ENGLISH.getValue())) {
            englishAnalizer.setLang(SupportedLanguages.ENGLISH);
            englishAnalizer.setUrl(url);
            englishAnalizer.parse(rawString);
            englishAnalizer.sorting();
        }
        else if (langDetector.getLang().equals(SupportedLanguages.GREEK.getValue())) {
            greekAnalizer.setLang(SupportedLanguages.GREEK);
            greekAnalizer.setUrl(url);
            greekAnalizer.parse(rawString);
            greekAnalizer.sorting();
        }
        else throw new IllegalArgumentException();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

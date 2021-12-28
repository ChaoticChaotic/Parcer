package com.ChaoticChaotic.parcer.langDetector;

import com.ChaoticChaotic.parcer.reader.SiteReader;
import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LangDetector {

    Logger LOGGER = Logger.getLogger(LangDetector.class);
    private final String PROFILE_DIRECTORY = "profiles";
    private String lang;
    private String rawString;

    @Autowired
    SiteReader reader;

    public LangDetector() throws LangDetectException{
            DetectorFactory.loadProfile(PROFILE_DIRECTORY);
    }

    public void detect(){
        try {
            Detector detector = DetectorFactory.create();
            rawString = reader.getRawString();
            detector.append(rawString);
            setLang(detector.detect());
        } catch (LangDetectException e) {
        LOGGER.error("Ошибка! Ошибка при попытке определения языка " + e);
        }
        LOGGER.info("Инициализация языка, язык сайта: " + getLang());
    }


    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getRawString() {
        return rawString;
    }

    public void setRawString(String rawString) {
        this.rawString = rawString;
    }
}

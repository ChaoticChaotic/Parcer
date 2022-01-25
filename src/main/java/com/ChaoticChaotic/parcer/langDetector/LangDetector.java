package com.ChaoticChaotic.parcer.langDetector;

import com.ChaoticChaotic.parcer.reader.SiteReader;
import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j @Getter @Setter
public class LangDetector {


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
        log.error("Ошибка! Ошибка при попытке определения языка " + e);
        }
        log.info("Инициализация языка, язык сайта: " + getLang());
    }
}

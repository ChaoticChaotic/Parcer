package com.ChaoticChaotic.parcer.reader;


import com.ChaoticChaotic.parcer.service.ParcerService;
import com.ChaoticChaotic.parcer.ui.UI;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SiteReader{

    private Logger LOGGER = Logger.getLogger(SiteReader.class);
    private String defAgent = "Chrome/95.0.4638.69";
    private String defReferrer = "http://www.google.com";
    private String rawString;

    @Autowired
    UI ui;


    public void read() {
        try {
            rawString = Jsoup.connect(ui.getUrl())
                    .userAgent(defAgent)
                    .referrer(defReferrer)
                    .get().text();
        } catch (IOException e) {
            LOGGER.warn("Ошибка входящего потока, перезапуск " + e);
            System.out.println("Ошибка входящего потока, перезапуск");
            System.out.println();

        } catch (IllegalArgumentException e) {
            LOGGER.warn("Некорректный формат адреса, перезапуск " + e);
            System.out.println("Некорректный формат адреса, перезапуск");
            System.out.println();

        }
        LOGGER.info("Текст с сайта " + ui.getUrl() +  " прочитан");
    }

    private SiteReader() {}

    public SiteReader(String defAgent, String defReferrer) {
        this.defAgent = defAgent;
        this.defReferrer = defReferrer;
    }

    public String getRawString() {
        return rawString;
    }

    public void setRawString(String rawString) {
        this.rawString = rawString;
    }

}

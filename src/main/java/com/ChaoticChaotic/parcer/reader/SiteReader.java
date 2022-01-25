package com.ChaoticChaotic.parcer.reader;


import com.ChaoticChaotic.parcer.ui.UI;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Log4j @NoArgsConstructor @Getter @Setter
public class SiteReader{


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
            log.warn("Ошибка входящего потока, перезапуск " + e);
            System.out.println("Ошибка входящего потока, перезапуск");
            System.out.println();

        } catch (IllegalArgumentException e) {
            log.warn("Некорректный формат адреса, перезапуск " + e);
            System.out.println("Некорректный формат адреса, перезапуск");
            System.out.println();

        }
        log.info("Текст с сайта " + ui.getUrl() +  " прочитан");
    }

    public SiteReader(String defAgent, String defReferrer) {
        this.defAgent = defAgent;
        this.defReferrer = defReferrer;
    }
}

package com.ChaoticChaotic.parcer.reader;


import com.ChaoticChaotic.parcer.service.ParserService;
import com.ChaoticChaotic.parcer.service.ParserServiceImpl;
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

    ParserServiceImpl parserService;


    public void read(String url) {
        try {
            rawString = Jsoup.connect(url)
                    .userAgent(defAgent)
                    .referrer(defReferrer)
                    .get().text();
        } catch (IOException e) {
            log.warn("Ошибка входящего потока, перезапуск " + e);
            System.out.println("Ошибка входящего потока, перезапуск");
            System.out.println();
            parserService.start();
        } catch (IllegalArgumentException e) {
            log.warn("Некорректный формат адреса, перезапуск " + e);
            System.out.println("Некорректный формат адреса, перезапуск");
            System.out.println();
            parserService.start();
        }
        log.info("Текст с сайта " + url +  " прочитан");
    }

    public SiteReader(String defAgent, String defReferrer) {
        this.defAgent = defAgent;
        this.defReferrer = defReferrer;
    }
}

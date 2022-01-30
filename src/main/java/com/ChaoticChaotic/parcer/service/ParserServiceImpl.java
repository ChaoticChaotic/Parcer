package com.ChaoticChaotic.parcer.service;

import com.ChaoticChaotic.parcer.langDetector.LangDetector;
import com.ChaoticChaotic.parcer.reader.SiteReader;
import com.ChaoticChaotic.parcer.ui.UI;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Log4j @Getter @Setter
public class ParserServiceImpl implements ParserService {


    private boolean isStarted = true;

    @Autowired
    SiteReader siteReader;
    @Autowired
    LangDetector langDetector;
    @Autowired
    ParsingSet parsingSetter;
    @Autowired
    UI ui;

    @Override
    public void start() {
        while (isStarted) {
            log.info("Начало работы");
            ui.getInput();
            siteReader.read(ui.getUrl());
            langDetector.detect();
            parsingSetter.setUrl(ui.getUrl());
            try {
                parsingSetter.setParsing();
            } catch (IllegalArgumentException e) {
                System.out.println("Язык пока не поддерживается! Введите данные повторно.");
                System.out.println();
                log.warn("Язык пока не поддерживается,перезапуск");
                start();
            }
            log.info("Текст обработан");
            setStarted(false);
            close(isStarted);
        }
    }

    @Override
    public void close(Boolean isStarted) {
        if (!isStarted) {
            Integer command = ui.getCommand();
            if (command <= 3){
                ui.commandHandler(command);
            }
            else if (command == 4){
                log.info("Запрос продолжения подсчета уникальных слов");
                setStarted(true);
                start();
            }
            else
                log.info("Запрос завершения работы");
                setStarted(false);
                log.info("Завершение работы");
                System.exit(0);

        }
    }
}

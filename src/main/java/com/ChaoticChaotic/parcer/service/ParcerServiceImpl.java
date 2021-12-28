package com.ChaoticChaotic.parcer.service;

import com.ChaoticChaotic.parcer.langDetector.LangDetector;
import com.ChaoticChaotic.parcer.reader.SiteReader;
import com.ChaoticChaotic.parcer.ui.UI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ParcerServiceImpl implements ParcerService  {

    private final Logger LOGGER = Logger.getLogger(ParcerService.class);
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
            LOGGER.info("Начало работы");
            ui.getInput();
            siteReader.read();
            langDetector.detect();
            parsingSetter.setUrl(ui.getUrl());
            try {
                parsingSetter.setParsing();
            } catch (IllegalArgumentException e) {
                System.out.println("Язык пока не поддерживается! Введите данные повторно.");
                System.out.println();
                LOGGER.warn("Язык пока не поддерживается,перезапуск");
                start();
            }
            LOGGER.info("Текст обработан");
            setStarted(false);
            close(isStarted);
        }
    }

    @Override
    public void close(Boolean isStarted) {
        if (!isStarted) {
            Integer command = ui.getCommand();
            if (command <= 4){
                try {
                    ui.commandHandler(command);
                } catch (IllegalArgumentException e) {
                    System.out.println("Язык пока не поддерживается! Введите данные повторно.");
                    System.out.println();
                    LOGGER.warn("Язык пока не поддерживается,перезапуск");
                    setStarted(false);
                    close(isStarted());
                }
                setStarted(false);
                close(isStarted());
            }
            else if (command == 5){
                LOGGER.info("Запрос продолжения подсчета уникальных слов");
                setStarted(true);
                start();
            }
            else
                LOGGER.info("Запрос завершения работы");
                setStarted(false);
                System.exit(0);
                LOGGER.info("Завершение работы");
        }
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }
}

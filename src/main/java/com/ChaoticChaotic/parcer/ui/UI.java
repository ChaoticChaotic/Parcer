package com.ChaoticChaotic.parcer.ui;

import com.ChaoticChaotic.parcer.langDetector.SupportedLanguages;
import com.ChaoticChaotic.parcer.wordsDAO.WordsDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UI {

    Scanner scanner = new Scanner(System.in);
    Logger LOGGER = Logger.getLogger(UI.class);
    private String url;
    private Integer command;

    @Autowired
    WordsDAO wordsDAO;

    public void getInput() {
        System.out.println("Вставьте URL адрес");
        LOGGER.info("Получение URL адресa");
        url = scanner.nextLine();
        if (getUrl().equals(" ") || getUrl().equals("") || getUrl() == null) {
            try {
                throw new IllegalArgumentException("Пустой URL адрес");
            } catch (IllegalArgumentException e) {
                System.out.println(e);
                System.out.println();
                LOGGER.error("Ошибка! Пустой URL адрес " + e);
                getInput();
            }
        }
        LOGGER.info("Url адрес " + url);
    }

    public Integer getCommand() {
        LOGGER.info("Загрузка меню");
        System.out.println();
        System.out.println("Выберите комманду");
        System.out.println(
                        "1. показать все записи;" +
                        "\n" +
                        "2. показать записи для введенного значения" +
                        "\n" +
                        "3. показать записи для введенного адреса" +
                        "\n" +
                        "4. показать записи для введенного языка" +
                        "\n" +
                        "5. для продолжения парсинга" +
                        "\n" +
                        "6. для завершения работы"
        );
        System.out.println();
        command = Integer.valueOf(scanner.nextLine());
        if (command < 0 || command > 6 || command == null) {
            try {
                throw new IllegalArgumentException("Неподходящий формат или отсутствующая команда");
            } catch (IllegalArgumentException e) {
                LOGGER.error("Неподходящий формат или отсутствующая команда");
                System.out.println(e);
                getCommand();
            }
        }
        return command;
    }

    public void commandHandler(Integer command) throws IllegalArgumentException {
        if (command == 1){
            LOGGER.info("Запрос вывода всех позиций базы данных");
            System.out.println(wordsDAO.findAll());
        }
        else if (command == 2){
            System.out.println("введите слово для поиска");
            System.out.println();
            String value = scanner.nextLine();
            LOGGER.info("Запрос вывода всех позиций базы данных по слову " + value);
            System.out.println(wordsDAO.findAllByValue(value));
        }
        else if (command == 3){
            System.out.println("введите url адрес");
            System.out.println();
            String url = scanner.nextLine();
            LOGGER.info("Запрос вывода всех позиций базы данных по url " + url);
            System.out.println(wordsDAO.findAllByUrl(url));
        }
        else if (command == 4){
            LOGGER.info("Запрос вывода всех позиций базы данных по языку");
            System.out.println("введите язык для поиска: en, ru, el ?");
            System.out.println();
            String lang = scanner.nextLine();
            if (lang.equals(SupportedLanguages.RUSSIAN.getValue())){
                LOGGER.info("Язык запроса: ru");
                System.out.println(wordsDAO.findAllByLanguage(SupportedLanguages.RUSSIAN));
            }
            else if (lang.equals(SupportedLanguages.ENGLISH.getValue())){
                LOGGER.info("Язык запроса: en");
                System.out.println(wordsDAO.findAllByLanguage(SupportedLanguages.ENGLISH));
            }
            else if (lang.equals(SupportedLanguages.GREEK.getValue())){
                LOGGER.info("Язык запроса: el");
                System.out.println(wordsDAO.findAllByLanguage(SupportedLanguages.GREEK));
            }
            else throw new IllegalArgumentException();
        }

    }

    public UI() {
    }

    public UI(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

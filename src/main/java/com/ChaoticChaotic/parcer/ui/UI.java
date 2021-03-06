package com.ChaoticChaotic.parcer.ui;

import com.ChaoticChaotic.parcer.entity.Word;
import com.ChaoticChaotic.parcer.wrappers.InputScanner;
import com.ChaoticChaotic.parcer.langDetector.SupportedLanguages;
import com.ChaoticChaotic.parcer.wordsDAO.WordsDAO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Log4j @Getter @Setter @NoArgsConstructor
public class UI {


    private String url;
    private Integer command;

    @Autowired
    WordsDAO wordsDAO;

    @Autowired
    InputScanner scanner;

    public void getInput() {
        System.out.println("Вставьте URL адрес");
        log.info("Получение URL адресa");
        url = scanner.getLine();
        if (getUrl().equals(" ") || getUrl().equals("") || getUrl() == null) {
            try {
                throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Пустой URL адрес");
                System.out.println();
                log.error("Ошибка! Пустой URL адрес " + e);
                getInput();
            }
        }
        log.info("Url адрес " + url);
    }

    public Integer getCommand() {
        log.info("Загрузка меню");
        System.out.println();
        System.out.println("Выберите комманду");
        System.out.println(
                        "1. показать все записи;" +
                        "\n" +
                        "2. показать записи для введенного значения" +
                        "\n" +
                        "3. показать записи для введенного адреса" +
                        "\n" +
                        "4. для продолжения парсинга" +
                        "\n" +
                        "5. для завершения работы"
        );
        System.out.println();
        command = Integer.valueOf(scanner.getLine());
        if (command <= 0 || command > 6 || command == null) {
            try {
                throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                log.error("Неподходящий формат или отсутствующая команда");
                System.out.println("Неподходящий формат или отсутствующая команда");
                getCommand();
            }
        }
        return command;
    }

    public void commandHandler(Integer command) throws IllegalArgumentException {
        if (command == 1){
            log.info("Запрос вывода всех позиций базы данных");
            for (Word word: wordsDAO.findAll()){
                System.out.println(word);
            }
        }
        else if (command == 2){
            System.out.println("введите слово для поиска");
            System.out.println();
            String value = scanner.getLine().toUpperCase(Locale.ROOT);
            log.info("Запрос вывода всех позиций базы данных по слову " + value);
            System.out.println(wordsDAO.findAllByValue(value));
        }
        else if (command == 3){
            System.out.println("введите url адрес");
            System.out.println();
            String url = scanner.getLine();
            log.info("Запрос вывода всех позиций базы данных по url " + url);
            for (Word word: wordsDAO.findAll()){
                System.out.println(word);
            }
        }
            else throw new IllegalArgumentException();
        }

}

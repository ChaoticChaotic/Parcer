package com.ChaoticChaotic.parcer.analizers;


import com.ChaoticChaotic.parcer.entity.Word;
import com.ChaoticChaotic.parcer.langDetector.SupportedLanguages;
import com.ChaoticChaotic.parcer.wordsDAO.WordsDAO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Log4j @Getter @Setter
public class UniqueWordsAnalizer {


        private String rawString;
        private String[] parsedString;
        private LinkedHashMap<String,Integer> sortedString;
        private String url;
        private SupportedLanguages lang;

        @Autowired
        WordsDAO wordsDAO;


        public void sorting() {
            HashMap<String, Integer> tmpMap = new LinkedHashMap<>();
            for (String t : getParsedString()) {
                if (tmpMap.containsKey(t)) {
                    tmpMap.put(t, tmpMap.get(t) + 1);
                } else {
                    tmpMap.put(t, 1);
                }
            }
            sortedString = tmpMap.entrySet()
                    .stream()
                    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .collect(
                            toMap(Map.Entry::getKey,Map.Entry::getValue, (e1,e2) -> e2,LinkedHashMap::new)
                    );
            log.info("Подсчет уникальных слов окончен");
            log.info("Загрузка статистики в базу данных");
            for (String s: sortedString.keySet()) {
                if (s.length() > 3 && sortedString.get(s) > 2) {
                    Word word = new Word();
                    word.setLanguage(lang);
                    word.setUrlAddress(url);
                    word.setValue(s);
                    word.setRepeats(sortedString.get(s));
                    wordsDAO.addWord(word);
                    System.out.println(word);
                }
            }
            log.info("Загрузка статистики в базу данных завершена");
        }
}


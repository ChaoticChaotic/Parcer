package com.ChaoticChaotic.parcer.analizers;


import com.ChaoticChaotic.parcer.entity.Word;
import com.ChaoticChaotic.parcer.langDetector.SupportedLanguages;
import com.ChaoticChaotic.parcer.wordsDAO.WordsDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.toMap;


public class UniqueWordsAnalizer {

        private Logger LOGGER = Logger.getLogger(UniqueWordsAnalizer.class);
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
            LOGGER.info("Подсчет уникальных слов окончен");
            LOGGER.info("Загрузка статистики в базу данных");
            for (String s: sortedString.keySet()) {
                if (s.length() > 3 && sortedString.get(s) > 2) {
                    Word word = new Word();
                    word.setLanguage(lang);
                    word.setUrlAddress(url);
                    word.setValue(s);
                    word.setRepeats(sortedString.get(s));
                    wordsDAO.addWord(word);
                }
            }
            LOGGER.info("Загрузка статистики в базу данных завершена");
        }

        public String getRawString() { return rawString; }

        public void setRawString(String rawString) {
            this.rawString = rawString;
        }

        public String[] getParsedString() {
            return parsedString;
        }

        public void setParsedString(String[] parsedString) {
            this.parsedString = parsedString;
        }

        public LinkedHashMap<String,Integer> getSortedString() {
            return sortedString;
        }

        public void setSortedString(LinkedHashMap<String,Integer> sortedString) {
            this.sortedString = sortedString;
        }

        public String getUrl() {
        return url;
        }

        public void setUrl(String url) {
        this.url = url;
        }

        public SupportedLanguages getLang() {
        return lang;
        }

        public void setLang(SupportedLanguages lang) {
        this.lang = lang;
        }
}


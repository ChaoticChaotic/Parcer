package com.ChaoticChaotic.parcer.wordsDAO;

import com.ChaoticChaotic.parcer.entity.Word;
import com.ChaoticChaotic.parcer.langDetector.SupportedLanguages;



import java.util.List;

public interface WordsDAO {
    void addWord(Word word);
    List<Word> findAll();
    List<Word> findAllByValue(String value);
    List<Word> findAllByUrl(String urlAddress);
}

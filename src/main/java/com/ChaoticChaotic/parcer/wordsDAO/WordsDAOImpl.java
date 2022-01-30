package com.ChaoticChaotic.parcer.wordsDAO;

import com.ChaoticChaotic.parcer.entity.Word;
import com.ChaoticChaotic.parcer.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordsDAOImpl implements WordsDAO {

    @Autowired
    WordRepository wordRepository;

    @Override
    public void addWord(Word word) {
        wordRepository.save(word);
    }

    @Override
    public List<Word> findAll() {
        return wordRepository.findAll();
    }

    @Override
    public List<Word> findAllByValue(String value) {
        return wordRepository.findAllByValue(value);
    }

    @Override
    public List<Word> findAllByUrl(String urlAddress) {
        return wordRepository.findAllByUrl(urlAddress);
    }
}

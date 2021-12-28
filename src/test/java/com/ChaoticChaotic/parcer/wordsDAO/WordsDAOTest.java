package com.ChaoticChaotic.parcer.wordsDAO;

import com.ChaoticChaotic.parcer.entity.Word;
import com.ChaoticChaotic.parcer.langDetector.SupportedLanguages;
import com.ChaoticChaotic.parcer.repository.WordRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WordsDAOTest {

    Word testWord;
    @Mock
    WordRepository wordRepository;
    @InjectMocks
    WordsDAOImpl underTest;

    @BeforeEach
    public void setup(){
        testWord = new Word(
                 1L
                ,"GREECE"
                ,345
                ,"www.testHttp.com"
                ,SupportedLanguages.ENGLISH
        );
    }

    @Test
    void canAddWord() {
        Word test = new Word(
                 2L
                ,"KEK"
                ,344
                ,"www.testHttp.com"
                ,SupportedLanguages.ENGLISH
        );
        underTest.addWord(test);
        ArgumentCaptor<Word> townArgumentCaptor =
                ArgumentCaptor.forClass(Word.class);
        verify(wordRepository)
                .save(townArgumentCaptor.capture());
        Word capturedShipping = townArgumentCaptor.getValue();
        assertThat(capturedShipping).isEqualTo(test);
    }

    @Test
    void canFindAll() {
        underTest.findAll();
        verify(wordRepository).findAll();
    }

    @Test
    @Disabled
    void findAllByValue() {
    }

    @Test
    @Disabled
    void findAllByUrl() {
    }

    @Test
    @Disabled
    void findAllByLanguage() {
    }
}
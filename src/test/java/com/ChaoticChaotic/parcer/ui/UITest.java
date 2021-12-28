package com.ChaoticChaotic.parcer.ui;

import com.ChaoticChaotic.parcer.wordsDAO.WordsDAO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.InputStream;
import java.util.Scanner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UITest {

    String url;
    Integer command;

    @Mock
    WordsDAO wordsDAO;
    @Mock
    Scanner scanner = mock(Scanner.class);
    @InjectMocks
    UI underTest;


    @Test
    @Disabled
    void getInputNullThenException() {
        given(scanner.nextLine()).willReturn(null);
        assertThatThrownBy(() -> underTest.getInput())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Пустой URL адрес");
    }

    @Test
    @Disabled
    void getCommand() {
    }

    @Test
    @Disabled
    void canHandleCommandOne() {
    }

}
package com.ChaoticChaotic.parcer.ui;

import com.ChaoticChaotic.parcer.wrappers.InputScanner;
import com.ChaoticChaotic.parcer.wordsDAO.WordsDAO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
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
    InputScanner scanner;
    @InjectMocks
    UI underTest;


    @Test
    @Disabled
    void getInputNullThenException() {
        when(scanner.getLine()).thenReturn(" ");
        url = scanner.getLine();
        assertThatThrownBy(() -> underTest.getInput())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Пустой URL адрес");
    }

    @Test
    @Disabled
    void getCommand() {
        when(scanner.getLine()).thenReturn("0");
        assertThatThrownBy(() -> underTest.getCommand())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Неподходящий формат или отсутствующая команда");
    }

    @Test
    @Disabled
    void canHandleCommandOne() {
    }

}
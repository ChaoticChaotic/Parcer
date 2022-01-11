package com.ChaoticChaotic.parcer.wrappers;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class InputScanner {

    private final Scanner scanner;

    public InputScanner() {
        this(new Scanner(System.in));
    }

    InputScanner(Scanner scanner) {
        this.scanner  = scanner;
    }

    public String getLine() {
        return scanner.nextLine();
    }
}

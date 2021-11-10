package com.example.demo.calculator;

import org.springframework.stereotype.Service;

enum State {
    INIT,
    FIRST_FIGURE,
    SECOND_FIGURE,
    RESULT,
    ERROR
}

@Service
public class CalculatorService {
    State currentState = State.INIT;
    int firstFigure = 0;
    int secondFigure = 0;
    float result = 0.0f;
    char operation = ' ';

    CalculatorService() {
    }

    public String getFirstFigureString() {
        return String.valueOf(firstFigure);
    }

    public String getSecondFigureString() {
        return String.valueOf(secondFigure);
    }

    public String getResultString() {
        return String.valueOf(result);
    }

    public String getOperationString() {
        return String.valueOf(operation);
    }

    public boolean process(String operation) {
        for (int i = 0; i < operation.length(); i++) {
            if (Character.isDigit(operation.charAt(i))) {
                this.handleNumber(operation.charAt(i));
            } else {
                this.handleSymbol(operation.charAt(i));
            }
        }
        if(this.currentState == State.SECOND_FIGURE) {
            this.result = this.calculate();
            return true;
        }
        return false;
    }

    public void handleNumber(int number) {
        // ascii to number
        number = number - 48;
        System.out.println("handleNumber: " + number);
        switch (currentState) {
            case INIT:
                firstFigure = number;
                currentState = State.FIRST_FIGURE;
                break;
            case FIRST_FIGURE:
                firstFigure = firstFigure * 10 + number;
                break;
            case SECOND_FIGURE:
                secondFigure = secondFigure * 10 + number;
                break;
            case RESULT:
                firstFigure = number;
                secondFigure = 0;
                operation = ' ';
                result = 0.0f;
                currentState = State.FIRST_FIGURE;
                break;
            case ERROR:

                break;
        }
        System.out.println("Current State: " + currentState);
    }

    public void handleSymbol(char symbol) {
        System.out.println("handleSymbol: " + symbol);
        switch (currentState) {
            case INIT:
                currentState = State.ERROR;
                break;
            case FIRST_FIGURE:
                if(symbol == '+' || symbol == '-' || symbol == '*' || symbol == '/') {
                    operation = symbol;
                    currentState = State.SECOND_FIGURE;
                } else {
                    currentState = State.ERROR;
                }
                break;
            case SECOND_FIGURE:
                currentState = State.ERROR;
                break;
            case RESULT:
                if(symbol == '+' || symbol == '-' || symbol == '*' || symbol == '/') {
                    firstFigure = (int) result;
                    secondFigure = 0;
                    operation = symbol;
                    result = 0.0f;
                    currentState = State.SECOND_FIGURE;
                }
                break;
            case ERROR:
                break;
        }
    }

    public float calculate() {
        System.out.println("calculate: " + firstFigure + " " + operation + " " + secondFigure);

        switch (operation) {
            case '+':
                result = Float.parseFloat(Integer.toString(firstFigure)) + Float.parseFloat(Integer.toString(secondFigure));
                break;
            case '-':
                result = Float.parseFloat(Integer.toString(firstFigure)) - Float.parseFloat(Integer.toString(secondFigure));
                break;
            case '*':
                result = Float.parseFloat(Integer.toString(firstFigure)) * Float.parseFloat(Integer.toString(secondFigure));
                break;
            case '/':
                result = Float.parseFloat(Integer.toString(firstFigure)) / Float.parseFloat(Integer.toString(secondFigure));
                break;
            default:
                result = 0;
                break;
        }
        return result;
    }

    public float calculate(String num1, String num2, String operator) {
        float result = 0;
        switch (operator) {
            case "+":
                result = Float.parseFloat(num1) + Float.parseFloat(num2);
                break;
            case "-":
                result = Float.parseFloat(num1) - Float.parseFloat(num2);
                break;
            case "*":
                result = Float.parseFloat(num1) * Float.parseFloat(num2);
                break;
            case "/":
                result = Float.parseFloat(num1) / Float.parseFloat(num2);
                break;
            default:
                result = 0;
                break;
        }
        return result;
    }


}
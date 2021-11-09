// package demo.src.main.java.com.example.demo;

package com.example.demo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {


    @GetMapping("/calculator")
    public String calculator(
        @RequestParam(name="num1", required=false, defaultValue="0") String num1, 
        @RequestParam(name="num2", required=false, defaultValue="0") String num2, 
        @RequestParam(name="op", required=false, defaultValue="0") String operator, 
        @RequestParam(name="operation", required=false, defaultValue="") String operation, 
        Model model) {
            CalculatorEngine ce = new CalculatorEngine();
            if(!operation.equals("")){
                System.out.println("operation: " + operation);
                for (int i = 0; i < operation.length(); i++) {
                    if (Character.isDigit(operation.charAt(i))) {
                        ce.handleNumber(operation.charAt(i));
                    } else {
                        ce.handleSymbol(operation.charAt(i));
                    }
                }
                if(ce.currentState == State.SECOND_FIGURE) {
                    ce.result = ce.calculate();
                    String resultString = Float.toString(ce.result);
                    String firstFigureString = Integer.toString(ce.firstFigure);
                    String secondFigureString = Integer.toString(ce.secondFigure);
                    // ascii char to string
                    String operationString = Character.toString(operation.charAt(0));

                    System.out.println("result: " + firstFigureString + " " + operationString + " " + secondFigureString + " = " + resultString);
                    model.addAttribute("th_num1", firstFigureString);
                    model.addAttribute("th_num2", secondFigureString);
                    model.addAttribute("th_operator", operationString);
                    model.addAttribute("th_result", resultString);
                    ce.currentState = State.RESULT;
                } else {
                    model.addAttribute("th_result", "ERROR");
                }

            }else {
                float result = ce.calculate(num1, num2, operator);
                model.addAttribute("th_num1", num1);
                model.addAttribute("th_num2", num2);
                model.addAttribute("th_operator", operator);
                model.addAttribute("th_result", result);
                System.out.println("Resultado: " + result);
            }
        
        return "calculator";
    }

}

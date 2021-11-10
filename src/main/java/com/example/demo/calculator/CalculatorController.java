// package demo.src.main.java.com.example.demo;

package com.example.demo.calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    @Autowired
    private CalculatorService ce;

    @GetMapping("/calculator")
    public String calculator(
        @RequestParam(name="num1", required=false, defaultValue="0") String num1, 
        @RequestParam(name="num2", required=false, defaultValue="0") String num2, 
        @RequestParam(name="op", required=false, defaultValue="0") String operator, 
        @RequestParam(name="operation", required=false, defaultValue="") String operation, 
        Model model) {
            if(!operation.equals("")){
                if(ce.process(operation)) {
                    model.addAttribute("th_num1", ce.getFirstFigureString());
                    model.addAttribute("th_num2", ce.getSecondFigureString());
                    model.addAttribute("th_operator", ce.getOperationString());
                    model.addAttribute("th_result", ce.getResultString());
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

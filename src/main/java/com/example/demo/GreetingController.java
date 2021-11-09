// package demo.src.main.java.com.example.demo;

package com.example.demo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
    @GetMapping("/greeting")
    public String greeting(
        @RequestParam(name="nombre", required=false, defaultValue="World") String name, 
        @RequestParam(name="edad", required=false, defaultValue="0") String age, 
        Model model) {
        model.addAttribute("th_name", name);
        model.addAttribute("th_age", age);
        return "greeting";
    }

    @GetMapping("/calculator")
    public String calculator(
        @RequestParam(name="num1", required=false, defaultValue="0") String num1, 
        @RequestParam(name="num2", required=false, defaultValue="0") String num2, 
        @RequestParam(name="op", required=false, defaultValue="0") String operator, 
        Model model) {
        float result = this.calculate(num1, num2, operator);
        model.addAttribute("th_num1", num1);
        model.addAttribute("th_num2", num2);
        model.addAttribute("th_operator", operator);
        model.addAttribute("th_result", result);
        System.out.println("Resultado: " + result);
        
        return "calculator";
    }

    private float calculate(String num1, String num2, String operator) {
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

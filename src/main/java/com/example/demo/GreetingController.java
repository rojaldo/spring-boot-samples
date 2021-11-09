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
        @RequestParam(name="nombre", required=true, defaultValue="World") String name, 
        @RequestParam(name="edad", required=false, defaultValue="0") String age, 
        Model model) {
        model.addAttribute("th_name", name);
        model.addAttribute("th_age", age);
        return "greeting";
    }

}

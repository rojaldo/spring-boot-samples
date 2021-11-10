package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ApodController {
    @Autowired
    private ApodRepository repository;

    @GetMapping("/apod")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Model model) {
        // for all the elements in repository
        for (Apod apod : repository.findAll()) {
            // add the element to the model
            model.addAttribute("apod", apod);
            // System.out.println("APOD: " + apod.toString());
        }
        return "apod";
    }
}

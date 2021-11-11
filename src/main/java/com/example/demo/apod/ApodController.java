package com.example.demo.apod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ApodController {


    @Autowired
    private ApodService apodService;

    @GetMapping("/apod")
    public String greeting(@RequestParam(name = "date", required = false, defaultValue = "") String date,
            Model model) {
        
        model.addAttribute("apodArray", apodService.getApodData(date));

        return "apod";
    }
}

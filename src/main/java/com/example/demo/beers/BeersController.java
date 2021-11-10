package com.example.demo.beers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BeersController {

    @Autowired
    private BeersService beersService;

    @GetMapping("/beers")
    public String greeting(@RequestParam(name = "abv", required = false, defaultValue = "") String date,
            Model model) {
        model.addAttribute("beersArray", beersService.getBeers());
        return "beers";
    }
}

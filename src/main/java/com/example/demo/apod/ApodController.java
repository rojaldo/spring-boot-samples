package com.example.demo.apod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class ApodController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApodRepository repository;

    @GetMapping("/apod")
    public String greeting(@RequestParam(name = "date", required = false, defaultValue = "World") String name,
            Model model) {
        ApodResponse apod = restTemplate.getForObject("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY", ApodResponse.class);
        ApodEntity myApod = new ApodEntity(apod.getTitle(), apod.getExplanation(), apod.getDate(), apod.getMediaType(), apod.getUrl());
        this.repository.save(myApod);
        model.addAttribute("apodArray", repository.findAll());

        return "apod";
    }
}

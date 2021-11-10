package com.example.demo.apod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ApodService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApodRepository repository;

    ApodService() {
    }

    Iterable<ApodEntity> getApodData(String date) {
        ApodResponse apod = new ApodResponse();
        boolean repeated = false;
        if (date.equals("")) {
            apod = restTemplate.getForObject("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY", ApodResponse.class);
            
        } else {
            apod = restTemplate.getForObject("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&date=" + date, ApodResponse.class);
        }
        Iterable<ApodEntity> apodCollection = this.repository.findAll();
        for (ApodEntity apodEntity : apodCollection) {
            if (apodEntity.getDate().equals(apod.getDate())) {
                repeated = true;
            }
        }
        ApodEntity myApod = new ApodEntity(apod.getTitle(), apod.getExplanation(), apod.getDate(), apod.getMediaType(), apod.getUrl());
        if(repeated == false) this.repository.save(myApod);
        return this.repository.findAll();
    }
}

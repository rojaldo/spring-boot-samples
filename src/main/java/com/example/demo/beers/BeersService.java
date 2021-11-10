package com.example.demo.beers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BeersService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BeerRepository repository;

    BeersService() {
    }

    Iterable<BeerEntity> getBeers() {
        BeersResponse[] beers = restTemplate.getForObject("https://api.punkapi.com/v2/beers", BeersResponse[].class);
        for (BeersResponse beer : beers) {
            BeerEntity myBeer = new BeerEntity(beer.getName(), beer.getDescription(), beer.getTagline(),
                    beer.getFirstBrewed(), beer.getImageUrl(), beer.getAbv());
            repository.save(myBeer);
        }
        return repository.findAll();

    }
}

package com.example.demo.beers;

import java.util.ArrayList;
import java.util.List;

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

    public List<BeerEntity> getAllBeersFromApi() {

		List<BeerEntity> beerToReturn = new ArrayList<>();
		BeersResponse[] beers = restTemplate.getForObject("https://api.punkapi.com/v2/beers", BeersResponse[].class);
		for (BeersResponse beer : beers) {
			BeerEntity myBeer = new BeerEntity(beer.getName(), beer.getDescription(), beer.getTagline(),
					beer.getFirstBrewed(), beer.getImageUrl(), beer.getAbv());
			beerToReturn.add(myBeer);

		}

		return beerToReturn;
	}

	public List<BeerResponse> getBeerss() {
		List<BeerResponse> beerReturn = new ArrayList<>();
		
		
		for (BeerEntity beer : repository.findAll()) {
			BeerResponse myBeer = new BeerResponse(beer.getName(), beer.getDescription(), beer.getTagline(),
					beer.getFirstBrewed(), beer.getImageUrl(), beer.getAbv());

			beerReturn.add(myBeer);
		}
		return beerReturn;

	}

    public Iterable<BeerEntity> getFilteredBeers(String abvGt, String abvLt) {
        if (abvGt.isEmpty() && abvLt.isEmpty()) {
            return repository.findAll();
        } else if (abvGt.isEmpty() && !abvLt.isEmpty()) {
            double abvLtDouble = Double.parseDouble(abvLt);
            return repository.findByAbvLessThan(abvLtDouble);
        } else if (!abvGt.isEmpty() && abvLt.isEmpty()) {
            // string to double
            double abvGtDouble = Double.parseDouble(abvGt);
            return repository.findByAbvGreaterThan(abvGtDouble);
        } else if (!abvGt.isEmpty() && !abvLt.isEmpty()) {
            // string to double
            double abvGtDouble = Double.parseDouble(abvGt);
            double abvLtDouble = Double.parseDouble(abvLt);
            return repository.findByAbvGreaterThanAndAbvLessThan(abvGtDouble, abvLtDouble);
        }
        return repository.findAll();   
     }
}

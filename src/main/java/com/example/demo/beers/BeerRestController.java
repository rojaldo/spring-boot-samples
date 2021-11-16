package com.example.demo.beers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/beers")
public class BeerRestController {

    @Autowired
    private BeerRepository repository;

    @Autowired
    private BeersService service;

    @GetMapping("")
    public Iterable<BeerEntity> getAllBeers(
            @RequestParam(name = "abv_gt", required = false, defaultValue = "") String abvGt,
            @RequestParam(name = "abv_lt", required = false, defaultValue = "") String abvLt) {

        return service.getFilteredBeers(abvGt, abvLt);
    }

    @GetMapping("/{id}")
    public BeerEntity getBeerById(@PathVariable Long id) {
        return repository.findById(id).get();
    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public BeerEntity createBeer(@RequestBody BeerEntity beer) {
        return repository.save(beer);
    }

    @PutMapping("/{id}")
    public BeerEntity updateBeer(@PathVariable Long id, @RequestBody BeerEntity beer) {
        // find beer by id
        BeerEntity beerEntity = repository.findById(id).get();
        beer.setId(beerEntity.getId());
        return repository.save(beer);
    }

    @DeleteMapping("/{id}")
    public void deleteBeer(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
package com.example.demo.pubs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pubs")
public class PubRestController {
    
    @Autowired
    private PubRepository pubRepository;

    @GetMapping("")
    public Iterable<PubEntity> getAllPub(){
        return pubRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PubEntity> getPubById(@PathVariable Long id) {
        try {
            return new ResponseEntity<PubEntity>(pubRepository.findById(id).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<PubEntity>(new PubEntity(), HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public PubEntity createPub(@RequestBody PubEntity pubEntity) {
        return pubRepository.save(pubEntity);
    }

    @PutMapping("/{id}")
    public PubEntity updatePub(@PathVariable Long id, @RequestBody PubEntity pubEntity) {
        PubEntity pubToUpdate = pubRepository.findById(id).get();
        pubToUpdate.setName(pubEntity.getName());
        pubToUpdate.setLocation(pubEntity.getLocation());
        pubToUpdate.setPhone(pubEntity.getPhone());
        
        return pubRepository.save(pubToUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deletePub(@PathVariable Long id) {
        try {
            pubRepository.deleteById(id);
            return new ResponseEntity<Long>(id, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<Long>(id, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public PubEntity patchBeer(@PathVariable Long id, @RequestBody PubEntity pubEntity) {
        PubEntity pubToUpdate = pubRepository.findById(id).get();
        if(pubEntity.getName() != null) {
            pubToUpdate.setName(pubEntity.getName());
        }
        if(pubEntity.getLocation() != null) {
            pubToUpdate.setLocation(pubEntity.getLocation());
        }
        if(pubEntity.getPhone() != null) {
            pubToUpdate.setPhone(pubEntity.getPhone());
        }
    
        return pubRepository.save(pubToUpdate);
    }

}





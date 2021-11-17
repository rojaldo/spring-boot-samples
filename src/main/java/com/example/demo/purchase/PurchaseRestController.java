package com.example.demo.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/purchases")
public class PurchaseRestController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping
    public Iterable<PurchaseEntity> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    @GetMapping("/{id}")
    public PurchaseEntity getPurchaseById(@PathVariable("id") long id) {
        return purchaseService.getPurchaseById(id);
    }

    @PostMapping
    public PurchaseEntity createPurchase(@RequestBody PurchaseRequest purchase) {

        return purchaseService.createPurchase(purchase);
    }

    @PutMapping("/{id}")
    public PurchaseEntity updatePurchase(@PathVariable long id, @RequestBody PurchaseRequest purchase) {
        return purchaseService.updatePurchase(id, purchase);
    }

}

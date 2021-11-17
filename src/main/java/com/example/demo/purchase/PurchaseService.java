package com.example.demo.purchase;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.beers.BeerEntity;
import com.example.demo.beers.BeerRepository;
import com.example.demo.pubs.PubRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

    @Autowired
    private BeerRepository beerRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PubRepository pubRepository;

    public Iterable<PurchaseEntity> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    public PurchaseEntity createPurchase(PurchaseRequest purchase) {

        PurchaseEntity newPurchase = new PurchaseEntity();
        newPurchase.setPub(pubRepository.findById(purchase.getPubId()));
        newPurchase.setAmount(purchase.getAmount());
        newPurchase.setDate(purchase.getDate());
        newPurchase.setPrice(purchase.getPrice());
        newPurchase.setBeer(beerRepository.findById(purchase.getBeerId()));

        return purchaseRepository.save(newPurchase);
    }

    public PurchaseEntity updatePurchase(long id, PurchaseRequest purchase) {

        PurchaseEntity purchaseToUpdate = purchaseRepository.findById(id);
        purchaseToUpdate.setPub(pubRepository.findById(purchase.getPubId()));
        purchaseToUpdate.setAmount(purchase.getAmount());
        purchaseToUpdate.setDate(purchase.getDate());
        purchaseToUpdate.setPrice(purchase.getPrice());
        purchaseToUpdate.setBeer(beerRepository.findById(purchase.getBeerId()));

        return purchaseRepository.save(purchaseToUpdate);

    }

    public void deletePurchase(long id) {
        purchaseRepository.deleteById(id);
    }

    public PurchaseEntity getPurchaseById(long id) {
        return purchaseRepository.findById(id);
    }
}

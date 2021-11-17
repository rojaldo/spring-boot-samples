package com.example.demo.purchase;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.demo.beers.BeerEntity;
import com.example.demo.pubs.PubEntity;

@Entity
@Table(name = "purchases")
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int amount;

    private double price;

    private String date;

    @ManyToOne()
    @JoinColumn(name = "PubEntity_id")
    private PubEntity pub;

    @ManyToOne()
    @JoinColumn(name = "BeerEntity_id")
    private BeerEntity beer;


    public PurchaseEntity() {
    }

    public PurchaseEntity(int amount, double price, String date, PubEntity pub, BeerEntity beer) {
        this.amount = amount;
        this.price = price;
        this.date = date;
        this.pub = pub;
        this.beer = beer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public PubEntity getPub() {
        return pub;
    }

    public void setPub(PubEntity pub) {
        this.pub = pub;
    }

    public BeerEntity getBeer() {
        return beer;
    }

    public void setBeer(BeerEntity beer) {
        this.beer = beer;
    }

    @Override
    public String toString() {
        return "PurchaseEntity [id=" + id + ", amount=" + amount + ", price=" + price + ", date=" + date + ", pub=" + pub
                + ", beer=" + beer + "]";
    }
}
package com.example.demo.purchase;


import com.fasterxml.jackson.annotation.JsonProperty;

public class PurchaseRequest {

    private int amount;

    private double price;

    private String date;

    @JsonProperty("pub_id")
    private long pubId;

    @JsonProperty("beer_id")
    private long beerId;

    public PurchaseRequest() {
    }

    public PurchaseRequest(int amount, double price, String date, long pubId, long beerId) {
        this.amount = amount;
        this.price = price;
        this.date = date;
        this.pubId = pubId;
        this.beerId = beerId;
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

    public long getPubId() {
        return pubId;
    }

    public void setPubId(long pubId) {
        this.pubId = pubId;
    }

    public long getBeerId() {
        return beerId;
    }

    public void setBeerId(long beerId) {
        this.beerId = beerId;
    }

    @Override
    public String toString() {
        return "PurchaseRequest{" +
                "amount=" + amount +
                ", price=" + price +
                ", date='" + date + '\'' +
                ", pubId=" + pubId +
                ", beerId=" + beerId +
                '}';
    }


}

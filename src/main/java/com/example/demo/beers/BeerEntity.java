package com.example.demo.beers;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.demo.purchase.PurchaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class BeerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    private String tagline;

    @JsonProperty("first_brewed")
    private String firstBrewed;

    @JsonProperty("image_url")
    private String imageUrl;

    private double abv;

    @OneToMany(mappedBy = "pub", 
    cascade = CascadeType.ALL, 
    fetch = javax.persistence.FetchType.LAZY,
    orphanRemoval = true)
    private List<PurchaseEntity> purchases;

    protected BeerEntity() {
    }

    public BeerEntity(String name, String description, String tagline, String firstBrewed, String imageUrl,
            double abv) {
        this.name = name;
        this.description = description;
        this.tagline = tagline;
        this.firstBrewed = firstBrewed;
        this.imageUrl = imageUrl;
        this.abv = abv;
    }

    @Override
    public String toString() {
        return "BeerEntity [id=" + id + ", name=" + name + ", description=" + description + ", tagline=" + tagline
                + ", firstBrewed=" + firstBrewed + ", imageUrl=" + imageUrl + ", abv=" + abv + "]";
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTagline() {
        return tagline;
    }

    public String getFirstBrewed() {
        return firstBrewed;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getAbv() {
        return abv;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

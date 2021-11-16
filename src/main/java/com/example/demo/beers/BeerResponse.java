package com.example.demo.beers;

import javax.persistence.Column;

public class BeerResponse {

    private String name;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    private String tagline;

    private String firstBrewed;

    private String imageUrl;

    private double abv;

    public BeerResponse(String name, String description, String tagline, String firstBrewed, String imageUrl,
            double abv) {
        super();
        this.name = name;
        this.description = description;
        this.tagline = tagline;
        this.firstBrewed = firstBrewed;
        this.imageUrl = imageUrl;
        this.abv = abv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getFirstBrewed() {
        return firstBrewed;
    }

    public void setFirstBrewed(String firstBrewed) {
        this.firstBrewed = firstBrewed;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getAbv() {
        return abv;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }

    @Override
    public String toString() {
        return "BeerEntiryResponse [name=" + name + ", description=" + description + ", tagline=" + tagline
                + ", firstBrewed=" + firstBrewed + ", imageUrl=" + imageUrl + ", abv=" + abv + "]";
    }

}

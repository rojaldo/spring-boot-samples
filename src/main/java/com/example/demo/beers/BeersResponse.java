package com.example.demo.beers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BeersResponse {

    String name;
    String tagline;
    @JsonProperty("first_brewed")
    String firstBrewed;
    String description;
    @JsonProperty("image_url")
    String imageUrl;
    Double abv;

    BeersResponse() {
    }

    BeersResponse(String name, String tagline, String firstBrewed, String description, String imageUrl, Double abv) {
        this.name = name;
        this.tagline = tagline;
        this.firstBrewed = firstBrewed;
        this.description = description;
        this.imageUrl = imageUrl;
        this.abv = abv;
    }

    public String getName() {
        return name;
    }
    
    public String getTagline() {
        return tagline;
    }

    public String getFirstBrewed() {
        return firstBrewed;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Double getAbv() {
        return abv;
    }
}

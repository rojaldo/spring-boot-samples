package com.example.demo.apod;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ApodEntity {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  private String title;
  
  @Column(columnDefinition = "LONGTEXT")
  private String explanation;

  private String url;
  private String date;
  private String mediaType;

  protected ApodEntity() {}

  public ApodEntity(String title, String explanation, String date, String mediaType, String url) {
    this.title = title;
    this.explanation = explanation;
    this.url = url;
    this.date = date;
    this.mediaType = mediaType;
  }
  
  @Override
  public String toString() {
    return "Apod [id=" + id + ", title=" + title + ", explanation=" + explanation + ", url=" + url + ", date=" + date + ", mediaType=" + mediaType + "]";
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getExplanation() {
    return explanation;
  }

  public String getUrl() {
    return url;
  }

  public String getDate() {
    return date;
  }

  public String getCopyright() {
    return copyright;
  }

  public String getMediaType() {
    return mediaType;
  }

}


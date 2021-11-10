package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Apod {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  private String title;
  
  @Column(columnDefinition = "LONGTEXT")
  private String explanation;
  
  private String url;
  private String date;
  private String copyright;
  private String mediaType;

  protected Apod() {}

  public Apod(String title, String explanation, String url, String date, String mediaType) {
    this.title = title;
    this.explanation = explanation;
    this.url = url;
    this.date = date;
    this.copyright = copyright;
    this.mediaType = mediaType;
  }
  
  @Override
  public String toString() {
    return "Apod [id=" + id + ", title=" + title + ", explanation=" + explanation + ", url=" + url + ", date=" + date + ", copyright=" + copyright + ", mediaType=" + mediaType + "]";
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


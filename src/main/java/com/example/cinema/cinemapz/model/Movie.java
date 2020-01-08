package com.example.cinema.cinemapz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name="movie_name")
  private String name;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name="category_id", referencedColumnName = "id")
  private MovieCategory movieCategory;

  private String description;
  private String age;
  private String duration;



  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public MovieCategory getMovieCategory() {
    return movieCategory;
  }

  public void setMovieCategory(MovieCategory movieCategory) {
    this.movieCategory = movieCategory;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getDuration() {
    return duration;
  }

  public void setDuration(String duration) {
    this.duration = duration;
  }

}

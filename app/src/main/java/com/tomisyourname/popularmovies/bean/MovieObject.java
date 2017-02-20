package com.tomisyourname.popularmovies.bean;

import java.io.Serializable;

/**
 * Created by zain on 20/02/2017.
 */

public class MovieObject implements Serializable {

  public int id;
  public String title;
  public String overview;
  public String releaseDate;
  public int voteCount;
  public double voteAverage;
  public String posterPath;

  public MovieObject() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getOverview() {
    return overview;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }

  public int getVoteCount() {
    return voteCount;
  }

  public void setVoteCount(int voteCount) {
    this.voteCount = voteCount;
  }

  public double getVoteAverage() {
    return voteAverage;
  }

  public void setVoteAverage(double voteAverage) {
    this.voteAverage = voteAverage;
  }

  public String getPosterPath() {
    return posterPath;
  }

  public void setPosterPath(String posterPath) {
    this.posterPath = "https://image.tmdb.org/t/p/w185" + posterPath;
  }
}

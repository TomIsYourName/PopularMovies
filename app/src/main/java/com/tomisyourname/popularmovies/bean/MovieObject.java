package com.tomisyourname.popularmovies.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zain on 20/02/2017.
 */

public class MovieObject implements Parcelable {

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

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.id);
    dest.writeString(this.title);
    dest.writeString(this.overview);
    dest.writeString(this.releaseDate);
    dest.writeInt(this.voteCount);
    dest.writeDouble(this.voteAverage);
    dest.writeString(this.posterPath);
  }

  protected MovieObject(Parcel in) {
    this.id = in.readInt();
    this.title = in.readString();
    this.overview = in.readString();
    this.releaseDate = in.readString();
    this.voteCount = in.readInt();
    this.voteAverage = in.readDouble();
    this.posterPath = in.readString();
  }

  public static final Creator<MovieObject> CREATOR = new Creator<MovieObject>() {
    @Override
    public MovieObject createFromParcel(Parcel source) {
      return new MovieObject(source);
    }

    @Override
    public MovieObject[] newArray(int size) {
      return new MovieObject[size];
    }
  };
}

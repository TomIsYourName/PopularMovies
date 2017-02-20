package com.tomisyourname.popularmovies.net;

/**
 * Created by zain on 20/02/2017.
 */

public interface HttpRequestListener {

  public void onResponse(String response);

  public void onError(String error);
}

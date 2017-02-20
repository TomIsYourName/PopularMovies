package com.tomisyourname.popularmovies.net;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zain on 20/02/2017.
 */

public class HttpRequest {

  private static final String LOG_TAG = "HttpRequest";

  public static void get(String urlString, HttpRequestListener listener) {
    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;
    try {
      URL url = new URL(urlString);

      urlConnection = (HttpURLConnection) url.openConnection();
      urlConnection.setRequestMethod("GET");
      urlConnection.connect();

      InputStream inputStream = urlConnection.getInputStream();
      StringBuffer buffer = new StringBuffer();
      if (inputStream == null) {
        listener.onError("Can not connect to the internet, please try again later!");
      } else {
        reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
          buffer.append(line + "\n");
        }
        if (buffer.length() == 0) {
          listener.onError("Can not connect to the internet, please try again later!");
        } else {
          listener.onResponse(buffer.toString());
          Log.e(LOG_TAG, "Response json >>>" + buffer.toString());
        }
      }
    } catch (IOException e) {
      listener.onError("Can not connect to the internet, please try again later!");
      Log.e(LOG_TAG, "Error >>>" + e);
    } finally {
      if (urlConnection != null) {
        urlConnection.disconnect();
      }
      if (reader != null) {
        try {
          reader.close();
        } catch (final IOException e) {
        }
      }
    }
  }

}

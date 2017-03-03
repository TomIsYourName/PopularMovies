package com.tomisyourname.popularmovies.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tomisyourname.popularmovies.R;
import com.tomisyourname.popularmovies.bean.MovieObject;

import java.util.List;

/**
 * Created by zain on 20/02/2017.
 */

public class MoviesListAdapter extends RecyclerView.Adapter {

  private List<MovieObject> movies;

  public MoviesListAdapter(List<MovieObject> movies) {
    this.movies = movies;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, null);
    return new ItemViewHolder(convertView);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    final MovieObject movie = movies.get(position);
    ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
    itemViewHolder.tvTitle.setText(movie.title);
    itemViewHolder.tvRate.setText(movie.voteAverage + "/10");
    final Context context = itemViewHolder.ivPoster.getContext();
    Picasso.with(context)
        .load(movie.posterPath)
        .placeholder(R.mipmap.ic_launcher)
        .into(itemViewHolder.ivPoster);
    itemViewHolder.convertView.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent intent = new Intent(context, DetailPageActivity.class);
        intent.putExtra("movie", movie);
        context.startActivity(intent);
      }
    });
  }

  @Override
  public int getItemCount() {
    return movies == null ? 0 : movies.size();
  }

  final class ItemViewHolder extends RecyclerView.ViewHolder {

    public final View convertView;
    public final ImageView ivPoster;
    public final TextView tvTitle;
    public final TextView tvRate;

    public ItemViewHolder(View convertView) {
      super(convertView);
      this.convertView = convertView;
      ivPoster = (ImageView) convertView.findViewById(R.id.iv_poster);
      tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
      tvRate = (TextView) convertView.findViewById(R.id.tv_rate);
    }

  }


}

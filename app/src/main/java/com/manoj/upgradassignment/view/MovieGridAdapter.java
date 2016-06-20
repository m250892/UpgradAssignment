package com.manoj.upgradassignment.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.manoj.upgradassignment.MovieDatabase;
import com.manoj.upgradassignment.R;
import com.manoj.upgradassignment.model.Movie;
import com.manoj.upgradassignment.utils.ImageLoader;

/**
 * Created by manoj on 21/06/16.
 */
public class MovieGridAdapter extends RecyclerView.Adapter<MovieGridAdapter.ViewHolder> {

    private MovieDatabase movieDatabase;
    private Context context;
    private AdapterView adapterView;

    public MovieGridAdapter(Context context, AdapterView adapterView) {
        this.context = context;
        this.adapterView = adapterView;
        this.movieDatabase = MovieDatabase.getInstance();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_gird_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movieDatabase.getItem(position);
        ImageLoader.getInstance().loadImage(movie.getPosterUrl(), holder.imageView, ContextCompat.getDrawable(context, R.drawable.no_image_available));

        //sending call for next item
        if (position == getItemCount() - 1) {
            adapterView.onLastItemDisplayed();
        }
    }

    @Override
    public int getItemCount() {
        return movieDatabase.getDataSize();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.movie_poster_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            adapterView.onItemClicked(getAdapterPosition());
        }
    }

}

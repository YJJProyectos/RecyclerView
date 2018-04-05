package com.android.jyang.recyclerview.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.jyang.recyclerview.R;
import com.android.jyang.recyclerview.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jyang on 3/4/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private String[] mDataset;
    private List<Movie> movies;
    private int layout;
    private OnItemClickListener itemClickListener;
    private Context context;


    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Movie> movies, int layout, OnItemClickListener listener) {
        this.movies = movies;
        this.layout = layout;
        this.itemClickListener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        context = parent.getContext();
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bind(movies.get(position), itemClickListener);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Movie movie, int position);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
//        public TextView name;
        public TextView textView;
        public ImageView imageViewPoster;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
//            this.name = (TextView) itemView.findViewById(R.id.textView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            textView = (TextView) itemView.findViewById(R.id.textViewTitle);
            imageViewPoster = (ImageView) itemView.findViewById(R.id.imageViewPoster);
        }

        public void bind(final Movie movie, final OnItemClickListener listener) {

//            this.name.setText(name);
            textView.setText(movie.getName());
            Picasso.get().load(movie.getPoster()).fit().into(imageViewPoster);
//            imageViewPoster.setImageResource(movie.getPoster());

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(movie, getAdapterPosition());
                }
            });
        }

    }



}
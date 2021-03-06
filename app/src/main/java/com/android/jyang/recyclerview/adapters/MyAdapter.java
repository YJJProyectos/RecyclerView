package com.android.jyang.recyclerview.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.jyang.recyclerview.R;
import com.android.jyang.recyclerview.models.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jyang on 3/4/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private String[] mDataset;
    private List<Item> items;
    private int layout;
    private OnItemClickListener itemClickListener;
    private Context context;
    private Activity activity;


    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Item> items, int layout, Activity activity, OnItemClickListener listener) {
        this.items = items;
        this.layout = layout;
        this.itemClickListener = listener;
        this.activity = activity;
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

        holder.bind(items.get(position), itemClickListener);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Item item, int position);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{
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
            // activity registerForContextMenu
            itemView.setOnCreateContextMenuListener(this);
        }

        public void bind(final Item item, final OnItemClickListener listener) {

//            this.name.setText(name);
            textView.setText(item.getName());
            Picasso.get().load(item.getPoster()).fit().into(imageViewPoster);
//            imageViewPoster.setImageResource(item.getPoster());

            imageViewPoster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item, getAdapterPosition());
                }
            });
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.delete_item:
                    items.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            Item item = items.get(this.getAdapterPosition());
            contextMenu.setHeaderTitle(item.getName());
            MenuInflater inflater = activity.getMenuInflater();
            inflater.inflate(R.menu.context_menu, contextMenu);
            for (int i= 0; i < contextMenu.size(); i++) {
                contextMenu.getItem(i).setOnMenuItemClickListener(this);
            }
        }
    }



}
